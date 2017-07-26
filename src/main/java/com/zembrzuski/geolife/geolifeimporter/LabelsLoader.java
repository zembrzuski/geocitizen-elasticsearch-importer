package com.zembrzuski.geolife.geolifeimporter;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Carrega os labels do filesystem e converte elas para TrajectoryLabel.
 */
@Component
public class LabelsLoader {

    @Autowired
    private FileReader fileReader;

    public List<TrajectoryLabel> load(String labelsPath) throws IOException {

        if (!new File(labelsPath).exists()) {
            return Lists.newArrayList();
        }

        return fileReader
                .doRead(labelsPath)
                .stream()
                .skip(1)
                .map(TrajectoryLabel::new)
                .collect(Collectors.toList());
    }

}
