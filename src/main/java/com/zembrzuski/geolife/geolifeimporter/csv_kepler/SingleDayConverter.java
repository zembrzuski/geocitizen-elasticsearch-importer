package com.zembrzuski.geolife.geolifeimporter.csv_kepler;

import com.zembrzuski.geolife.geolifeimporter.csv_kepler.entity.GeolocationPoint;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SingleDayConverter {

    public GeolocationPoint convert(String inp) {
        String[] split = inp.split(",");

        return GeolocationPoint.builder()
                .latitude(new BigDecimal(split[0]))
                .longitute(new BigDecimal(split[1]))
                .timestamp(dateConverter(split[2]))
                .build();
    }

    DateTime dateConverter(String inpt) {
        return new DateTime(inpt,  DateTimeZone.UTC);
    }

}
