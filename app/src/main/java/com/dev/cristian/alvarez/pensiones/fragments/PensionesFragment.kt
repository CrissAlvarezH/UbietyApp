package com.dev.cristian.alvarez.pensiones.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev.cristian.alvarez.pensiones.R
import com.dev.cristian.alvarez.pensiones.actividades.DetallesPensionActivity
import com.dev.cristian.alvarez.pensiones.adapters.PensionesAdapter
import com.dev.cristian.alvarez.pensiones.modelos.*


class PensionesFragment : Fragment(), PensionesAdapter.ListenerClick {

    private var pensionesAdapter: PensionesAdapter? = null;
    private var pensiones: List<Pension> = listOf();

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val vista: View = inflater.inflate(R.layout.fragment_inicio, container, false);

        val edtBuscar = vista.findViewById<EditText>(R.id.edt_buscar_pension_inicio);
        val recyclerPensiones = vista.findViewById<RecyclerView>(R.id.recycler_pensiones);

        val lmPensiones = LinearLayoutManager(context);
        recyclerPensiones.layoutManager = lmPensiones;

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

        pensionesAdapter = PensionesAdapter(pensiones, context, this);
        recyclerPensiones.adapter = pensionesAdapter;

        return vista;
    }

    override fun clickItem(pension: Pension, posicion: Int) {
        val irDetallesPension = Intent(context, DetallesPensionActivity::class.java);
        irDetallesPension.putExtra(DetallesPensionActivity.ARG_PENSION, pension)
        startActivity(irDetallesPension)
    }
}
