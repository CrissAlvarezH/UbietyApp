package com.dev.cristian.alvarez.pensiones.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dev.cristian.alvarez.pensiones.R
import com.dev.cristian.alvarez.pensiones.modelos.Servicio

class ServiciosAdapter (servicios: List<Servicio>, val listener: ListenerClick?) : RecyclerView.Adapter<ServiciosAdapter.ServiciosViewHolder>() {

    private var servicios = servicios
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    interface ListenerClick {
        fun clickServicio(servicio: Servicio, posicion: Int)
    }

    inner class ServiciosViewHolder(item: View): RecyclerView.ViewHolder(item) {

        val imgLogo: ImageView = item.findViewById(R.id.item_img_logo_servicio);
        val txtNombre: TextView = item.findViewById(R.id.item_txt_nombre_servicio)
        val txtDescripcion: TextView = item.findViewById(R.id.item_txt_descripcion_servicio);
        val contItem: LinearLayout = item.findViewById(R.id.cont_item_servicio)

        init {
            contItem.setOnClickListener {
                listener?.clickServicio(
                    servicios[adapterPosition],
                    adapterPosition
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiciosViewHolder {
        val item = LayoutInflater.from( parent.context )
                    .inflate(R.layout.item_servicio, parent, false);

        return ServiciosViewHolder(item)
    }

    override fun onBindViewHolder(holder: ServiciosViewHolder, position: Int) {
        val servicio = servicios[position];

        holder.txtDescripcion.text = servicio.descripcion;

        val resLogo: Int;
        val nombre: String;

        when (servicio.tipo) {
            Servicio.Tipos.WIFI -> {
                resLogo = R.drawable.ic_wifi
                nombre = "Internet"
            }
            Servicio.Tipos.COMIDA -> {
                resLogo = R.drawable.ic_comida
                nombre = "Comida"
            }
            Servicio.Tipos.TV -> {
                resLogo = R.drawable.ic_tv
                nombre = "Televisión"
            }
            Servicio.Tipos.LAVADO_ROPA -> {
                resLogo = R.drawable.ic_lavado_ropa
                nombre = "Lavado de ropa"
            }
            Servicio.Tipos.PARQUEADERO -> {
                resLogo = R.drawable.ic_parking
                nombre = "Parqueadero"
            }
            Servicio.Tipos.AIRE_ACONDICIONADO -> {
                resLogo = R.drawable.ic_aire_acondicionado
                nombre = "Aire acondicionado"
            }
            Servicio.Tipos.ENTREGA_DE_LLAVES -> {
                resLogo = R.drawable.ic_llaves
                nombre = "Entrega de llaves"
            }
            else -> {
                resLogo = R.drawable.ic_wifi
                nombre = "Internet"
            }
        }

        holder.imgLogo.setImageResource(resLogo)
        holder.txtNombre.text = nombre;

    }

    override fun getItemCount(): Int {
        return servicios.size
    }

}