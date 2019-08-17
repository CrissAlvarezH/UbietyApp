package com.dev.cristian.alvarez.pensiones.modelos

import java.io.Serializable

data class Restriccion (val id: Int, val tipo: Int, val permision: Int): Serializable {

    class Tipos {
        companion object {
            const val MASCOTAS = 1;
            const val VISITAS_CONYUGALES = 2;
            const val RUIDO = 3;
        }
    }

    class Permisiones {
        companion object {
            const val PERMITIDO = 1;
            const val NEGOCIABLE = 2;
            const val NO_PERMITIDO = 3;
        }
    }

}