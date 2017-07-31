package com.zembrzuski.geolife.geolifeimporter;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.function.Function;
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

    private static final String DIRECTORY = "/home/nozes/labs/Geolife Trajectories 1.3/Data/%s/";
    private static final Gson GSON = new Gson();

    public void processDirectory(String userId) throws IOException {
        String directoryPath = String.format(DIRECTORY, userId);

        List<TrajectoryLabel> someLabels = labelsLoader.load(directoryPath + "labels.txt");
        String trajectoriesPath = directoryPath + "/Trajectory/";
        File directory = new File(trajectoriesPath);
        List<File> files = Lists.newArrayList(directory.listFiles());

        List<TrajectoryToPersist> trajectories = files
                .stream()
                .map(file -> enrichTrajectory(someLabels, file))
                .map(x -> new TrajectoryToPersist(userId, x.get()))
                .collect(Collectors.toList());

        System.out.println(trajectories.size());
        System.out.println(trajectories.size());
        System.out.println(trajectories.size());
        System.out.println(trajectories.size());
        System.out.println(trajectories.size());
        System.out.println(trajectories.size());
        System.out.println(trajectories.size());


    }

    private Optional<TreeMap<GeoLocationPoint, TrajectoryLabel>> enrichTrajectory(List<TrajectoryLabel> someLabels, File file) {
        Optional<TreeMap<GeoLocationPoint, TrajectoryLabel>> myOptional = Optional.empty();
        try {
            myOptional = Optional.of(trajectoryEnricher.enrichTrajectory(file.getAbsolutePath(), someLabels));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return myOptional;
    }

}
