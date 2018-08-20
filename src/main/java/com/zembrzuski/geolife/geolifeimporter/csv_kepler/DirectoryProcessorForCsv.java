package com.zembrzuski.geolife.geolifeimporter.csv_kepler;

import com.zembrzuski.geolife.geolifeimporter.csv_kepler.entity.GeolocationPoint;
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

    @Autowired
    private BeijingConverter beijingConverter;

    @Autowired
    private CsvSerializer csvSerializer;

    public List<String> readDirectory(String directoryPath) throws IOException {
        Stream<String> header = Stream.of("latitude,longitude,timestamp");

        Stream<String> filesContent = Files
                .walk(Paths.get(directoryPath))
                .filter(x -> x.toFile().isFile())
                .flatMap(x -> singleFileProcessor.readFile(x));

        Stream<String> body = filesContentConverter(filesContent);

        return Stream.concat(header, body)
                .collect(Collectors.toList());
    }

    Stream<String> filesContentConverter(Stream<String> filesContent) {
        return filesContent
                .map(x -> singleDayConverter.convert(x))
                .map(x -> beijingConverter.toBeijingTimezone(x))
                .map(x -> csvSerializer.serialize(x));
    }

}
