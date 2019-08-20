package com.dev.cristian.alvarez.pensiones.modelos

import java.io.Serializable

data class Pension(val id: Int, val precios: List<Long>, val cupos: Int,
                   val direccion: String, val descripion: String,
                   val sexos: Int, val propietario: Persona,
                   val servicios: List<Servicio>, val restricciones: List<Restriccion>) : Serializable{

    class Sexos {
        companion object {
            const val MASCULINO = 1;
            const val FEMENINO = 2;
            const val MIXTO = 3;
        }
    }

}