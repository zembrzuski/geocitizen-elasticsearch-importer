package com.zembrzuski.geolife.geolifeimporter.csv_kepler;

import org.junit.Test;

import static org.junit.Assert.*;

public class LineFormatterTest {

    @Test
    public void simpleTest() {
        String input = "39.984702,116.318417,0,492,39744.1201851852,2008-10-23,02:53:04";
        String expected = "39.984702,116.318417,2008-10-23 02:53:04";

        String output = new LineFormatter().format(input);

        assertEquals(output, expected);
    }
}