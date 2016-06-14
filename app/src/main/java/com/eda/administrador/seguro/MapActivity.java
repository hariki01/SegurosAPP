package com.eda.administrador.seguro;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener {

    private double mLongitud;
    private double mLatitud;

    Button btnConfirmar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        btnConfirmar = (Button) findViewById(R.id.btnConfirmar);
        btnConfirmar.setOnClickListener(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        googleMap.setMyLocationEnabled(true);
        UiSettings uiSettings = googleMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
        uiSettings.setZoomGesturesEnabled(true);

        LatLng jockey = new LatLng(-12.085937, -76.976017);
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(jockey, 15));
        //googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(jockey, 15));
        Marker marker = googleMap.addMarker(new MarkerOptions()
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher))
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
                .title("Jockey Plaza")
                .snippet("Av. Javier Prado Este 4200")
                .position(jockey)
                .draggable(true));

        LatLng position;
        position = marker.getPosition();
        mLatitud = position.latitude;
        mLongitud = position.longitude;
    }

    public String direccion() {

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        String city = "";
        String state;
        String zip;
        String country;
        String direccion = "";

        try {
            List<Address> addresses = geocoder.getFromLocation(mLatitud, mLongitud, 1);
            city = addresses.get(0).getLocality();
            state = addresses.get(0).getAdminArea();
            zip = addresses.get(0).getPostalCode();
            country = addresses.get(0).getCountryName();
            direccion = addresses.get(0).getAddressLine(0);

        } catch (IOException e) {

        }

        return direccion;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnConfirmar:
                String direccion;
                direccion = direccion();
                Intent intent=new Intent();
                intent.putExtra("DIRECCION",direccion);
                intent.putExtra("LATITUD",mLatitud);
                intent.putExtra("LONGITUD",mLongitud);
                setResult(RESULT_OK,intent);
                finish();
                break;
        }
    }
}
