package com.zembrzuski.geolife.geolifeimporter.geojson.entity;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

@Data
public class LineStringGeoJson {

    private final String type = "FeatureCollection";
    private final List<LineStringFeature> features;

    public LineStringGeoJson(List<LatLongPoint> points) {
        this.features = Lists.newArrayList(new LineStringFeature(points));
    }

}
