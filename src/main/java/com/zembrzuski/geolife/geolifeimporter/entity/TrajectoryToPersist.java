package com.zembrzuski.geolife.geolifeimporter.entity;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Entidade que vai virar um json para persistir no elastic-search.
 */

public class TrajectoryToPersist {

    private String userId;
    private List<Path> path;

    public TrajectoryToPersist(String userId, TreeMap<Path, TrajectoryLabel> geoLocationPointTrajectoryLabelTreeMap) {
        this.userId = userId;
        this.path = fromTreeMap(geoLocationPointTrajectoryLabelTreeMap);
    }

    public TrajectoryToPersist(String userId, List<Path> path) {
        this.userId = userId;
        this.path = path;
    }

    private List<Path> fromTreeMap(TreeMap<Path, TrajectoryLabel> geoLocationPointTrajectoryLabelTreeMap) {
        List<Path> x = Lists.newArrayList();

        for(Map.Entry<Path, TrajectoryLabel> entry: geoLocationPointTrajectoryLabelTreeMap.entrySet()) {
            Path key = entry.getKey();
            TrajectoryLabel value = entry.getValue();

            x.add(new Path(key.getLat(), key.getLng(), key.getTimestamp(), value.getMode()));
        }

        return x;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Path> getPath() {
        return path;
    }

    public void setPath(List<Path> path) {
        this.path = path;
    }
}
