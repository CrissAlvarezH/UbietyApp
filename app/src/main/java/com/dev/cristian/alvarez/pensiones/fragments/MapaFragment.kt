package com.dev.cristian.alvarez.pensiones.fragments

import android.Manifest
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
import android.Manifest.permission
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.app.Activity
import androidx.core.app.ActivityCompat
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.dev.cristian.alvarez.pensiones.utils.PermisosUtils

private const val COD_PEDIR_PERMISOS = 352

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

        establecerMiPosicionEnMapa()

        val monteria = LatLng(8.7506882,-75.8889799);
        this.mapa?.moveCamera(CameraUpdateFactory.newLatLngZoom(monteria, 15f));

    }

    public fun establecerMiPosicionEnMapa() {

        context?.let {

            if (ContextCompat.checkSelfPermission(
                    it,
                    ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                this.mapa?.isMyLocationEnabled = true;
            }
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

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
