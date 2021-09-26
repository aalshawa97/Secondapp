package com.abdul.secondapp;

import android.os.AsyncTask;

import java.net.URL;

public class Task extends AsyncTask<String, Void, String> {

    /**
     * Override this method to perform a computation on a background thread. The
     * specified parameters are the parameters passed to {@link #execute}
     * by the caller of this task.
     * <p>
     * This will normally run on a background thread. But to better
     * support testing frameworks, it is recommended that this also tolerates
     * direct execution on the foreground thread, as part of the {@link #execute} call.
     * <p>
     * This method can call {@link #publishProgress} to publish updates
     * on the UI thread.
     *
     * @param strings The parameters of the task.
     * @return A result, defined by the subclass of this task.
     * @see #onPreExecute()
     * @see #onPostExecute
     * @see #publishProgress
     */
    @Override
    protected String doInBackground(String... strings) {
        return null;
    }

    /*
    protected Long doInBackground(URL... urls) {
        int count = urls.length;
        long totalSize = 0;
        for (int i = 0; i < count; i++) {
            totalSize += Downloader.downloadFile(urls[i]);
            publishProgress((int) ((i / (float) count) * 100));
            // Escape early if cancel() is called
            if (isCancelled()) break;
        }
        return totalSize;
    }*/
}
/*


    String currentLatitude;
    String currentlongitude;

    public Task(String currentLatitude, String currentlongitude){
        this.currentLatitude = currentLatitude;
        this.currentlongitude = currentlongitude;
    }

    @Override
    protected String doInBackground(City... cities) {
        final Markers mMap = ...;
        for (City city : cities) {
            GeoPoint geoPoint = city.getLocation();
            String nameBeach = city.getName();

            if (geoPoint != null) {
                String latitude = String.valueOf(geoPoint.getLatitude());
                String longitude = String.valueOf(geoPoint.getLongitude());

                HttpURLConnection urlConnection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL("https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + currentLatitude + "," + currentlongitude + "&destinations=" + latitude + "," + longitude + "&key=xxx";);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                    JSONObject jsonObject = new JSONObject(result.toString()).getJSONArray("rows").getJSONObject(0).getJSONArray("elements").getJSONObject(0);
                    String duration = jsonObject.getJSONObject("duration").getString("text");
                    String distance = jsonObject.getJSONObject("distance").getString("text");

                    mMap.addMarker(new MarkerOptions().position(new LatLng(geoPoint.getLatitude(), geoPoint.getLongitude()))
                            .title(nameBeach)
                            .snippet(distance + ", " + duration)
                            .icon(BitmapDescriptorFactory.defaultMarker()));
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if(reader!=null){
                        try {
                            reader.close();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    if (urlConnection != null) {
                        try {
                            urlConnection.disconnect();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        return mMap;
    }
}
*/

/*
fun createLocationRequest() {
    val locationRequest = LocationRequest.create()?.apply {
        interval = 10000
        fastestInterval = 5000
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }
}
*/