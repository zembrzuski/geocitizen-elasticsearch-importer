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
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.stream.Collectors;

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
        String directoryPath = String.format(directory, userId);

        List<TrajectoryLabel> someLabels = labelsLoader.load(directoryPath + "labels.txt");
        String trajectoriesPath = directoryPath + "/Trajectory/";
        File directory = new File(trajectoriesPath);
        List<File> files = Lists.newArrayList(directory.listFiles());

        List<String> collect = files.stream()
                .map(file -> enrichTrajectory(someLabels, file))
                .map(x -> new TrajectoryToPersist(userId, x.get()))
                .map(x -> gson.toJson(x))
                .limit(1)
                .collect(Collectors.toList());


        String first = collect.iterator().next();
        doPostToElasticsearch(first);

    }

    private void doPostToElasticsearch(String payload) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        restTemplate.postForObject("http://192.168.0.16:9200/geocitizen_one/group", payload, String.class, entity);
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
