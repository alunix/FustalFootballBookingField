package com.hammersmith.fustalfootballbookingfield.TabMain;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
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
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
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
public class Map extends Fragment implements OnMapReadyCallback {
    int[] id;
    String[] title;

    List<Field> fields = new ArrayList<>();
    Field field;
    protected GoogleMap googleMap;
    String image = "";
    String nameField;
    Marker marker;
    LatLng lat;
    private ProgressDialog pDialog;
    ArrayList<String> listNames = new ArrayList<>();

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
        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.show();
        setUpMapIfNeeded();
        setupParent(viewMap);
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
                            lat = new LatLng(Double.parseDouble(obj.getString("latitude")),
                                    Double.parseDouble(obj.getString("longitude")));
                            fields.add(field);
                            listNames.add(obj.getString("name"));
                            Marker marker = googleMap.addMarker(new MarkerOptions()
                                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                                    .title(obj.getString("name"))
                                    .snippet(obj.getString("address"))
                                    .position(lat));
                            marker.showInfoWindow();
                            for (int n = 0; n < listNames.size(); n++) {
                                final String latl = listNames.get(n);
                                if (marker.getTitle().equals(latl)) {
                                    Log.d("latlng",""+latl+""+lat);
                                    googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                                        @Override
                                        public void onInfoWindowClick(Marker marker) {
                                            Toast.makeText(getActivity(), latl + " = " + marker.getTitle(), Toast.LENGTH_SHORT).show();
//                                            CameraPosition cameraPosition = new CameraPosition.Builder().target(lat).zoom(18).build();
//                                            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                                        }
                                    });
                                }
                            }
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
            int socketTimeout = 60000;
            RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            fieldReq.setRetryPolicy(policy);
            AppController.getInstance().addToRequestQueue(fieldReq);
        }
        return viewMap;
    }

    @Override
    public void onMapReady(GoogleMap mMap) {
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
            hidePDialog();
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
                hidePDialog();
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

    public void directLocation() {
        googleMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                LatLng lat = new LatLng(location.getLatitude(), location.getLongitude());
                googleMap.addMarker(new MarkerOptions().position(lat).title("Current Location"));
                CameraPosition cameraPosition = new CameraPosition.Builder().target(lat).zoom(12).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                Log.d("Latitude ", "" + location.getLatitude());
                Log.d("Longitude ", "" + location.getLongitude());
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
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
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }
    protected void setupParent(View view) {
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard();
                    return false;
                }
            });
        }
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupParent(innerView);
            }
        }
    }

    private void hideSoftKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
    }
}