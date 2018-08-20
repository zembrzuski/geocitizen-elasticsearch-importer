package com.zembrzuski.geolife.geolifeimporter.csv_kepler.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeolocationPoint {

    private BigDecimal latitude;
    private BigDecimal longitute;
    private DateTime timestamp;
    private String transportationMode;

}
