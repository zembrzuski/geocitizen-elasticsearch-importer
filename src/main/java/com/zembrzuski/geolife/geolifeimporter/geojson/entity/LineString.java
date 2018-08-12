package com.zembrzuski.geolife.geolifeimporter.geojson.entity;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
public class LineString {
    private final String type = "LineString";
    private final List<List<Float>> coordinates;

    public LineString(List<LatLongPoint> points) {
        this.coordinates = points
                .stream()
                .map(p -> Lists.newArrayList(p.getLng(), p.getLat()))
                .collect(Collectors.toList());
    }
}
