package com.dev.cristian.alvarez.pensiones.modelos

import com.dev.cristian.alvarez.pensiones.R
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.io.Serializable

class Pension(val id: Int, val precios: List<Long>, val cupos: Int,
                   val direccion: String, val descripion: String,
                   val sexos: Int, val propietario: Persona,
                   val servicios: List<Servicio>, val restricciones: List<Restriccion>,
                   val posicion: Posicion) : Serializable{

    class Sexos {
        companion object {
            const val MASCULINO = 1;
            const val FEMENINO = 2;
            const val MIXTO = 3;
        }
    }

    fun getPrecioMinMax(): String {
        var preciosMinMax = "$${ precios[0] }";

        if ( precios.size > 1 )
            preciosMinMax += " - $${ precios [ precios.size - 1 ] }"

        return preciosMinMax
    }

    fun getMarkerOptions(): MarkerOptions {
        return MarkerOptions()
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_pos))
            .position(LatLng(posicion.latitud, posicion.longitud))
            .title(direccion);
    }

}