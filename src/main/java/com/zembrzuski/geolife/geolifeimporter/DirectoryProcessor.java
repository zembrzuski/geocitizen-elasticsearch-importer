package com.zembrzuski.geolife.geolifeimporter;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zembrzuski.geolife.geolifeimporter.entity.Path;
import com.zembrzuski.geolife.geolifeimporter.entity.TrajectoryLabel;
import com.zembrzuski.geolife.geolifeimporter.entity.TrajectoryToPersist;
import com.zembrzuski.geolife.geolifeimporter.helpers.LocalDateAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;

/**
 * Todo pipeline de importacao de conteudo.
 *   - Leitura de arquivo
 *   - Manipulacao de dados
 *   - Enriquecimento de dados
 *   - Geracao de payload para elasticsearch
 *   - Post no elasticsearch
 */
@Component
public class DirectoryProcessor {

    @Autowired private LabelsLoader labelsLoader;
    @Autowired private TrajectoryEnricher trajectoryEnricher;

    private static final String ELASTICSEARCH_HOST = "localhost";

    private String directory;
    private Gson gson;
    private RestTemplate restTemplate;

    @PostConstruct
    public void init() {
        this.directory = "/home/nozes/labs/Geolife Trajectories 1.3/Data/%s/";

        // TODO tacar isso num bean para fazer autowired.
        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateAdapter())
                .create();

        // TODO tacar isso num bean para fazer autowired.
        this.restTemplate = new RestTemplateBuilder()
                .basicAuthorization("username", "passwd")
                .build();
    }


    public void processDirectory(String userId) throws IOException {
        System.out.println("comecou" + userId);
        String directoryPath = String.format(directory, userId);

        List<TrajectoryLabel> someLabels = labelsLoader.load(directoryPath + "labels.txt");
        String trajectoriesPath = directoryPath + "/Trajectory/";
        File directory = new File(trajectoriesPath);
        List<File> files = Lists.newArrayList(directory.listFiles());
//        List<File> files = Lists.newArrayList(
//                new File("/home/nozes/labs/Geolife Trajectories 1.3/Data/020/Trajectory/20110911000506.plt"));


        System.out.println("ae");

        // TODO a better idea would be to use bulk api to post content to elasticsearch.
        files.stream()
                .map(file -> enrichTrajectory(someLabels, file))
                .map(x -> new TrajectoryToPersist(userId, x.get()))
                .forEach(this::doPostToElasticsearch);

        System.out.println("acabou" + userId);
    }

    private void doPostToElasticsearch(TrajectoryToPersist trajectoryToPersist) {
        String payload = gson.toJson(trajectoryToPersist);
        System.out.println(payload);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        try {
            restTemplate.postForObject("http://" + ELASTICSEARCH_HOST + ":9200/geocitizen_one/group", payload, String.class, entity);
        } catch (HttpClientErrorException e) {
            // se deu pau, assumo que eh payload muito grande. pode ser um tiro no pe, mas por enquanto
            // nao me importa
            List<Path> firstPart = trajectoryToPersist.getPath().subList(0, trajectoryToPersist.getPath().size() / 2);
            List<Path> secondPart = trajectoryToPersist.getPath().subList(trajectoryToPersist.getPath().size() / 2, trajectoryToPersist.getPath().size());

            TrajectoryToPersist firstToPersist = new TrajectoryToPersist(trajectoryToPersist.getUserId(), firstPart);
            TrajectoryToPersist secondToPersist = new TrajectoryToPersist(trajectoryToPersist.getUserId(), secondPart);

            System.out.println("entrou na recursao");
            doPostToElasticsearch(firstToPersist);
            doPostToElasticsearch(secondToPersist);
        }
    }

    private Optional<TreeMap<Path, TrajectoryLabel>> enrichTrajectory(List<TrajectoryLabel> someLabels, File file) {
        Optional<TreeMap<Path, TrajectoryLabel>> myOptional = Optional.empty();
        try {
            myOptional = Optional.of(trajectoryEnricher.enrichTrajectory(file.getAbsolutePath(), someLabels));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return myOptional;
    }

}
