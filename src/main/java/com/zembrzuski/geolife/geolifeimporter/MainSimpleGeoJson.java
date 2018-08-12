package com.zembrzuski.geolife.geolifeimporter;

import com.google.common.collect.Lists;
import com.zembrzuski.geolife.geolifeimporter.csv_kepler.SingleFileProcessor;
import com.zembrzuski.geolife.geolifeimporter.geojson.entity.LatLongPoint;
import com.zembrzuski.geolife.geolifeimporter.geojson.service.LineStringToGeoJsonMapper;
import com.zembrzuski.geolife.geolifeimporter.geojson.service.PointToGeoJsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class MainSimpleGeoJson implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MainSimpleGeoJson.class, args);
    }

    @Autowired
    private PointToGeoJsonMapper pointToGeoJsonMapper;

    @Autowired
    private LineStringToGeoJsonMapper lineStringToGeoJsonMapper;

    @Autowired
    private SingleFileProcessor singleFileProcessor;

    @Override
    public void run(String... strings) throws IOException {
        //System.out.println(pointToGeoJsonMapper.pointToGeoJson(30.0346F, 51.2177F));

//        List<LatLongPoint> points = Lists.newArrayList(
//                new LatLongPoint(-30.0346F, -51.2177F),
//                new LatLongPoint(-30.8494F, -51.8048F)
//        );
//
//        System.out.println(lineStringToGeoJsonMapper.pointToGeoJson(points));

        System.out.println("---");
        singleFileProcessor.readFile();
    }

}
