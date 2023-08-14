package com.example.puntualo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions

class Mapa : AppCompatActivity() , OnMapReadyCallback {

    companion object {
        const val EXTRA_LATITUDE = "extra_latitude"
        const val EXTRA_LONGITUDE = "extra_longitude"
        const val EXTRA_RESTAURANT_NAME = "extra_restaurant_name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mapa)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapa) as SupportMapFragment

        mapFragment.getMapAsync(this);

    }
    override fun onMapReady(googleMap: GoogleMap) {

        googleMap.setMapStyle(  MapStyleOptions.loadRawResourceStyle(
            this, R.raw.style_json))


        println(intent.getDoubleExtra(EXTRA_LATITUDE, 0.0))
        println(intent.getDoubleExtra(EXTRA_LONGITUDE, 0.0))
        val ubicacion = LatLng(intent.getDoubleExtra(EXTRA_LATITUDE, 0.0),intent.getDoubleExtra(EXTRA_LONGITUDE, 0.0))
        googleMap.addMarker(
            MarkerOptions()
                .position(ubicacion)
                .title(intent.getStringExtra(EXTRA_RESTAURANT_NAME))
                .icon((BitmapDescriptorFactory.fromResource(R.drawable.marcador
                )))
        )
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ubicacion,15f))

        googleMap.uiSettings.isZoomControlsEnabled = true
    }

}