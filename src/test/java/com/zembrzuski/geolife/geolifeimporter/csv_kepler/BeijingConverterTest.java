package com.zembrzuski.geolife.geolifeimporter.csv_kepler;

import com.zembrzuski.geolife.geolifeimporter.csv_kepler.entity.GeolocationPoint;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Test;

import java.util.TimeZone;

import static org.junit.Assert.*;

public class BeijingConverterTest {

    @Test
    public void fromGmtToBeijingDatetime() {
        DateTime datetimeInput = new DateTime("2018-02-02T23:31:17.003Z", DateTimeZone.UTC);
        DateTime dateTimeOutpt = new DateTime("2018-02-02T23:31:17.003Z", DateTimeZone.forTimeZone(TimeZone.getTimeZone("Asia/Shanghai")));

        GeolocationPoint inpt = GeolocationPoint.builder()
                .latitude(32.1F)
                .longitute(11.3F)
                .transportationMode("a")
                .timestamp(datetimeInput)
                .build();

        GeolocationPoint expected = GeolocationPoint.builder()
                .latitude(32.1F)
                .longitute(11.3F)
                .transportationMode("a")
                .timestamp(dateTimeOutpt)
                .build();

        GeolocationPoint result = new BeijingConverter().toBeijingTimezone(inpt);

        assertEquals(expected, result);
    }
}