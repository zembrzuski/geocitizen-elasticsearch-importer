package com.zembrzuski.geolife.geolifeimporter.geojson.entity;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

@Data
public class Point {
    private final String type = "Point";
    private final List<Float> coordinates;

    public Point(float lat, float lon) {
        this.coordinates = Lists.newArrayList(lon, lat);
    }
}
