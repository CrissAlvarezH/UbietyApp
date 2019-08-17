package com.dev.cristian.alvarez.pensiones.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.dev.cristian.alvarez.pensiones.R
import com.dev.cristian.alvarez.pensiones.adapters.RestriccionesAdapter
import com.dev.cristian.alvarez.pensiones.modelos.Restriccion
import java.io.Serializable

private const val ARG_RESTRICCIONES = "arg_restricciones"

class RestriccionesFragment : Fragment() {

    private var restricciones: List<Restriccion>? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        restricciones = arguments?.getSerializable(ARG_RESTRICCIONES) as List<Restriccion>
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val vista = inflater.inflate(R.layout.fragment_restricciones, container, false)

        val recyclerRestricciones: RecyclerView = vista.findViewById(R.id.recycler_restricciones);

        val lmRestricciones = LinearLayoutManager(context);
        recyclerRestricciones.layoutManager = lmRestricciones

        restricciones?.let {
            val restriccionesAdapter = RestriccionesAdapter(it, null)
            recyclerRestricciones.adapter = restriccionesAdapter
        }

        return vista;
    }

    companion object {

        fun getInstancia(restricciones: List<Restriccion>) : RestriccionesFragment {
            return RestriccionesFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_RESTRICCIONES, restricciones as Serializable)
                }
            }
        }

    }

}
