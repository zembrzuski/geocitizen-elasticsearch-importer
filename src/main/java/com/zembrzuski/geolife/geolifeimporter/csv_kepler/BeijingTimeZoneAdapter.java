package com.zembrzuski.geolife.geolifeimporter.csv_kepler;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

@Component
public class BeijingTimeZoneAdapter {

    public String fromGmtToBrazilDate(String inp) {
        String dateInString = inp.replace("T", " ").replace("Z", "");
        LocalDateTime ldt = LocalDateTime.parse(dateInString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        ZonedDateTime spDateTime = ldt.atZone(ZoneId.of("UTC"));
        Date from = Date.from(spDateTime.toInstant());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));

        String format = sdf.format(from);

        return format.substring(0, 10) + "T" + format.substring(11) + "Z";
    }

    public String fromGmtToBeijingDate(String inp) {
        String dateInString = inp.replace("T", " ").replace("Z", "");
        LocalDateTime ldt = LocalDateTime.parse(dateInString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        ZonedDateTime spDateTime = ldt.atZone(ZoneId.of("UTC"));
        Date from = Date.from(spDateTime.toInstant());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));

        String format = sdf.format(from);

        return format.substring(0, 10) + "T" + format.substring(11) + "Z";
    }

}
