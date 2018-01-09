package com.zembrzuski.geolife.geolifeimporter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.TreeMap;

@SpringBootApplication
public class GeolifeImporterApplication implements CommandLineRunner {

    @Autowired
    private DirectoryProcessor directoryProcessor;

    public static void main(String[] args) {
        SpringApplication.run(GeolifeImporterApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {

        /**
         * ATENÇÃO.
         *
         * Não esqueça de criar o indice com o mapeamento correto no elasticsearch
         * antes de fazer o import.
         *
         * Veja o mapeamento correto em elasticsearch_info.txt
         */

        for(int i = 1 ; i <= 181 ; i++) {
            String formatted = String.format("%03d", i);
            directoryProcessor.processDirectory(formatted);
        }

    }


}
