package com.zembrzuski.geolife.geolifeimporter.geojson.entity;

import lombok.Data;

import java.util.List;

@Data
public class LineStringFeature {

    private final String type = "Feature";
    private final LineString geometry;

    public LineStringFeature(List<LatLongPoint> points) {
        this.geometry = new LineString(points);
    }
}

