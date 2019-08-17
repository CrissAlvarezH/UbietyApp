package com.dev.cristian.alvarez.pensiones.modelos

import java.io.Serializable

data class Pension(val id: Int, val precios: List<Long>, val cupos: Int,
                   val direccion: String, val propietario: Persona) : Serializable{
}