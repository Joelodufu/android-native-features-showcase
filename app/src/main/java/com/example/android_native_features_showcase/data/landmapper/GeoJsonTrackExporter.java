package com.example.android_native_features_showcase.data.landmapper;

import com.example.android_native_features_showcase.data.landmapper.models.LandTrack;
import com.example.android_native_features_showcase.data.landmapper.models.TrackPoint;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class GeoJsonTrackExporter implements TrackExporter {

    @Override
    public String export(LandTrack landTrack) {
        JSONObject geoJson = new JSONObject();
        geoJson.put("type", "Feature");

        JSONObject properties = new JSONObject();
        properties.put("name", landTrack.getName());
        properties.put("description", landTrack.getDescription());
        geoJson.put("properties", properties);

        JSONObject geometry = new JSONObject();
        geometry.put("type", "LineString");

        List<TrackPoint> points = landTrack.getTrackPoints();
        JSONArray coordinates = new JSONArray();
        for (TrackPoint point : points) {
            JSONArray coord = new JSONArray();
            coord.put(point.getLongitude());
            coord.put(point.getLatitude());
            coordinates.put(coord);
        }
        geometry.put("coordinates", coordinates);

        geoJson.put("geometry", geometry);

        return geoJson.toString();
    }
}
