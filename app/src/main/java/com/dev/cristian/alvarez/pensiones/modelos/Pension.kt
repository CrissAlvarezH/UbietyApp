package com.dev.cristian.alvarez.pensiones.modelos

data class Pension(val id: Int, val imgs: List<String>, val precios: List<Long>, val cupos: Int,
                   val direccion: String, val propietario: Persona) {
}