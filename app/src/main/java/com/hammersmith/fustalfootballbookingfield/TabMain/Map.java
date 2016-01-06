package com.hammersmith.fustalfootballbookingfield.TabMain;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.hammersmith.fustalfootballbookingfield.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by USER on 11/17/2015.
 */
public class Map extends Fragment {
    // private GoogleApiClient googleApiClient;

    protected GoogleMap googleMap;


//    private HashMap<Marker,MyMarker> markerMyMarkerHashMap;
//    private ArrayList<MyMarker>myMarkerArrayList = new ArrayList<MyMarker>();


    public Map() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onResume() {
        super.onResume();
        setUpMapIfNeeded();



    }
    @Override
    public void onStart() {
        super.onStart();
//        googleApiClient.connect();
    }



    private void setUpMapIfNeeded() {
        if (googleMap == null) {
            googleMap = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map)).getMap();
            if (googleMap != null) {
                // googleMap.setMyLocationEnabled(true);


                UiSettings mapSettings;
                mapSettings = googleMap.getUiSettings();
                mapSettings.setZoomControlsEnabled(true);
                mapSettings.setScrollGesturesEnabled(true);
                mapSettings.setTiltGesturesEnabled(true);
                mapSettings.setRotateGesturesEnabled(true);
                //  setUpMap();
                new MarkerTask().execute();


            }

        }
    }


    private void createMarkerFromJson(String json) throws JSONException {

        JSONArray jsonArray = new JSONArray(json);
        for (int i = 0; i<jsonArray.length();i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            googleMap.addMarker(new MarkerOptions()
                            .title(jsonObject.getString("name"))
                            .snippet(Integer.toString(jsonObject.getInt("population")))
                            .position(new LatLng(
                                    jsonObject.getJSONArray("latlng").getDouble(0),
                                    jsonObject.getJSONArray("latlng").getDouble(1)
                            ))
            );
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewMap = inflater.inflate(R.layout.map, container, false);

        setUpMapIfNeeded();
        // handleIntent(getActivity().getIntent());
        return viewMap;
    }

    private class MarkerTask extends AsyncTask<Void,Void,String> {
        private static final String LOG_TAG ="ExampleApp";
        private static final String SERVICE_URL = "https://api.myjson.com/bins/4jb09";

        @Override
        protected String doInBackground(Void... params) {

            HttpURLConnection conn = null;
            final StringBuilder json = new StringBuilder();
            try {
                URL url = new URL(SERVICE_URL);
                conn = (HttpURLConnection) url.openConnection();
                InputStreamReader in = new InputStreamReader(conn.getInputStream());

                int read;
                char[] buff = new char[1024];
                while ((read = in.read(buff))!=-1){
                    json.append(buff,0,read);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                Log.e(LOG_TAG,"Error connecting to service",e);
            }finally {
                if (conn!= null){
                    conn.disconnect();
                }
            }
            return json.toString();
        }

        @Override
        protected void onPostExecute(String json) {
            try {
                JSONArray jsonArray = new JSONArray(json);
                for (int i = 0;i<jsonArray.length();i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    LatLng latLng = new LatLng(jsonObject.getJSONArray("latlng").getDouble(0),
                            jsonObject.getJSONArray("latlng").getDouble(1));

                    if (i==1){
                        CameraPosition cameraPosition = new CameraPosition.Builder()
                                .target(latLng).zoom(0).build();

                        googleMap.animateCamera(CameraUpdateFactory
                                .newCameraPosition(cameraPosition));
                        googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                    }
                    googleMap.addMarker(new MarkerOptions()
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                            .title(jsonObject.getString("name"))
                            .snippet(Integer.toString(jsonObject.getInt("population")))
                            .position(latLng));


                }
            } catch (JSONException e) {
                Log.e(LOG_TAG,"Error processing JSON",e);
            }
            super.onPostExecute(json);
        }
    }

}