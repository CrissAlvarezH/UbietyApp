package com.dev.cristian.alvarez.pensiones.utils

import android.Manifest
import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.content.pm.PackageManager.PERMISSION_GRANTED
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.checkSelfPermission

class PermisosUtils {

    companion object {

        fun validarPermisosFaltantes(contexto: Activity, cod: Int, pedir: Boolean): Boolean {

            val permisoLocalizacionCoarse = checkSelfPermission(contexto, ACCESS_COARSE_LOCATION) != PERMISSION_GRANTED;
            val permisoLocalizacionFine = checkSelfPermission(contexto, ACCESS_FINE_LOCATION) != PERMISSION_GRANTED

            val listPermisos: MutableList<String> = mutableListOf();

            if (permisoLocalizacionCoarse) listPermisos.add(ACCESS_COARSE_LOCATION);
            if (permisoLocalizacionFine) listPermisos.add(ACCESS_FINE_LOCATION)

            val estanTodosConsedidos = listPermisos.isEmpty();

            if ( pedir && !estanTodosConsedidos ) {
                ActivityCompat.requestPermissions(
                    contexto,
                    listPermisos.toTypedArray(),
                    cod
                )
            }

            return estanTodosConsedidos;
        }

    }

}