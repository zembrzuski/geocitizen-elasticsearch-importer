package com.zembrzuski.geolife.geolifeimporter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.TreeMap;

@SpringBootApplication
public class GeolifeImporterApplication implements CommandLineRunner {

    private static final String FILE_PATH = "/home/zembrzuski/labs/geolife/Data/010/Trajectory/20081101042408.plt";
    private static final String LABELS_PATH = "/home/zembrzuski/labs/geolife/Data/010/labels.txt";

    @Autowired private TrajectoryEnricher trajectoryEnricher;
    @Autowired private FileReader fileReader;
    @Autowired private LabelsLoader labelsLoader;

    public static void main(String[] args) {
        SpringApplication.run(GeolifeImporterApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {

        List<TrajectoryLabel> someLabels = labelsLoader.load(LABELS_PATH);

        TreeMap<GeoLocationPoint, TrajectoryLabel> trajectory =
                trajectoryEnricher.enrichTrajectory(FILE_PATH, someLabels);

        for (GeoLocationPoint p : trajectory.keySet()) {
            if (trajectory.get(p).getMode() != null) {
                System.out.println(p);
                System.out.println(trajectory.get(p));
                System.out.println();
            }
        }

    }


}
