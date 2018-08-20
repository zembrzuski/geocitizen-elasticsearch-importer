package com.zembrzuski.geolife.geolifeimporter.csv_kepler;

import com.zembrzuski.geolife.geolifeimporter.csv_kepler.entity.GeolocationPoint;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.TimeZone;

public class BeijingConverter {

//    @Autowired
//    private BeijingTimeZoneAdapterStringuera timeZoneAdapter;

    public GeolocationPoint toBeijingTimezone(GeolocationPoint inpt) {
        DateTime timestamp = new DateTime(
                inpt.getTimestamp(),
                DateTimeZone.forTimeZone(TimeZone.getTimeZone("Asia/Shanghai")));

        return GeolocationPoint.builder()
                .latitude(inpt.getLatitude())
                .longitute(inpt.getLongitute())
                .transportationMode(inpt.getTransportationMode())
                .timestamp(timestamp)
                .build();
    }

}
