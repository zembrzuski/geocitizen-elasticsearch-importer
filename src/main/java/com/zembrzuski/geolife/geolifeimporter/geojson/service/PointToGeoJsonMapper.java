package com.zembrzuski.geolife.geolifeimporter.geojson.service;

import com.zembrzuski.geolife.geolifeimporter.geojson.entity.PointGeoJson;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PointToGeoJsonMapper {

    ObjectMapper mapper = new ObjectMapper();

    public String pointToGeoJson(float lat, float lon) throws IOException {
        return mapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(new PointGeoJson(lat, lon));

    }

}
