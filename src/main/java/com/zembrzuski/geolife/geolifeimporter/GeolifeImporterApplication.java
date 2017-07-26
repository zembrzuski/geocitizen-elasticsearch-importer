package com.zembrzuski.geolife.geolifeimporter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.TreeMap;

@SpringBootApplication
public class GeolifeImporterApplication implements CommandLineRunner {

    @Autowired
    private DirectoryProcessor directoryProcessor;

    public static void main(String[] args) {
        SpringApplication.run(GeolifeImporterApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {

        directoryProcessor.processDirectory("010");

//        List<TrajectoryLabel> someLabels = labelsLoader.load(LABELS_PATH);

//        TreeMap<GeoLocationPoint, TrajectoryLabel> trajectory =
//                trajectoryEnricher.enrichTrajectory(FILE_PATH, someLabels);

//        for (GeoLocationPoint p : trajectory.keySet()) {
//            if (trajectory.get(p).getMode() != null) {
//                System.out.println(p);
//                System.out.println(trajectory.get(p));
//                System.out.println();
//            }
//        }

    }


}
