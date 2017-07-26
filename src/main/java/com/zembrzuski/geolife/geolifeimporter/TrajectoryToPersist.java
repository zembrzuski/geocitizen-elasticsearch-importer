package com.zembrzuski.geolife.geolifeimporter;

import com.google.common.collect.Lists;
import org.codehaus.jackson.annotate.JsonWriteNullProperties;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Entidade que vai virar um json para persistir no elastic-search.
 */

public class TrajectoryToPersist {

    private String userId;
    private List<GeoLocationPoint> geoLocationPoints;

    public TrajectoryToPersist(String userId, TreeMap<GeoLocationPoint, TrajectoryLabel> geoLocationPointTrajectoryLabelTreeMap) {
        this.userId = userId;
        this.geoLocationPoints = fromTreeMap(geoLocationPointTrajectoryLabelTreeMap);
    }

    private List<GeoLocationPoint> fromTreeMap(TreeMap<GeoLocationPoint, TrajectoryLabel> geoLocationPointTrajectoryLabelTreeMap) {
        List<GeoLocationPoint> x = Lists.newArrayList();

        for(Map.Entry<GeoLocationPoint, TrajectoryLabel> entry: geoLocationPointTrajectoryLabelTreeMap.entrySet()) {
            GeoLocationPoint key = entry.getKey();
            TrajectoryLabel value = entry.getValue();

            x.add(new GeoLocationPoint(key.getLat(), key.getLng(), key.getTimestamp(), value.getMode()));
        }

        return x;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<GeoLocationPoint> getGeoLocationPoints() {
        return geoLocationPoints;
    }

    public void setGeoLocationPoints(List<GeoLocationPoint> geoLocationPoints) {
        this.geoLocationPoints = geoLocationPoints;
    }
}
