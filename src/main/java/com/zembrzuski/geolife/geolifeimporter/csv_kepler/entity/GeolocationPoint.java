package com.zembrzuski.geolife.geolifeimporter.csv_kepler.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeolocationPoint {

    private float latitude;
    private float longitute;
    private DateTime timestamp;
    private String transportationMode;

}
