package com.dev.cristian.alvarez.pensiones.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dev.cristian.alvarez.pensiones.R
import com.dev.cristian.alvarez.pensiones.glide.GlideApp
import com.dev.cristian.alvarez.pensiones.modelos.Pension

class PensionesAdapter(pensiones: List<Pension>, val contexto: Context?, var listener: ListenerClick)
    : RecyclerView.Adapter<PensionesAdapter.PensionesViewHolder>() {

    private var pensiones = pensiones
        set(pen) {
            field = pen;
            notifyDataSetChanged();
        }

    interface ListenerClick {
        fun clickItem(pension: Pension, posicion: Int);
    }

    inner class PensionesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        val txtNumCupos = itemView.findViewById<TextView>(R.id.item_pension_num_cupos);
        val txtPrecios = itemView.findViewById<TextView>(R.id.item_pension_precios);
        val txtNombrePersona = itemView.findViewById<TextView>(R.id.item_pension_nombre_persona);
        val txtDireccion = itemView.findViewById<TextView>(R.id.item_pension_direccion);
        val img1 = itemView.findViewById<ImageView>(R.id.item_pension_img_1);
        val img2 = itemView.findViewById<ImageView>(R.id.item_pension_img_2);
        val img3 = itemView.findViewById<ImageView>(R.id.item_pension_img_3);
        val imgPersona = itemView.findViewById<ImageView>(R.id.item_pension_img_persona);

        val contItem = itemView.findViewById<LinearLayout>(R.id.layout_cont_item_pension);

        init {
            contItem.setOnClickListener(this);
        }

        override fun onClick(view: View?) {

            listener.clickItem(
                pensiones[adapterPosition],
                adapterPosition
            );

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PensionesViewHolder {
        val view = LayoutInflater.from( parent.context )
            .inflate(R.layout.item_pension, parent, false);

        return PensionesViewHolder(view);
    }

    override fun onBindViewHolder(holder: PensionesViewHolder, position: Int) {
        val pension = pensiones[position];

        holder.txtNumCupos.text = "${ pension.cupos } Cupos";
        holder.txtDireccion.text = pension.direccion;

        // Establecemos el precio minimo y el maximo (La lista está ordenada de mayor a menor)
        var precios = "$${ pension.precios[0] }";

        if ( pension.precios.size > 1 ) {
            precios += " - $${ pension.precios[ pension.precios.size - 1 ] }";
        }

        holder.txtPrecios.text = precios;

        // Establecemos los datos de la persona propietaria
        holder.txtNombrePersona.text = "${ pension.propietario.nombres } ${ pension.propietario.apellidos }";

        contexto?.let {
            GlideApp
                .with(it)
                .load("https://e00-telva.uecdn.es/imagenes/2015/08/04/estilo_de_vida/1438695367_extras_noticia_head_moviles_0.jpg")
                .into(holder.imgPersona);

            GlideApp
                .with(it)
                .load("https://i.ebayimg.com/images/g/TfEAAOSw3UZa65MA/s-l400.jpg")
                .into(holder.img1);

            GlideApp
                .with(it)
                .load( "https://vida-spyqpdxrgyld6rrkjib.netdna-ssl.com/wp-content/uploads/2018/10/Casas-en-Atia-Residencial-Queretaro-436x300.jpg" )
                .into( holder.img2 );

            GlideApp
                .with(it)
                .load( "https://i.ebayimg.com/images/g/0NUAAOSwQN5aejnd/s-l800.jpg" )
                .into( holder.img3 );
        }

    }

    override fun getItemCount(): Int {
        return pensiones.size;
    }


}