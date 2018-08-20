package com.zembrzuski.geolife.geolifeimporter.csv_kepler;

import com.zembrzuski.geolife.geolifeimporter.csv_kepler.entity.GeolocationPoint;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class SingleDayConverter {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public GeolocationPoint convert(String inp) {
        String[] split = inp.split(",");

        return GeolocationPoint.builder()
                .latitude(Float.valueOf(split[0]))
                .longitute(Float.valueOf(split[1]))
                .timestamp(dateConverter(split[2]))
                .build();
    }

    Date dateConverter(String inpt) {
        try {
            return sdf.parse(inpt);
        } catch (ParseException e) {
            throw new IllegalArgumentException("could not convert date");
        }
    }

}
