package com.dev.cristian.alvarez.pensiones.fragments

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
import com.dev.cristian.alvarez.pensiones.adapters.PensionesAdapter
import com.dev.cristian.alvarez.pensiones.modelos.Pension
import com.dev.cristian.alvarez.pensiones.modelos.Persona


class InicioFragment : Fragment(), PensionesAdapter.ListenerClick {

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
                listOf("https://i.ebayimg.com/images/g/TfEAAOSw3UZa65MA/s-l400.jpg", "https://vida-spyqpdxrgyld6rrkjib.netdna-ssl.com/wp-content/uploads/2018/10/Casas-en-Atia-Residencial-Queretaro-436x300.jpg", "https://i.ebayimg.com/images/g/0NUAAOSwQN5aejnd/s-l800.jpg"),
                listOf(3000, 4000),
                3,
                "Crr 5 Cll 34",
                Persona(1, "Cristian", "Alvarez")
            ),
            Pension(
                2,
                listOf("https://i.ebayimg.com/images/g/0NUAAOSwQN5aejnd/s-l800.jpg", "https://vida-spyqpdxrgyld6rrkjib.netdna-ssl.com/wp-content/uploads/2018/10/Casas-en-Atia-Residencial-Queretaro-436x300.jpg", "https://i.ebayimg.com/images/g/TfEAAOSw3UZa65MA/s-l400.jpg"),
                listOf(3400),
                3,
                "Crr 23 Cll 4",
                Persona(1, "Jose", "Ramirez")
            ),
            Pension(
                3,
                listOf("https://vida-spyqpdxrgyld6rrkjib.netdna-ssl.com/wp-content/uploads/2018/10/Casas-en-Atia-Residencial-Queretaro-436x300.jpg", "https://i.ebayimg.com/images/g/0NUAAOSwQN5aejnd/s-l800.jpg", "https://i.ebayimg.com/images/g/TfEAAOSw3UZa65MA/s-l400.jpg"),
                listOf(1000, 2000, 3000),
                3,
                "Crr 9 Cll 12",
                Persona(1, "Juan", "Hernandez")
            )
        );

        pensionesAdapter = PensionesAdapter(pensiones, context, this);
        recyclerPensiones.adapter = pensionesAdapter;

        return vista;
    }

    override fun clickItem(pension: Pension, posicion: Int) {

    }
}
