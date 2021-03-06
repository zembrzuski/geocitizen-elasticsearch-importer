package com.zembrzuski.geolife.geolifeimporter;

import com.google.common.collect.Maps;
import com.zembrzuski.geolife.geolifeimporter.entity.Path;
import com.zembrzuski.geolife.geolifeimporter.entity.TrajectoryLabel;
import com.zembrzuski.geolife.geolifeimporter.helpers.FileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Enriquece uma trajetoria com seus respectivos labels.
 */
@Component
public class TrajectoryEnricher {

    @Autowired
    private FileReader fileReader;

    public TreeMap<Path, TrajectoryLabel> enrichTrajectory(
            String filePath, List<TrajectoryLabel> someLabels) throws IOException {

        // TODO it would be much better if I had collected it already sorted.

        Map<Path, TrajectoryLabel> x = fileReader
                .doRead(filePath)
                .stream()
                .skip(6)
                .map(Path::new)
                .collect(Collectors.toMap(
                        o -> o,
                        o -> doTheFilter(someLabels.stream(), o),
                        (a, b) -> a)
                );

        TreeMap<Path, TrajectoryLabel> sorted =
                Maps.newTreeMap((o1, o2) -> o1.getTimestamp().isBefore(o2.getTimestamp()) ? -1 : 1);

        sorted.putAll(x);

        return sorted;
    }

    private TrajectoryLabel doTheFilter(Stream<TrajectoryLabel> someLabels, Path o) {
        return someLabels
                .filter(trajectoryLabel ->
                        o.getTimestamp().isEqual(trajectoryLabel.getStart())
                                || o.getTimestamp().isEqual(trajectoryLabel.getEnd())
                                || (o.getTimestamp().isAfter(trajectoryLabel.getStart()) && o.getTimestamp().isBefore(trajectoryLabel.getEnd()))
                )
                .findFirst()
                .orElseGet(TrajectoryLabel::new);
    }

}
