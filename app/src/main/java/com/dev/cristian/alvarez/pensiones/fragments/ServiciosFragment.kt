package com.dev.cristian.alvarez.pensiones.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.dev.cristian.alvarez.pensiones.R
import com.dev.cristian.alvarez.pensiones.adapters.ServiciosAdapter
import com.dev.cristian.alvarez.pensiones.modelos.Servicio
import java.io.Serializable


private const val TAG = "FragmentServicios";
private const val ARG_SERVICIOS = "arg_servicios"


class ServiciosFragment : Fragment() {

    private var servicios: List<Servicio>? = null;
    private var serviciosAdapter: ServiciosAdapter? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        servicios = arguments?.getSerializable(ARG_SERVICIOS) as List<Servicio>;
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val vista = inflater.inflate(R.layout.fragment_servicios, container, false)

        val recyclerServicios: RecyclerView = vista.findViewById(R.id.recycler_servicios);

        val lmServicios = LinearLayoutManager(context);
        recyclerServicios.layoutManager = lmServicios
        recyclerServicios.isNestedScrollingEnabled = false;

        servicios?.let {
            val adapterServicios = ServiciosAdapter(it, null)
            recyclerServicios.adapter = adapterServicios
        }

        Log.v(TAG, "Lista de servicios: ${ servicios }")

        return vista;
    }

    companion object {

        fun getInstancia(servicios: List<Servicio>): ServiciosFragment {
            return ServiciosFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_SERVICIOS, servicios as Serializable)
                }
            }
        }
    }

}
