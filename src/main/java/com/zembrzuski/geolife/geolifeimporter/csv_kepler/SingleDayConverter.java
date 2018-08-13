package com.zembrzuski.geolife.geolifeimporter.csv_kepler;

import org.springframework.stereotype.Component;

@Component
public class SingleDayConverter {

    public String convert(String inp) {
        String[] split = inp.split(",");

        return split[0] + "," +split[1] + "," + dateConverter(split[2]);
    }

    public String dateConverter(String inp) {
        return "2018-01-01T" + inp.substring(11);
    }

}
