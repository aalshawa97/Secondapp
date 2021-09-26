package com.abdul.secondapp;
import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONObject;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

// Implement OnMapReadyCallback.
public class MapCallback extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the layout file as the content view.
        setContentView(R.layout.activity_maps);

        // Get a handle to the fragment and register the callback.
        try {
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
            queryGoogleDistanceApi();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Get a handle to the GoogleMap object and display marker.
    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(0, 0))
                .title("Marker"));
        googleMap.getMaxZoomLevel();
    }

    public JSONObject queryGoogleDistanceApi() throws Exception{
        //String Url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + origin + "&destinations=" + destination + "&mode=driving&language=en&key=" + API_KEY_PLACES;
        String Url = "https://localsearch.azurewebsites.net/api/Locations";
        HttpURLConnection conn = null;
        StringBuilder jsonResults = new StringBuilder();
        URL url = new URL(Url);
        Log.d(TAG, Url);
        conn = (HttpURLConnection) url.openConnection();
        //Throws error
        InputStreamReader in = new InputStreamReader(conn.getInputStream());
        // Load the results into a StringBuilder
        int read;
        char[] buff = new char[1024];
        while ((read = in.read(buff)) != -1) {
            jsonResults.append(buff, 0, read);
        }
        if (conn != null) {
            conn.disconnect();
        }

        JSONObject object = new JSONObject(jsonResults.toString());
        return object;
    }
}