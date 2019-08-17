package com.dev.cristian.alvarez.pensiones.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.dev.cristian.alvarez.pensiones.R
import com.dev.cristian.alvarez.pensiones.modelos.Restriccion
import kotlinx.android.synthetic.main.item_restriccion.view.*

class RestriccionesAdapter (restricciones: List<Restriccion>, val listener: ListenerClick?) : RecyclerView.Adapter<RestriccionesAdapter.RestriccionesViewHolder>() {

    private var restricciones = restricciones
        set(value) {
            field = value;
            notifyDataSetChanged()
        }

    interface ListenerClick {
        fun clickRestriccion(restriccion: Restriccion, posicion: Int)
    }

    inner class RestriccionesViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        val imgLogo: ImageView = item.findViewById(R.id.item_img_logo_restriccion);
        val txtNombre: TextView = item.findViewById(R.id.item_txt_nombre_restriccion);
        val txtPermision: TextView = item.findViewById(R.id.item_txt_permision_restriccion);
        val cardContPermision: CardView = item.findViewById(R.id.item_card_cont_permision)
        val contItem: LinearLayout = item.findViewById(R.id.cont_item_restriccion);

        init {
            contItem.setOnClickListener {

                listener?.clickRestriccion(
                    restricciones[adapterPosition],
                    adapterPosition
                )
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestriccionesViewHolder {
        val item: View = LayoutInflater.from(parent.context)
                            .inflate(R.layout.item_restriccion, parent, false);

        return RestriccionesViewHolder(item)
    }

    override fun onBindViewHolder(holder: RestriccionesViewHolder, position: Int) {
        val restriccion = restricciones[position];

        val resColor: Int;
        val textRestriccion: String;

        when (restriccion.permision) {
            Restriccion.Permisiones.PERMITIDO -> {
                resColor = R.color.verde
                textRestriccion = "Permitido"
            }
            Restriccion.Permisiones.NEGOCIABLE -> {
                resColor = R.color.morado
                textRestriccion = "Negociable"
            }
            Restriccion.Permisiones.NO_PERMITIDO -> {
                resColor = R.color.rojo
                textRestriccion = "No permitido"
            }
            else -> {
                resColor = R.color.verde
                textRestriccion = ""
            }
        }

        val nombre: String;
        val resLogo: Int;

        when (restriccion.tipo) {
            Restriccion.Tipos.MASCOTAS -> {
                resLogo = R.drawable.ic_mascotas
                nombre = "Mascotas"
            }
            Restriccion.Tipos.VISITAS_CONYUGALES -> {
                resLogo = R.drawable.ic_visita_conyugal
                nombre = "Visitas conyugales"
            }
            Restriccion.Tipos.RUIDO -> {
                resLogo = R.drawable.ic_volume_up
                nombre = "Ruido"
            }
            else -> {
                resLogo = R.drawable.ic_mascotas
                nombre = "Mascotas"
            }
        }

        holder.txtPermision.setBackgroundResource(resColor)
        holder.txtPermision.text = textRestriccion;
        holder.imgLogo.setImageResource(resLogo)
        holder.txtNombre.text = nombre

    }

    override fun getItemCount(): Int {
        return restricciones.size
    }

}