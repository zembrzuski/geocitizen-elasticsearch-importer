package com.zembrzuski.geolife.geolifeimporter.csv_kepler;

import com.zembrzuski.geolife.geolifeimporter.csv_kepler.entity.GeolocationPoint;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.TimeZone;
import java.util.stream.Stream;

import static org.junit.Assert.assertArrayEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DirectoryProcessorForCsvTest {

    @Mock
    private CsvSerializer csvSerializer;

    @Mock
    private SingleDayConverter singleDayConverter;

    @Mock
    private BeijingConverter beijingConverter;

    @InjectMocks
    private DirectoryProcessorForCsv directoryProcessorForCsv;

    @Test
    public void testConvertion() {
        String inputString = "39.999694,116.326063,2018-01-01T00:59:31.000Z";
        Stream<String> inputStream = Stream.of(inputString);
        String[] expected = {"39.999694,116.326063,2018-01-08T00:59:31.000Z"};

        GeolocationPoint geolocationPoint = GeolocationPoint.builder()
                .latitude(new BigDecimal("39.9888083"))
                .longitute(new BigDecimal("116.3064916"))
                .timestamp(new DateTime("2018-01-01T00:59:31.000Z",  DateTimeZone.UTC))
                .build();

        GeolocationPoint geolocationPointBeijing = GeolocationPoint.builder()
                .latitude(new BigDecimal("39.9888083"))
                .longitute(new BigDecimal("116.3064916"))
                .timestamp(new DateTime("2018-01-01T00:59:31.000Z",  DateTimeZone.forTimeZone(TimeZone.getTimeZone("Asia/Shanghai"))))
                .build();

        when(singleDayConverter.convert(inputString)).thenReturn(geolocationPoint);
        when(beijingConverter.toBeijingTimezone(geolocationPoint)).thenReturn(geolocationPointBeijing);
        when(csvSerializer.serialize(geolocationPointBeijing)).thenReturn(expected[0]);

        Stream<String> result = directoryProcessorForCsv.filesContentConverter(inputStream);

        assertArrayEquals(result.toArray(), expected);
    }
}