package com.zembrzuski.geolife.geolifeimporter.csv_kepler;

import com.zembrzuski.geolife.geolifeimporter.csv_kepler.entity.GeolocationPoint;
import org.junit.Test;

import java.time.Month;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class SingleDayConverterTest {

    @Test
    public void simpleConversion() {
        String input = "39.9888083,116.3064916,2008-11-04 06:36:22";
        GeolocationPoint result = new SingleDayConverter().convert(input);

        Calendar expectedDate = Calendar.getInstance();
        expectedDate.set(Calendar.MILLISECOND, 0);
        expectedDate.set(2008, 10, 4, 6, 36, 22);

        GeolocationPoint expected = new GeolocationPoint(
                39.9888083F,
                116.3064916F,
                expectedDate.getTime(),
                null
        );

        assertEquals(result, expected);
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidConversion() {
        String input = "39.9888083,116.3064916,2008-11-04T06:36:22";
        new SingleDayConverter().convert(input);
    }

    @Test
    public void testDateConversion() {
        String inpt = "2008-11-04 06:36:22";
        Date result = new SingleDayConverter().dateConverter(inpt);

        Calendar expectedDate = Calendar.getInstance();
        expectedDate.set(2008, 10, 4, 6, 36, 22);
        expectedDate.set(Calendar.MILLISECOND, 0);

        assertEquals(expectedDate.getTime(), result);
    }

}