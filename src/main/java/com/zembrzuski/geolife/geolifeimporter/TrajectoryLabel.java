package com.zembrzuski.geolife.geolifeimporter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Label do Geolife.
 */
public class TrajectoryLabel {

    private LocalDateTime start;
    private LocalDateTime end;
    private TransportationMode mode;

    public TrajectoryLabel(String inp) {
        String[] splitted = inp.split("\t");

        this.start = toTimestamp(splitted[0]);
        this.end = toTimestamp(splitted[1]);
        this.mode = TransportationMode.fromString(splitted[2]);
    }


    /**
     * Retirar esse cara daqui. Usar o helper.
     */
    private LocalDateTime toTimestamp(String s) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

        return LocalDateTime.parse(s, dateTimeFormatter);
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public TransportationMode getMode() {
        return mode;
    }

    public void setMode(TransportationMode mode) {
        this.mode = mode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrajectoryLabel that = (TrajectoryLabel) o;

        if (start != null ? !start.equals(that.start) : that.start != null) return false;
        if (end != null ? !end.equals(that.end) : that.end != null) return false;
        return mode != null ? mode.equals(that.mode) : that.mode == null;
    }

    @Override
    public int hashCode() {
        int result = start != null ? start.hashCode() : 0;
        result = 31 * result + (end != null ? end.hashCode() : 0);
        result = 31 * result + (mode != null ? mode.hashCode() : 0);
        return result;
    }
}
