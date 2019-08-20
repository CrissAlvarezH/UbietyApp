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
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Intent
import android.content.pm.PackageManager
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.dev.cristian.alvarez.pensiones.actividades.DetallesPensionActivity
import com.dev.cristian.alvarez.pensiones.modelos.*
import com.google.android.gms.maps.model.*
import kotlinx.android.synthetic.main.activity_detalles_pension.*


class MapaFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnMapClickListener {

    private var contInfowindo: CardView? = null;
    private var img1Pension: ImageView? = null;
    private var img2Pension: ImageView? = null;
    private var img3Pension: ImageView? = null;
    private var imgPropietarioPension: ImageView? = null;
    private var txtPreciosPension: TextView? = null;
    private var txtPropietarioPension: TextView? = null;

    private var mapView: MapView? = null;
    private var mapa: GoogleMap? = null;

    private var pensiones: List<Pension>? = null;
    private var pensionSeleccionada: Pension? = null;

    override fun onResume() {
        super.onResume()
        mapView?.onResume();
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val vista: View = inflater.inflate(R.layout.fragment_mapa, container, false);

        enlazarXML(vista)

        mapView?.getMapAsync(this);
        mapView?.onCreate(savedInstanceState);

        return vista;
    }

    private fun enlazarXML(vista: View) {
        mapView = vista.findViewById(R.id.mapview);

        // Infowindow personalizado
        contInfowindo = vista.findViewById(R.id.card_cont_infowindow_pensiones)
        img1Pension = vista.findViewById(R.id.img_1_pension)
        img2Pension = vista.findViewById(R.id.img_2_pension)
        img3Pension = vista.findViewById(R.id.img_3_pension)
        imgPropietarioPension = vista.findViewById(R.id.img_propietario_pension)
        txtPropietarioPension = vista.findViewById(R.id.txt_nombre_propietario_pension)
        txtPreciosPension = vista.findViewById(R.id.txt_precios_pension)

        contInfowindo?.setOnClickListener {
            val irDetalles = Intent(context, DetallesPensionActivity::class.java);
            irDetalles.putExtra(DetallesPensionActivity.ARG_PENSION, pensionSeleccionada);
            startActivity(irDetalles)
        }
    }

    override fun onMapReady(map: GoogleMap?) {
        this.mapa = map;

        establecerMiPosicionEnMapa()

        val monteria = LatLng(8.7506882,-75.8889799);
        this.mapa?.moveCamera(CameraUpdateFactory.newLatLngZoom(monteria, 13f));

        this.mapa?.setOnMarkerClickListener(this)
        this.mapa?.setOnMapClickListener(this)

        refreshPosicionesPensiones()
    }

    fun establecerMiPosicionEnMapa() {

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

    fun refreshPosicionesPensiones() {
        pensiones = listOf(
            Pension(
                1,
                listOf(3000, 4000),
                3,
                "Crr 5 Cll 34",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                Pension.Sexos.MASCULINO,
                Persona(1, "Cristian", "Alvarez"),
                listOf(
                    Servicio(1, "Descripcion del servicio numero 2", Servicio.Tipos.PARQUEADERO),
                    Servicio(2,  "Descripcion del servicio numero 2", Servicio.Tipos.LAVADO_ROPA),
                    Servicio(3, "Descripcion del servicio numero 2", Servicio.Tipos.WIFI)
                ),
                listOf(
                    Restriccion(1, Restriccion.Tipos.MASCOTAS, Restriccion.Permisiones.NEGOCIABLE),
                    Restriccion(2, Restriccion.Tipos.VISITAS_CONYUGALES, Restriccion.Permisiones.NO_PERMITIDO),
                    Restriccion(3, Restriccion.Tipos.RUIDO, Restriccion.Permisiones.NO_PERMITIDO)
                ),
                Posicion(1, 8.7559054,-75.8882503)
            ),
            Pension(
                2,
                listOf(3400),
                3,
                "Crr 23 Cll 4",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                Pension.Sexos.FEMENINO,
                Persona(1, "Jose", "Ramirez"),
                listOf(
                    Servicio(1, "Descripcion del servicio numero 2", Servicio.Tipos.PARQUEADERO),
                    Servicio(2,  "Descripcion del servicio numero 2", Servicio.Tipos.LAVADO_ROPA),
                    Servicio(3, "Descripcion del servicio numero 2", Servicio.Tipos.WIFI)
                ),
                listOf(
                    Restriccion(1, Restriccion.Tipos.MASCOTAS, Restriccion.Permisiones.NEGOCIABLE),
                    Restriccion(2, Restriccion.Tipos.VISITAS_CONYUGALES, Restriccion.Permisiones.NO_PERMITIDO),
                    Restriccion(3, Restriccion.Tipos.RUIDO, Restriccion.Permisiones.NO_PERMITIDO)
                ),
                Posicion(2, 8.750603,-75.8879927)
            ),
            Pension(
                3,
                listOf(1000, 2000, 3000),
                3,
                "Crr 9 Cll 12",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                Pension.Sexos.MIXTO,
                Persona(1, "Juan", "Hernandez"),
                listOf(
                    Servicio(1, "Descripcion del servicio numero 2", Servicio.Tipos.PARQUEADERO),
                    Servicio(2,  "Descripcion del servicio numero 2", Servicio.Tipos.LAVADO_ROPA),
                    Servicio(3, "Descripcion del servicio numero 2", Servicio.Tipos.WIFI)
                ),
                listOf(
                    Restriccion(1, Restriccion.Tipos.MASCOTAS, Restriccion.Permisiones.NEGOCIABLE),
                    Restriccion(2, Restriccion.Tipos.VISITAS_CONYUGALES, Restriccion.Permisiones.NO_PERMITIDO),
                    Restriccion(3, Restriccion.Tipos.RUIDO, Restriccion.Permisiones.NO_PERMITIDO)
                ),
                Posicion(3, 8.7495426,-75.8899775)
            )
        );

        pensiones?.let {
            for (pension: Pension in it) {

                val markerOptions: MarkerOptions = pension.getMarkerOptions()

                val marker: Marker? = mapa?.addMarker(markerOptions);

                marker?.tag = pension.id;
            }
        }

    }

    override fun onMarkerClick(marker: Marker?): Boolean {

        val idPension: Int = marker?.tag as Int;

        pensiones?.let {

            for (pension in it) {
                if ( idPension == pension.id ) {
                    pensionSeleccionada = pension;
                    break;
                }
            }
        }

        pensionSeleccionada?.let {
            setDatosInfowindow(it)
        }

        return false;
    }

    private fun setDatosInfowindow(pension: Pension) {
        txtPreciosPension?.text = pension.getPrecioMinMax()
        txtPropietarioPension?.text = "${ pension.propietario.nombres } ${ pension.propietario.apellidos }"

        contInfowindo?.visibility = View.VISIBLE
    }

    override fun onMapClick(p0: LatLng?) {
        if ( contInfowindo?.visibility == View.VISIBLE ) {
            contInfowindo?.visibility = View.GONE
        }
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
