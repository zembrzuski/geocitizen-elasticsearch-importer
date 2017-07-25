package com.zembrzuski.geolife.geolifeimporter;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Metodos para manipular datas.
 */
@Component
public class DateTimeHelpers {

    /**
     * Cria LocalDateTime a partir de strings do tipo 2017-01-23 23:23:11
     * Nao leva em conta fuso horario.
     */
    public LocalDateTime toTimestamp(String s) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return LocalDateTime.parse(s, dateTimeFormatter);
    }

}
