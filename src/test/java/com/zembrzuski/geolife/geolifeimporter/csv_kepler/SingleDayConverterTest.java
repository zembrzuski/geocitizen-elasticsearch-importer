package com.zembrzuski.geolife.geolifeimporter.csv_kepler;

import org.junit.Test;

import static org.junit.Assert.*;

public class SingleDayConverterTest {

    @Test
    public void test1() {
        String inp = "2008-10-23T02:53:04Z";
        String out = new SingleDayConverter().dateConverter(inp);
        String expected = "2018-01-01T02:53:04Z";
        assertEquals(out, expected);
    }

    @Test
    public void test2() {
        String inp = "2020-12-11T14:23:14Z";
        String out = new SingleDayConverter().dateConverter(inp);
        String expected = "2018-01-01T14:23:14Z";
        assertEquals(out, expected);
    }

}