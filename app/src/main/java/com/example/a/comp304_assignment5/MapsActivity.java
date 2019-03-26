package com.example.a.comp304_assignment5;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String cityName;
    private String [] cinemaNames = new String[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent intent = getIntent();
        cityName = intent.getStringExtra("cityName");

        switch (cityName) {
            case "Toronto Downtown":
                cinemaNames = getResources().getStringArray(R.array.toronto_downtown_cinema);
                break;
            case "Scarborough":
                cinemaNames = getResources().getStringArray(R.array.scarborough_cinema);
                break;
            case "North York":
                cinemaNames = getResources().getStringArray(R.array.northyork_cinema);
                break;
            case "Mississauga":
                cinemaNames = getResources().getStringArray(R.array.mississauga_cinema);
                break;
            case "Oakville":
                cinemaNames = getResources().getStringArray(R.array.oakville_cinema);
                break;
           }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(mMap.MAP_TYPE_SATELLITE);

        Geocoder geoCoder = new Geocoder(this, Locale.getDefault());
        for (String cinemaName : cinemaNames) {
            try {
                List<Address> address = geoCoder.getFromLocationName(cinemaName, 5);
                if (address.size() > 0) {

                    Double latitude = (double) (address.get(0).getLatitude());
                    Double longitude = (double) (address.get(0).getLongitude());

                    //Log.d("lat-long", "" + latitude + "......." + longitude);
                    final LatLng user = new LatLng(latitude, longitude);
                    String addresses = address.get(0).getAddressLine(0);
                    String city = address.get(0).getLocality();
                    String state = address.get(0).getAdminArea();
                    // String country = addresses.get(0).getCountryName();
                    // String postalCode = addresses.get(0).getPostalCode();
                    //create your custom title
                    String title = addresses + "-" + city + "-" + state;
                    /*used marker for show the location */
                    mMap.addMarker(new MarkerOptions()
                            .position(user)
                            .title(title));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(user, 12));

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
