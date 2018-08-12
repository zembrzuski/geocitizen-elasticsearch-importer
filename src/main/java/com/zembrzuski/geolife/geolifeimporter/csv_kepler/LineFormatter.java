package com.zembrzuski.geolife.geolifeimporter.csv_kepler;

import org.springframework.stereotype.Component;

@Component
public class LineFormatter {

    public String format(String line) {
        String[] split = line.split(",");
        String date = split[5];
        String time = split[6];

        String datetime = date + "T" + time + "Z";

        return split[0] + "," + split[1] + "," + datetime;
    }

}
