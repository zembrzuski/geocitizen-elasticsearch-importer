package com.zembrzuski.geolife.geolifeimporter.csv_kepler;

import com.zembrzuski.geolife.geolifeimporter.csv_kepler.entity.GeolocationPoint;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Months;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class SingleDayConverterTest {

    @Test
    public void simpleConversion() {
        String input = "39.9888083,116.3064916,2008-11-04T06:36:22.000Z";
        GeolocationPoint result = new SingleDayConverter().convert(input);

        DateTime expectedDate = new DateTime()
                .withZone(DateTimeZone.UTC)
                .withDayOfMonth(4)
                .withMonthOfYear(11)
                .withYear(2008)
                .withTime(6,36,22,  0);


        GeolocationPoint expected = new GeolocationPoint(
                new BigDecimal("39.9888083"),
                new BigDecimal("116.3064916"),
                expectedDate,
                null
        );

        assertEquals(result, expected);
    }

    @Test
    public void testDateConversion() {
        String inpt = "2008-11-04T06:36:22.000Z";
        DateTime result = new SingleDayConverter().dateConverter(inpt);

        DateTime expectedDate = new DateTime()
            .withZone(DateTimeZone.UTC)
            .withDayOfMonth(4)
            .withMonthOfYear(11)
            .withYear(2008)
            .withTime(6,36,22,  0);

        assertEquals(expectedDate, result);
    }

}