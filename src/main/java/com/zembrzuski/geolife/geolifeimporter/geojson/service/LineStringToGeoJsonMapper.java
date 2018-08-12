package com.zembrzuski.geolife.geolifeimporter.geojson.service;

import com.zembrzuski.geolife.geolifeimporter.geojson.entity.LatLongPoint;
import com.zembrzuski.geolife.geolifeimporter.geojson.entity.LineStringGeoJson;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class LineStringToGeoJsonMapper {

    ObjectMapper mapper = new ObjectMapper();

    public String pointToGeoJson(List<LatLongPoint> points) throws IOException {
        return mapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(new LineStringGeoJson(points));

    }

}
