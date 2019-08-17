package com.dev.cristian.alvarez.pensiones.modelos

import java.io.Serializable

class Servicio (val id: Int, val descripcion: String, val tipo: Int): Serializable {

    class Tipos {
        companion object {
            const val WIFI = 1
            const val COMIDA = 2
            const val TV = 3
            const val LAVADO_ROPA = 4
            const val PARQUEADERO = 5
            const val AIRE_ACONDICIONADO = 6
            const val ENTREGA_DE_LLAVES = 7
        }
    }


}