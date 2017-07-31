package com.zembrzuski.geolife.geolifeimporter;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zembrzuski.geolife.geolifeimporter.entity.Path;
import com.zembrzuski.geolife.geolifeimporter.entity.TrajectoryLabel;
import com.zembrzuski.geolife.geolifeimporter.entity.TrajectoryToPersist;
import com.zembrzuski.geolife.geolifeimporter.helpers.LocalDateAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;
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

    @PostConstruct
    public void init() {
        this.directory = "/home/nozes/labs/Geolife Trajectories 1.3/Data/%s/";
        this.gson = new GsonBuilder()
                //.setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateAdapter())
                .create();
    }


    public void processDirectory(String userId) throws IOException {
        String directoryPath = String.format(directory, userId);

        List<TrajectoryLabel> someLabels = labelsLoader.load(directoryPath + "labels.txt");
        String trajectoriesPath = directoryPath + "/Trajectory/";
        File directory = new File(trajectoriesPath);
        List<File> files = Lists.newArrayList(directory.listFiles());

        List<TrajectoryToPersist> trajectories = files
                .stream()
                .map(file -> enrichTrajectory(someLabels, file))
                .map(x -> new TrajectoryToPersist(userId, x.get()))
                .collect(Collectors.toList());


        TrajectoryToPersist first = trajectories.iterator().next();

        String s = gson.toJson(first);
        System.out.println(s);

        System.out.println(trajectories.size());
        System.out.println(trajectories.size());
        System.out.println(trajectories.size());
        System.out.println(trajectories.size());
        System.out.println(trajectories.size());
        System.out.println(trajectories.size());
        System.out.println(trajectories.size());


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
