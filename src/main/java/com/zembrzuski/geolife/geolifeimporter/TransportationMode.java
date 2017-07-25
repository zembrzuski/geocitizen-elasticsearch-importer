package com.zembrzuski.geolife.geolifeimporter;

/**
 * Tipos de transporte possiveis.
 */
public enum TransportationMode {
    BUS("bus"),
    TRAIN("train"),
    TAXI("taxi"),
    WALK("walk"),
    SUBWAY("subway"),
    AIRPLANE("airplane"),
    CAR("car");

    private final String text;

    TransportationMode(String text) {
        this.text = text;
    }

    /**
     * Esse methodo poderia ser muito melhor.
     */
    public static TransportationMode fromString(String inp) {
        for(TransportationMode mode: TransportationMode.values()) {
            if(mode.text.equalsIgnoreCase(inp)) {
                return mode;
            }
        }
        throw new IllegalArgumentException("Could not create transportation mode from: " + inp);
    }


}
