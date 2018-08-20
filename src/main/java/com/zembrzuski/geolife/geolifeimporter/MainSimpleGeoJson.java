package com.zembrzuski.geolife.geolifeimporter;

import com.zembrzuski.geolife.geolifeimporter.csv_kepler.DirectoryProcessorForCsv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class MainSimpleGeoJson implements CommandLineRunner {

    @Value("${input_directory}")
    private String input;

    public static void main(String[] args) {
        SpringApplication.run(MainSimpleGeoJson.class, args);
    }

    @Autowired
    private DirectoryProcessorForCsv directoryProcessor;

    @Override
    public void run(String... strings) throws IOException {
        List<String> fileContent = directoryProcessor.readDirectory(input);
        System.out.println("oi");
//        String output = "/home/zem/Desktop/000.csv";
//        Files.write(Paths.get(output), fileContent);
    }

}
