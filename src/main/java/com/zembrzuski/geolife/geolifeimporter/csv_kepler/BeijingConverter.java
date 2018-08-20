package com.zembrzuski.geolife.geolifeimporter.csv_kepler;

import com.zembrzuski.geolife.geolifeimporter.csv_kepler.entity.GeolocationPoint;
import org.springframework.beans.factory.annotation.Autowired;

public class BeijingConverter {

    @Autowired
    private BeijingTimeZoneAdapterStringuera timeZoneAdapter;

    public String toBeijingTimezone(GeolocationPoint inpt) {
        throw new IllegalArgumentException("nonono");
    }

}
