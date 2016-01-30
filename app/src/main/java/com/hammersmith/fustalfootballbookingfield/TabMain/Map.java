package com.hammersmith.fustalfootballbookingfield.TabMain;

import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.hammersmith.fustalfootballbookingfield.Activities.ActivityBooking;
import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.controller.AppController;
import com.hammersmith.fustalfootballbookingfield.model.Field;
import com.hammersmith.fustalfootballbookingfield.utils.Constant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 11/17/2015.
 */
public class Map extends Fragment {
    int[] id;
    String[] title;

    List<Field> fields = new ArrayList<>();
    Field field;
    protected GoogleMap googleMap;
    String image = "";
    String nameField;
    Marker marker;

    public Map() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewMap = inflater.inflate(R.layout.map, container, false);
        setUpMapIfNeeded();

        if (fields.size() <= 0) {
            // Creating volley request obj
            JsonArrayRequest fieldReq = new JsonArrayRequest(Constant.URL_LOCATION, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray jsonArray) {
                    id = new int[jsonArray.length()];
                    title = new String[jsonArray.length()];
                    for (int i = 0; i < jsonArray.length(); i++) {
                        try {
                            JSONObject obj = jsonArray.getJSONObject(i);
                            field = new Field();
                            field.setImage(Constant.URL_HOME + obj.getString("image_path"));
                            field.setName(obj.getString("name"));
                            field.setLocation(obj.getString("address"));
                            id[i] = obj.getInt("id");
                            nameField = title[i] = obj.getString("name");
                            fields.add(field);
//                            Toast.makeText(getActivity(),nameField,Toast.LENGTH_SHORT).show();

                                googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                                    @Override
                                    public void onInfoWindowClick(Marker marker) {
                                        if (marker.getTitle().equals(nameField)) {
                                            Toast.makeText(getActivity(), marker.getTitle(), Toast.LENGTH_SHORT).show();
                                        }else{
                                            Toast.makeText(getActivity(),"Not Found"+nameField,Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Toast.makeText(getActivity(), volleyError + "", Toast.LENGTH_SHORT).show();
                }
            });
            AppController.getInstance().addToRequestQueue(fieldReq);

        }

        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {

//                for (int i = 0; i < fields.size(); i++) {
//                    if (marker.getTitle().equals(nameField)) {
//                        Toast.makeText(getActivity(), marker.getTitle(), Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(getActivity(), "Not found", Toast.LENGTH_SHORT).show();
//                    }
//                }
            }
        });

        googleMap.setMyLocationEnabled(true);
        googleMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                AlertDialog.Builder mAlertDialog = new AlertDialog.Builder(getActivity());

                mAlertDialog.setTitle("Location not available, Open GPS?")
                        .setMessage("Activate GPS to use use location services?")
                        .setPositiveButton("Open Settings", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                getActivity().startActivity(intent);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
//                                directLocation();
                            }
                        }).show();
                return true;
            }
        });

        return viewMap;
    }

    private class MarkerTask extends AsyncTask<Void, Void, String> {
        private static final String LOG_TAG = "ExampleApp";

        @Override
        protected String doInBackground(Void... params) {

            HttpURLConnection conn = null;
            final StringBuilder json = new StringBuilder();
            try {
                URL url = new URL(Constant.URL_LOCATION);
                conn = (HttpURLConnection) url.openConnection();
                InputStreamReader in = new InputStreamReader(conn.getInputStream());

                int read;
                char[] buff = new char[1024];
                while ((read = in.read(buff)) != -1) {
                    json.append(buff, 0, read);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                Log.e(LOG_TAG, "Error connecting to service", e);
            } finally {
                if (conn != null) {
                    conn.disconnect();
                }
            }
            return json.toString();
        }

        @Override
        protected void onPostExecute(String json) {
            try {
                JSONArray jsonArray = new JSONArray(json);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    LatLng latLng = new LatLng(Double.parseDouble(jsonObject.getString("latitude")),
                            Double.parseDouble(jsonObject.getString("longitude")));
                    if (i == 1) {
                        CameraPosition cameraPosition = new CameraPosition.Builder()
                                .target(latLng).zoom(0).build();
                        googleMap.animateCamera(CameraUpdateFactory
                                .newCameraPosition(cameraPosition));
                        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    }
                    Marker marker = googleMap.addMarker(new MarkerOptions()
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                            .title(jsonObject.getString("name"))
                            .snippet(jsonObject.getString("address"))
                            .position(latLng));
                    marker.showInfoWindow();
                    CameraPosition cameraPosition = new CameraPosition.Builder().target(latLng).zoom(12).build();
                    googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

                }
            } catch (JSONException e) {
                Log.e(LOG_TAG, "Error processing JSON", e);
            }
            super.onPostExecute(json);
        }
    }

    private void setUpMapIfNeeded() {
        if (googleMap == null) {
            googleMap = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map)).getMap();
            if (googleMap != null) {
                UiSettings mapSettings;
                mapSettings = googleMap.getUiSettings();
                mapSettings.setZoomControlsEnabled(true);
                mapSettings.setScrollGesturesEnabled(true);
                mapSettings.setTiltGesturesEnabled(true);
                mapSettings.setRotateGesturesEnabled(true);
                new MarkerTask().execute();
            }
        }
    }

    public void directLocation(){
        googleMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                LatLng lat = new LatLng(location.getLatitude(),location.getLongitude());
//                LatLng lat = new LatLng(11.3343,104.5222);
                googleMap.addMarker(new MarkerOptions().position(lat).title("Current Location"));
                CameraPosition cameraPosition = new CameraPosition.Builder().target(lat).zoom(12).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                Log.d("Latitude ", "" + location.getLatitude());
                Log.d("Longitude ",""+location.getLongitude());
            }
        });
    }

//    private void createMarkerFromJson(String json) throws JSONException {
//
//        JSONArray jsonArray = new JSONArray(json);
//        for (int i = 0; i < jsonArray.length(); i++) {
//            JSONObject jsonObject = jsonArray.getJSONObject(i);
//            googleMap.addMarker(new MarkerOptions()
//                            .title(jsonObject.getString("name"))
//                            .snippet(Integer.toString(jsonObject.getInt("id")))
//                            .position(new LatLng(Double.parseDouble(jsonObject.getString("latitude")), Double.parseDouble(jsonObject.getString("longitude"))
//                            ))
//            );
//        }
//    }

    @Override
    public void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

}