package com.zembrzuski.geolife.geolifeimporter;

import com.google.common.collect.Lists;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    private static final String DIRECTORY = "/home/zembrzuski/labs/geolife/Data/%s/";
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public void processDirectory(String userId) throws IOException {
        String directoryPath = String.format(DIRECTORY, userId);

        List<TrajectoryLabel> someLabels = labelsLoader.load(directoryPath + "labels.txt");
        String trajectoriesPath = directoryPath + "/Trajectory/";
        File directory = new File(trajectoriesPath);
        List<File> files = Lists.newArrayList(directory.listFiles());

        List<TrajectoryToPersist> collect = files.stream()
                .map(file -> {
                    Optional<TreeMap<GeoLocationPoint, TrajectoryLabel>> myOptional = Optional.empty();
                    try {
                        myOptional = Optional.of(trajectoryEnricher.enrichTrajectory(file.getAbsolutePath(), someLabels));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return myOptional;
                })
                .map(geoLocationPointTrajectoryLabelTreeMap -> new TrajectoryToPersist(userId, geoLocationPointTrajectoryLabelTreeMap.get()))
                .collect(Collectors.toList());

        System.out.println(collect.size());
        System.out.println(collect.size());
        System.out.println(collect.size());
        System.out.println(collect.size());
        System.out.println(collect.size());
        System.out.println(collect.size());
        System.out.println(collect.size());


    }

}
