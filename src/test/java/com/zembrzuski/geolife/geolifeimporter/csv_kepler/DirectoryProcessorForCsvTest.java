package com.zembrzuski.geolife.geolifeimporter.csv_kepler;

import com.google.common.collect.Lists;
import com.zembrzuski.geolife.geolifeimporter.csv_kepler.entity.GeolocationPoint;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DirectoryProcessorForCsvTest {

//    @Mock
//    BeijingTimeZoneAdapterStringuera adapter;
//

    @Mock
    SingleDayConverter singleDayConverter;

    @Mock
    BeijingConverter beijingConverter;

    @InjectMocks
    DirectoryProcessorForCsv directoryProcessorForCsv;
//
//    @Test
//    public void toBeijingDateTest() {
//        Mockito.when(adapter.fromGmtToBeijingDate("2018-01-01T00:59:31Z")).thenReturn("2018-01-01T08:59:31Z");
//        String input = "39.999694,116.326063,2018-01-01T00:59:31Z";
//        String expected = "39.999694,116.326063,2018-01-01T08:59:31Z";
//        String result = directoryProcessorForCsv.toBeijingDate(input);
//        assertEquals(result, expected);
//    }


    @Test
    public void testConvertion() {
        String inputString = "39.999694,116.326063,2018-01-01T00:59:31.000Z";
        Stream<String> inputStream = Stream.of(inputString);
        String[] expected = {"39.999694,116.326063,2018-01-08T00:59:31.000Z"};

        GeolocationPoint geolocationPoint = GeolocationPoint.builder()
                .latitude(39.999694F)
                .longitute(116.326063F)
                .timestamp(new DateTime("2018-01-01T00:59:31.000Z",  DateTimeZone.UTC))
                .build();

        GeolocationPoint geolocationPointBeijing = GeolocationPoint.builder()
                .latitude(39.999694F)
                .longitute(116.326063F)
                .timestamp(new DateTime("2018-01-01T00:59:31.000Z",  DateTimeZone.forTimeZone(TimeZone.getTimeZone("Asia/Shanghai"))))
                .build();

        when(singleDayConverter.convert(inputString)).thenReturn(geolocationPoint);
        when(beijingConverter.toBeijingTimezone(geolocationPoint)).thenReturn(geolocationPointBeijing);

        Stream<String> result = directoryProcessorForCsv.filesContentConverter(inputStream);

        assertArrayEquals(result.toArray(), expected);
    }
}