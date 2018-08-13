package com.zembrzuski.geolife.geolifeimporter.csv_kepler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

@Component
public class SingleFileProcessor {

    @Autowired
    private LineFormatter lineFormatter;

    public Stream<String> readFile(Path filePath) {
        try {
            return Files.lines(filePath)
                    .skip(6)
                    .map(x -> lineFormatter.format(x));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
