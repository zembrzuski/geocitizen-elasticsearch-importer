package com.zembrzuski.geolife.geolifeimporter.entity;


import com.google.common.collect.Lists;

import java.util.List;

public class Point {

    private final List<Float> coordinates;
    private final String type = "point";

    public Point(Float lat, Float longitude) {
        this.coordinates = Lists.newArrayList(longitude, lat);
    }

    public List<Float> getCoordinates() {
        return coordinates;
    }

    public String getType() {
        return type;
    }

    public Float getLat() {
        return this.coordinates.get(1);
    }

    public Float getLng() {
        return this.coordinates.get(0);
    }
}
