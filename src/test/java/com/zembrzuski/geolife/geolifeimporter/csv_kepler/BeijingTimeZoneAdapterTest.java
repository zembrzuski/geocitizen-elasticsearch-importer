package com.zembrzuski.geolife.geolifeimporter.csv_kepler;

import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.*;

public class BeijingTimeZoneAdapterTest {

    @Test
    public void fromUtcToBeijing() {
        String utcDate = "2018-08-13T23:01:04Z";
        String beijingDate = "2018-08-14T07:01:04Z";

        String output = new BeijingTimeZoneAdapter().fromGmtToBeijingDate(utcDate);

        assertEquals(beijingDate, output);
    }

    @Test
    public void fromUtcToSaoPaulo() throws ParseException {
        String utcDate = "2018-08-13T23:01:04Z";
        String brazilDate = "2018-08-13T20:01:04Z";

        String output = new BeijingTimeZoneAdapter().fromGmtToBrazilDate(utcDate);

        assertEquals(brazilDate, output);
    }

}