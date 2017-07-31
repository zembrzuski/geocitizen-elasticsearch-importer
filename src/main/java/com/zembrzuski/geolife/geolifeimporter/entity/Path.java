package com.zembrzuski.geolife.geolifeimporter.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Corresponde a uma localizacao geoespacial enviado para o servidor.
 */
public class Path {

    private Point point;
    private LocalDateTime timestamp;
    private TransportationMode mode;

    /**
     * Construtor simplao.
     */
    public Path(Float lat, Float lng, LocalDateTime timestamp, TransportationMode mode) {
        this.point = new Point(lat, lng);
        this.timestamp = timestamp;
        this.mode = mode;
    }

    /**
     * Cria um Path dado uma linha de uma trajetoria do Geolife.
     */
    public Path(String input) {
        String[] splitted = input.split(",");
        this.point = new Point(Float.parseFloat(splitted[0]), Float.parseFloat(splitted[1]));
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

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Path that = (Path) o;

        if (point != null ? !point.equals(that.point) : that.point != null) return false;
        if (timestamp != null ? !timestamp.equals(that.timestamp) : that.timestamp != null) return false;
        return mode == that.mode;
    }

    @Override
    public int hashCode() {
        int result = point != null ? point.hashCode() : 0;
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        result = 31 * result + (mode != null ? mode.hashCode() : 0);
        return result;
    }

    public Float getLat() {
        return this.point.getLat();
    }

    public Float getLng() {
        return this.point.getLng();
    }
}
