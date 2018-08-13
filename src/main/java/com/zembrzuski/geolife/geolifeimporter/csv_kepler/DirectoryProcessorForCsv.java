package com.zembrzuski.geolife.geolifeimporter.csv_kepler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class DirectoryProcessorForCsv {

    @Autowired
    private SingleFileProcessor singleFileProcessor;

    @Autowired
    private SingleDayConverter singleDayConverter;

    public List<String> readDirectory(String directoryPath) throws IOException {
        Stream<String> header = Stream.of("latitude,longitude,timestamp");

        Stream<String> body = Files
                .walk(Paths.get(directoryPath))
                .filter(x -> x.toFile().isFile())
                .flatMap(x -> singleFileProcessor.readFile(x))
                .map(x -> singleDayConverter.convert(x))
                ;

        return Stream.concat(header, body)
                .collect(Collectors.toList());
    }

}
