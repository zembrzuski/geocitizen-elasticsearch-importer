package com.zembrzuski.geolife.geolifeimporter.csv_kepler;

import com.zembrzuski.geolife.geolifeimporter.csv_kepler.entity.GeolocationPoint;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import static org.junit.Assert.*;

public class CsvSerializerTest {

    @Test
    public void simpleSerialization() {
        GeolocationPoint geolocationPointBeijing = GeolocationPoint.builder()
                .latitude(39.999694F)
                .longitute(116.326063F)
                .timestamp(new DateTime("2018-01-01T00:59:31.000Z",  DateTimeZone.forTimeZone(TimeZone.getTimeZone("Asia/Shanghai"))))
                .build();

        String result = new CsvSerializer().serialize(geolocationPointBeijing);

        String expected = "39.999694,116.326063,2018-01-01T08:59:31.000Z";

        assertEquals(result, expected);
    }
}