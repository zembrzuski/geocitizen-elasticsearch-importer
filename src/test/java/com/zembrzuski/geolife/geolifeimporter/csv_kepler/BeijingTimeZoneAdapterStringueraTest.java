package com.zembrzuski.geolife.geolifeimporter.csv_kepler;

import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.*;

public class BeijingTimeZoneAdapterStringueraTest {

    @Test
    public void fromUtcToBeijing() {
        String utcDate = "2018-08-13T23:01:04Z";
        String beijingDate = "2018-08-14T07:01:04Z";

        String output = new BeijingTimeZoneAdapterStringuera().fromGmtToBeijingDate(utcDate);

        assertEquals(beijingDate, output);
    }

    @Test
    public void fromUtcToSaoPaulo() throws ParseException {
        String utcDate = "2018-08-13T23:01:04Z";
        String brazilDate = "2018-08-13T20:01:04Z";

        String output = new BeijingTimeZoneAdapterStringuera().fromGmtToBrazilDate(utcDate);

        assertEquals(brazilDate, output);
    }

}