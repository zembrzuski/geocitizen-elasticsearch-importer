package com.zembrzuski.geolife.geolifeimporter.csv_kepler.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeolocationPoint {

    private float latitude;
    private float longitute;
    private Date timestamp;
    private String transportationMode;

}
