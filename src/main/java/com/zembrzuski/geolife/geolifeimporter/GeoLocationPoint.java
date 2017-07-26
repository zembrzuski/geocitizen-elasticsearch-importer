package com.zembrzuski.geolife.geolifeimporter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Corresponde a uma localizacao geoespacial enviado para o servidor.
 */
public class GeoLocationPoint {

    private Float lat;
    private Float lng;
    private LocalDateTime timestamp;
    private TransportationMode mode;

    /**
     * Construtor simplao.
     */
    public GeoLocationPoint(Float lat, Float lng, LocalDateTime timestamp, TransportationMode mode) {
        this.lat = lat;
        this.lng = lng;
        this.timestamp = timestamp;
        this.mode = mode;
    }

    /**
     * Cria um GeoLocationPoint dado uma linha de uma trajetoria do Geolife.
     */
    public GeoLocationPoint(String input) {
        String[] splitted = input.split(",");

        this.lat = Float.parseFloat(splitted[0]);
        this.lng = Float.parseFloat(splitted[1]);
        this.timestamp = toTimestamp(splitted[5] + " " + splitted[6]);
    }

    /**
     * Retirar esse cara daqui.
     * Usar o helper.
     */
    private LocalDateTime toTimestamp(String s) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return LocalDateTime.parse(s, dateTimeFormatter);
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLng() {
        return lng;
    }

    public void setLng(Float lng) {
        this.lng = lng;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public TransportationMode getMode() {
        return mode;
    }

    public void setMode(TransportationMode mode) {
        this.mode = mode;
    }

    @Override
    public String toString() {
        return "timestamp=" + timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GeoLocationPoint that = (GeoLocationPoint) o;

        if (lat != null ? !lat.equals(that.lat) : that.lat != null) return false;
        if (lng != null ? !lng.equals(that.lng) : that.lng != null) return false;
        if (timestamp != null ? !timestamp.equals(that.timestamp) : that.timestamp != null) return false;
        return mode == that.mode;
    }

    @Override
    public int hashCode() {
        int result = lat != null ? lat.hashCode() : 0;
        result = 31 * result + (lng != null ? lng.hashCode() : 0);
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        result = 31 * result + (mode != null ? mode.hashCode() : 0);
        return result;
    }
}
