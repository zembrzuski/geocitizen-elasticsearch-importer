package com.zembrzuski.geolife.geolifeimporter;

import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class GeolifeImporterApplication implements CommandLineRunner {

	private static final String FILE_PATH = "/home/zembrzuski/labs/geolife/Data/010/Trajectory/20081101042408.plt";
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

		// it would be much better if I had collected it already sorted.
        Map<GeoLocationPoint, TrajectoryLabel> x = fileReader
				.doRead(FILE_PATH)
				.stream()
				.skip(6)
				.map(GeoLocationPoint::new)
				.collect(Collectors.toMap(
				        o -> o,
                        o -> doTheFilter(someLabels.stream(), o))
				);

        TreeMap<GeoLocationPoint, TrajectoryLabel> sorted =
                Maps.newTreeMap((o1, o2) -> o1.getTimestamp().isBefore(o2.getTimestamp())? -1 : 1);

        sorted.putAll(x);

		for(GeoLocationPoint p: sorted.keySet()) {
		    if (x.get(p).getMode() != null) {
                System.out.println(p);
                System.out.println(x.get(p));
                System.out.println();
            }
        }

	}

	private TrajectoryLabel doTheFilter(Stream<TrajectoryLabel> someLabels, GeoLocationPoint o) {
		return someLabels
				.filter(trajectoryLabel ->
                        o.getTimestamp().isEqual(trajectoryLabel.getStart())
                                || o.getTimestamp().isEqual(trajectoryLabel.getEnd())
                                || (o.getTimestamp().isAfter(trajectoryLabel.getStart()) && o.getTimestamp().isBefore(trajectoryLabel.getEnd()))
                )
				.findFirst()
				.orElseGet(TrajectoryLabel::new);
	}
}
