package com.zembrzuski.geolife.geolifeimporter.geojson.entity;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

@Data
public class PointGeoJson {

    private final String type = "FeatureCollection";
    private final List<PointFeature> features;

    public PointGeoJson(float lat, float lon) {
        this.features = Lists.newArrayList(new PointFeature(lat, lon));
    }

}


