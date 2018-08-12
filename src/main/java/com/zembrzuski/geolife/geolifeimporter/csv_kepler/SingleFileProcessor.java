package com.zembrzuski.geolife.geolifeimporter.csv_kepler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SingleFileProcessor {

    @Autowired
    private LineFormatter lineFormatter;

    public void readFile() throws IOException {
        String filePath = "/home/zem/labs/msc/geolife_data/Data/000/Trajectory/20090526170706.plt";

        List<String> collect = Files.lines(Paths.get(filePath))
                .skip(6)
                .map(x -> lineFormatter.format(x))
                .collect(Collectors.toList());

        System.out.println("latitude,longitude,timestamp");
        collect.forEach(System.out::println);
    }

}
