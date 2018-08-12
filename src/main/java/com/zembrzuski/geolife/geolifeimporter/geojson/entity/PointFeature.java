package com.zembrzuski.geolife.geolifeimporter.geojson.entity;

import lombok.Data;

@Data
public class PointFeature {
    private final String type = "Feature";
    private final Point geometry;

    public PointFeature(float lat, float lon) {
        this.geometry = new Point(lat, lon);
    }
}

