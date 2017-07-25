package com.zembrzuski.geolife.geolifeimporter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class GeolifeImporterApplication implements CommandLineRunner {

	private static final String FILE_PATH = "/home/zembrzuski/labs/geolife/Data/000/Trajectory/20081023025304.plt";
	private static final String LABELS_PATH = "/home/zembrzuski/labs/geolife/Data/010/labels.txt";

	@Autowired
	private FileReader fileReader;

	public static void main(String[] args) {
		SpringApplication.run(GeolifeImporterApplication.class, args);
	}


	@Override
	public void run(String... strings) throws Exception {

		List<TrajectoryLabel> someLabels = fileReader
				.doRead(LABELS_PATH)
				.stream()
				.skip(1)
				.map(TrajectoryLabel::new)
				.collect(Collectors.toList());

		List<GeoLocationPoint> aTrajectory = fileReader
				.doRead(FILE_PATH)
				.stream()
				.skip(6)
				.map(GeoLocationPoint::new)
				.collect(Collectors.toList());
//
//		for(String s : trajectory) {
//			System.out.println(s);
//		}

	}
}
