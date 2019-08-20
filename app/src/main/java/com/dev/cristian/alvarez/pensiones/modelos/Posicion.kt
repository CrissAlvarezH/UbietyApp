package com.dev.cristian.alvarez.pensiones.modelos

import com.google.android.gms.maps.model.LatLng
import java.io.Serializable

data class Posicion (val id: Int, val latitud: Double, val longitud: Double): Serializable {
    fun getLatLng(): LatLng {
        return LatLng(latitud, longitud)
    }
}