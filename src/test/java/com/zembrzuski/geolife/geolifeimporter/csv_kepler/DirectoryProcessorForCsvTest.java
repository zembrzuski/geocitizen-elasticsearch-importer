package com.zembrzuski.geolife.geolifeimporter.csv_kepler;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class DirectoryProcessorForCsvTest {

    @Mock
    BeijingTimeZoneAdapter adapter;

    @InjectMocks
    DirectoryProcessorForCsv directoryProcessorForCsv;

    @Test
    public void toBeijingDateTest() {
        Mockito.when(adapter.fromGmtToBeijingDate("2018-01-01T00:59:31Z")).thenReturn("2018-01-01T08:59:31Z");
        String input = "39.999694,116.326063,2018-01-01T00:59:31Z";
        String expected = "39.999694,116.326063,2018-01-01T08:59:31Z";
        String result = directoryProcessorForCsv.toBeijingDate(input);
        assertEquals(result, expected);
    }

}