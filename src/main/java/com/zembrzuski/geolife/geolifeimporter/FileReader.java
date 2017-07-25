package com.zembrzuski.geolife.geolifeimporter;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Read a text file as a List of strings
 */
@Component
public class FileReader {

    public List<String> doRead(String filePath) throws IOException {
        List<String> lines = Lists.newArrayList();

        for (String line : Files.readAllLines(Paths.get(filePath))) {
            lines.add(line);
        }

        return lines;
    }

}
