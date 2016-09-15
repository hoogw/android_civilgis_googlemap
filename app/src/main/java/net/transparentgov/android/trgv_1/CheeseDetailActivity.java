/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.transparentgov.android.trgv_1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.SeekBar;
import android.view.View;
import android.widget.CheckBox;

import android.widget.SeekBar.OnSeekBarChangeListener;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.maps.model.TileProvider;
import com.google.android.gms.maps.model.UrlTileProvider;


import java.util.Map;
import java.util.Random;


import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.maps.android.geojson.GeoJsonFeature;
import com.google.maps.android.geojson.GeoJsonLayer;
import com.google.maps.android.geojson.GeoJsonLineStringStyle;
import com.google.maps.android.geojson.GeoJsonPointStyle;
import com.google.maps.android.geojson.GeoJsonPolygonStyle;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class CheeseDetailActivity extends AppCompatActivity
        implements OnSeekBarChangeListener, OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnCameraIdleListener,
        ActivityCompat.OnRequestPermissionsResultCallback {



    private static final int TRANSPARENCY_MAX = 100;

    /** This returns moon tiles. */
    private static final String MOON_MAP_URL_FORMAT = "http://166.62.80.50:8887/v2/%s/%d/%d/%d.png";
          //  "http://166.62.80.50:8887/v2/%1$s/%2$d/%3$d/%4$d.png";

    private TileOverlay mMoonTiles;
    private SeekBar mTransparencyBar;

    // MapView mMapView;

    private String area_subject;
    private String _area_;
    private String _subject_;

    private String _geometry_type;
    private String _key;
    private int _i;
    private double _lat;
    private double _lng;
    private int _zoom;


    private GeoJsonLayer _geojson_layer_last = null;




    private GoogleMap mMap;


    /**
     * Request code for location permission request.
     *
     * @see #onRequestPermissionsResult(int, String[], int[])
     */
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    /**
     * Flag indicating whether a requested permission has been denied after returning in
     * {@link #onRequestPermissionsResult(int, String[], int[])}.
     */
    private boolean mPermissionDenied = false;





    private Iterable<GeoJsonFeature>  _f_i;



    public static final String EXTRA_NAME = "cheese_name";


    private final static String mLogTag = "GeoJsonDemo";

    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        // ------------ google map -------------------------  Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


              //.... tile overlay ..........

        mTransparencyBar = (SeekBar) findViewById(R.id.transparencySeekBar);
        mTransparencyBar.setMax(TRANSPARENCY_MAX);
        mTransparencyBar.setProgress(0);

        //--------------End  google map -----------------


        Intent intent = getIntent();
        final String cheeseName = intent.getStringExtra(EXTRA_NAME);

        area_subject = cheeseName;

        Map<String, List<Double>> area_hmap_ = Area.getAreaInitLocation();

        for (Map.Entry<String, List<Double>> entry : area_hmap_.entrySet()) {
           // System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
            List<Double> _value = entry.getValue();

             _key = entry.getKey();



            if (area_subject.contains(_key) ){

                _area_ = _key;

               _subject_ = area_subject.substring(_area_.length()+1);

                _lat = _value.get(0);
                _lng = _value.get(1);
                _zoom = _value.get(2).intValue();
            }



        }




        AppBarLayout _appbar = (AppBarLayout) findViewById(R.id.appbar);
        _appbar.setExpanded(false);


        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(area_subject);

        // collapsingToolbar.setTitle(_area_);
        /*loadBackdrop();*/
    }

    /*private void loadBackdrop() {
        final ImageView imageView = (ImageView) findViewById(R.id.backdrop);
        Glide.with(this).load(Cheeses.getRandomCheeseDrawable()).centerCrop().into(imageView);
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sample_actions, menu);
        return true;
    }



    public void add_tile(){


        // ---------  Add tile overlay  ----------------
        TileProvider tileProvider = new UrlTileProvider(256, 256) {
            @Override
            public synchronized URL getTileUrl(int x, int y, int zoom) {
                // The moon tile coordinate system is reversed.  This is not normal.
                //int reversedY = (1 << zoom) - y - 1;
                String s = String.format(Locale.US, MOON_MAP_URL_FORMAT,area_subject, zoom, x, y);
                URL url = null;
                try {
                    url = new URL(s);
                } catch (MalformedURLException e) {
                    throw new AssertionError(e);
                }
                return url;
            }
        };

        mMoonTiles = mMap.addTileOverlay(new TileOverlayOptions().tileProvider(tileProvider));
        mTransparencyBar.setOnSeekBarChangeListener(this);

        // set position of bar by padding
        // mTransparencyBar.setPadding(0,0,0,0);
        // ---------End of  Add tile overlay  ----------------



    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        if (Area.hasTile(area_subject)) {

            add_tile();

        }




        //  -------  Add area boundary and zoom to area  ---------
        //LatLng center = new LatLng(33.65992448007282, -117.91505813598633);
        LatLng center = new LatLng(_lat, _lng);

      //  mMap.addMarker(new MarkerOptions().position(center).title(_area_));
       // mMap.addMarker(new MarkerOptions().position(center).title(_subject_));



        // this is a bug, can't disable auto center
        // ...............disable auto center when marker clicked................
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                return true;
            }
        });
        //.....................................................................



        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(center, _zoom));

       // Download the GeoJSON file.

        //Bug, do not use domain name, it will return html doc instead of json, should use ip
        // Wrong,  retrieveFileFromUrl("http://transparentgov.net/api/geojson/maparealimit/" + _area_ +"/limit/");
       // Wrong,   retrieveFileFromUrl("http://phpmap.transparentgov.net/api/maparealimit/" + _area_ +"/limit");

       // dot net
       // retrieveFileFromUrl("http://166.62.80.50/api/geojson/maparealimit/" + _area_ +"/limit");


        // php
        retrieveFileFromUrl("http://166.62.80.50:10/gis/api/maparealimit/" + _area_ +"/limit");

        // Alternate approach of loading a local GeoJSON file.
       // retrieveFileFromResource();

        //  -------End  Add area boundary and zoom to area  ---------







        // ===============  add map control ======= my location ===================

        mMap.setOnMyLocationButtonClickListener(this);
        enableMyLocation();





        //googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        googleMap.getUiSettings().setCompassEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(true);

        // let control move down 100, left 200
        googleMap.setPadding(0,100,220,0);
        // =======  End ========  add map control ======= my location ===================





        // +++++++++++ map event ++++++++++++++++++++

        mMap.setOnCameraIdleListener(this);





        // No need first time load geojson
       // get_map_bound();

        // ++++++++++++++ map event +++++++++++++++++++++





    }// on map ready



    public void get_map_bound() {

        LatLngBounds viewport_bounds = mMap.getProjection().getVisibleRegion().latLngBounds;

        double NE_lat  = viewport_bounds.northeast.latitude;
        double NE_lng  = viewport_bounds.northeast.longitude;

        double SW_lat = viewport_bounds.southwest.latitude;
        double SW_lng = viewport_bounds.southwest.longitude;

        // http://localhost:10/civilgis/api/load/general_landuse/SWlong/SWlat/NElong/NElat/   This is sample URI
        String _url_geojson = "http://166.62.80.50:10/gis/api/loadall_mobile/" + _area_ + '/' + _subject_ + "/" + SW_lng + "/" + SW_lat + "/" + NE_lng + "/" + NE_lat + "/";
       // String _url_geojson = "http://166.62.80.50/api/geojson/feature_mobile/" + _area_ + '/' + _subject_ + "/" + SW_lng + "/" + SW_lat + "/" + NE_lng + "/" + NE_lat + "/";



        Log.d("---URL---", _url_geojson);



        retrieveFileFromUrl(_url_geojson);


    }


    @Override
    public void onCameraIdle() {


        get_map_bound();




    }








    // =============== my location function =============




    /**
     * Enables the My Location layer if the fine location permission has been granted.
     */
    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission to access the location is missing.
            PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
                    Manifest.permission.ACCESS_FINE_LOCATION, true);
        } else if (mMap != null) {
            // Access to the location has been granted to the app.
            mMap.setMyLocationEnabled(true);
        }
    }

    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            return;
        }

        if (PermissionUtils.isPermissionGranted(permissions, grantResults,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Enable the my location layer if the permission has been granted.
            enableMyLocation();
        } else {
            // Display the missing permission error dialog when the fragments resume.
            mPermissionDenied = true;
        }
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        if (mPermissionDenied) {
            // Permission was not granted, display error dialog.
            showMissingPermissionError();
            mPermissionDenied = false;
        }
    }

    /**
     * Displays a dialog with error message explaining that the location permission is missing.
     */
    private void showMissingPermissionError() {
        PermissionUtils.PermissionDeniedDialog
                .newInstance(true).show(getSupportFragmentManager(), "dialog");
    }









    // ================ End my location function ====================





    // ------------------------------ geojson ----------------------------------




    private static float magnitudeToColor(double magnitude) {
        if (magnitude < 1.0) {
            return BitmapDescriptorFactory.HUE_CYAN;
        } else if (magnitude < 2.5) {
            return BitmapDescriptorFactory.HUE_GREEN;
        } else if (magnitude < 4.5) {
            return BitmapDescriptorFactory.HUE_YELLOW;
        } else {
            return BitmapDescriptorFactory.HUE_RED;
        }
    }



    private void retrieveFileFromUrl(String _geojson_url) {
       // new DownloadGeoJsonFile().execute(getString(R.string.geojson_url));


        new DownloadGeoJsonFile().execute(_geojson_url);

    }

    private void retrieveFileFromResource() {
        try {
            GeoJsonLayer layer = new GeoJsonLayer(mMap, R.raw.city, this);
            addGeoJsonLayerToMap(layer);
        } catch (IOException e) {
            Log.e(mLogTag, "GeoJSON file could not be read");
        } catch (JSONException e) {
            Log.e(mLogTag, "GeoJSON file could not be converted to a JSONObject");
        }
    }

    /**
     * Adds a point style to all features to change the color of the marker based on its magnitude
     * property
     */
    private void addColorsToMarkers(GeoJsonLayer layer) {
        // Iterate over all the features stored in the layer
        for (GeoJsonFeature feature : layer.getFeatures()) {
            // Check if the magnitude property exists
            if (feature.getProperty("mag") != null && feature.hasProperty("place")) {
                double magnitude = Double.parseDouble(feature.getProperty("mag"));

                // Get the icon for the feature
                BitmapDescriptor pointIcon = BitmapDescriptorFactory
                        .defaultMarker(magnitudeToColor(magnitude));

                // Create a new point style
                GeoJsonPointStyle pointStyle = new GeoJsonPointStyle();

                // Set options for the point style
                pointStyle.setIcon(pointIcon);
                pointStyle.setTitle("Magnitude of " + magnitude);
                pointStyle.setSnippet("Earthquake occured " + feature.getProperty("place"));

                // Assign the point style to the feature
                feature.setPointStyle(pointStyle);
            }
        }
    }

    private class DownloadGeoJsonFile extends AsyncTask<String, Void, GeoJsonLayer> {

        @Override
        protected GeoJsonLayer doInBackground(String... params) {

            StringBuilder result = new StringBuilder();

            try {
                // ================== Open a stream from the URL ============

                // Open a stream from the URL
                InputStream stream = new URL(params[0]).openStream();

                String line;

                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

                while ((line = reader.readLine()) != null) {
                    // Read and save each line of the stream
                    result.append(line);
                }

                // Close the stream
                reader.close();
                stream.close();

                //============================



                // log
                String TAG = CheeseDetailActivity.class.getSimpleName() + " ### ";
                Log.d(TAG, params[0]);
                Log.d(TAG, result.toString());
                // end log



                if (result.length()<20) {

/*
                    // display feature info at bottom small popup window.
                    Toast.makeText(CheeseDetailActivity.this,
                            "Found too many records,zoom in for detail",
                            Toast.LENGTH_SHORT).show();
*/
                    Log.e("--return number- ", result.toString());

                    // return a number
                    removeLastGeoJsonLayerFromMap(_geojson_layer_last);




                    return null;


                }

                else {

                     // return geojson



                    _geojson_layer_last = new GeoJsonLayer(mMap, new JSONObject(result.toString()));

                    return _geojson_layer_last;

                }




            } catch (IOException e) {
                Log.e(mLogTag, "GeoJSON file could not be read");
            } catch (JSONException e) {

                Log.e(mLogTag, "GeoJSON file could not be converted to a JSONObject");
            }
            finally {

            }
            return null;
        }

        @Override
        protected void onPostExecute(GeoJsonLayer layer) {
            if (layer != null) {

                removeLastGeoJsonLayerFromMap(_geojson_layer_last);


                addGeoJsonLayerToMap(layer);
            }
        }

    }// class downloadgeojsonfile




    private void removeLastGeoJsonLayerFromMap(GeoJsonLayer last_geoJsonlayer) {

        try {
          //  if (last_geoJsonlayer != null) {

            Log.e("-last geojson-", last_geoJsonlayer.toString());
                last_geoJsonlayer.removeLayerFromMap();


     Log.e("***** remove *******", last_geoJsonlayer.toString());
          //  }



        }
        catch(RuntimeException rte){

            Log.e("fail remove", "!!!!!!!!!!!!!!!!!!!!!!");

        }


    }






    private void addGeoJsonLayerToMap(GeoJsonLayer layer) {

       // addColorsToMarkers(layer);
        layer.addLayerToMap();

       Log.e("add geojson==", layer.toString());

        // Demonstrate receiving features via GeoJsonLayer clicks.
        layer.setOnFeatureClickListener(new GeoJsonLayer.GeoJsonOnFeatureClickListener() {
            @Override
            public void onFeatureClick(GeoJsonFeature feature) {



                try {


                    Random rnd = new Random();
                    int random_color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));


                    // ---------------   highlight feature (clicked)  -----------------------

                 /*
                GeoJsonGeometry _geometry = feature.getGeometry();
               _geometry_type =  _geometry.getType();



                if (_geometry_type.equals("LineString") || _geometry_type.equals("MultiLineString") ){



                    GeoJsonLineStringStyle _line_style = new GeoJsonLineStringStyle();
                    _line_style.setColor(random_color);

                    feature.setLineStringStyle(_line_style);

                }


                if (_geometry_type.equals("Polygon") || _geometry_type.equals("MultiPolygon") ){


                    GeoJsonPolygonStyle _plg_style = new GeoJsonPolygonStyle();
                    _plg_style.setStrokeColor(random_color);

                    feature.setPolygonStyle(_plg_style);

                }



                if (_geometry_type.equals("Point") || _geometry_type.equals("MultiPoint") ){

                   GeoJsonPointStyle _point_style = new GeoJsonPointStyle();
                  //  _point_style.setIcon();


                }

*/
                    // above code has bug, because get geometry type often throw exception.

                    GeoJsonLineStringStyle _line_style = new GeoJsonLineStringStyle();
                    _line_style.setColor(random_color);

                    feature.setLineStringStyle(_line_style);



                    GeoJsonPolygonStyle _plg_style = new GeoJsonPolygonStyle();
                    _plg_style.setStrokeColor(random_color);

                    feature.setPolygonStyle(_plg_style);


                // --------------   End --- -----------------   highlight feature (clicked)  -----------------------









                    // get all properties


                    String _info = "";

                    Iterable<String> _ppk_i = feature.getPropertyKeys();

                    Iterator<String> _ppk_r = _ppk_i.iterator();

                    while (_ppk_r.hasNext()) {
                        String _key = _ppk_r.next();
                        //System.out.println(cur);

                        _info = _info + " | " + _key + " : " + feature.getProperty(_key);
                    }

                    // End -- get all properties





                // display feature info at bottom small popup window.
                Toast.makeText(CheeseDetailActivity.this,
                        _info,
                        Toast.LENGTH_SHORT).show();


                }
                catch(NullPointerException npe){

                 // if failed to get property key
                    Toast.makeText(CheeseDetailActivity.this,
                            "No property",
                            Toast.LENGTH_SHORT).show();

                }


              // Log.d("&&&&&&&& ", ".......................");


            }
        });

    }








    // -------------End ----------------- geojson ----------------------------------

    public void setFadeIn(View v) {
        if (mMoonTiles == null) {
            return;
        }
        mMoonTiles.setFadeIn(((CheckBox) v).isChecked());
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (mMoonTiles != null) {
            mMoonTiles.setTransparency((float) progress / (float) TRANSPARENCY_MAX);
        }
    }

}// class






