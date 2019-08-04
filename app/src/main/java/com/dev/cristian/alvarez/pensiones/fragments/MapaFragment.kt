package com.dev.cristian.alvarez.pensiones.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dev.cristian.alvarez.pensiones.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.activity_maps.*


class MapaFragment : Fragment(), OnMapReadyCallback {

    private var mapView: MapView? = null;
    private var mapa: GoogleMap? = null;

    override fun onResume() {
        super.onResume()
        mapView?.onResume();
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val vista: View = inflater.inflate(R.layout.fragment_mapa, container, false);

        mapView = vista.findViewById(R.id.mapview);

        mapView?.getMapAsync(this);
        mapView?.onCreate(savedInstanceState);

        return vista;
    }

    override fun onMapReady(map: GoogleMap?) {
        this.mapa = map;

        val monteria = LatLng(8.7506882,-75.8889799);
        this.mapa?.moveCamera(CameraUpdateFactory.newLatLngZoom(monteria, 15f));

    }

    override fun onStop() {
        super.onStop()
        mapView?.onStop();
    }

    override fun onDestroyView() {
        super.onDestroyView();
        mapView?.onDestroy();
    }
}
