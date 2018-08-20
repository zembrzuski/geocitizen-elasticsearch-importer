package com.zembrzuski.geolife.geolifeimporter.csv_kepler;

import com.zembrzuski.geolife.geolifeimporter.csv_kepler.entity.GeolocationPoint;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

@Component
public class CsvSerializer {

    private static DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

    public String serialize(GeolocationPoint x) {
        String formatted1 = fmt.print(x.getTimestamp());
        String formatted2 = formatted1.replaceAll(" ", "T");
        String formatted3 = formatted2 + ".000Z";

        return x.getLatitude() + "," + x.getLongitute() + "," + formatted3;
    }

}
