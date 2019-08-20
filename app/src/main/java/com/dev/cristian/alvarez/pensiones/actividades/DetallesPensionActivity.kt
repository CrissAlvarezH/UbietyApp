package com.dev.cristian.alvarez.pensiones.actividades

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.dev.cristian.alvarez.pensiones.R
import com.dev.cristian.alvarez.pensiones.fragments.ImagenFragment
import com.dev.cristian.alvarez.pensiones.fragments.RestriccionesFragment
import com.dev.cristian.alvarez.pensiones.fragments.ServiciosFragment
import com.dev.cristian.alvarez.pensiones.glide.GlideApp
import com.dev.cristian.alvarez.pensiones.modelos.Pension
import com.dev.cristian.alvarez.pensiones.modelos.Restriccion
import com.dev.cristian.alvarez.pensiones.modelos.Servicio
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_detalles_pension.*
import kotlinx.android.synthetic.main.activity_maps.*


class DetallesPensionActivity : AppCompatActivity(), OnMapReadyCallback {

    companion object {
        const val ARG_PENSION = "arg_pension"
    }

    private var viewPagerImgs: ViewPager? = null;
    private var txtDireccion: TextView? = null;
    private var txtNombrePropietario: TextView? = null;
    private var txtNumCupos: TextView? = null;
    private var txtSexo: TextView? = null;
    private var txtDescripcion: TextView? = null;
    private var txtPrecios: TextView? = null;
    private var imgPropietario: ImageView? = null;
    private var imgAmpliarMapa: ImageView? = null;
    private var mapviewPension: MapView? = null;
    private var tabs: TabLayout? = null;
    private var contFragmentsTabs: FrameLayout? = null;

    private var imgPageAdapter: ImgsPagerAdapter? = null;

    private var pension: Pension? = null;

    override fun onResume() {
        super.onResume()

        mapviewPension?.onResume();
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_pension)
        enlazarXML()

        mapviewPension?.onCreate(savedInstanceState);
        mapviewPension?.getMapAsync(this)

        pension = intent.getSerializableExtra(ARG_PENSION) as Pension;

        if ( pension == null ) {
            Toast.makeText(this, "Pensión no especificada", Toast.LENGTH_SHORT).show()
            finish()
            return;
        }

        setDatos(pension)

        tabs?.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {

                pension?.let {
                    if ( it.servicios != null && it.restricciones != null ) {

                        val fragment = when ( tab?.position ) {
                            0 -> ServiciosFragment.getInstancia(it.servicios)
                            1 -> RestriccionesFragment.getInstancia(it.restricciones)
                            else -> ServiciosFragment.getInstancia(it.servicios)
                        }

                        supportFragmentManager.beginTransaction()
                            .replace(R.id.cont_fragment_tabs_pension, fragment)
                            .commit()
                    }
                }
            }

        })

        // Por defecto ponemos el fragment de servicios
        pension?.let {

            supportFragmentManager.beginTransaction()
                .add(R.id.cont_fragment_tabs_pension, ServiciosFragment.getInstancia(it.servicios))
                .commit()
        }
    }

    private fun enlazarXML() {
        val toolbar: Toolbar = findViewById(R.id.toolbar_detalles_pension);

        setSupportActionBar(toolbar);

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = ""
        }

        viewPagerImgs = findViewById(R.id.viewpager_imagenes_pension);
        txtDireccion = findViewById(R.id.txt_direccion_pension);
        txtPrecios = findViewById(R.id.txt_precios_pension);
        txtNumCupos = findViewById(R.id.txt_num_cupos_pension);
        txtSexo = findViewById(R.id.txt_sexo_pension)
        txtDescripcion = findViewById(R.id.txt_descripcion_pension)

        imgPropietario = findViewById(R.id.img_propietario_pension);
        txtNombrePropietario = findViewById(R.id.txt_nombre_propietario_pension);

        mapviewPension = findViewById(R.id.mapview_pension);
        imgAmpliarMapa = findViewById(R.id.img_ampliar_mapa);

        tabs = findViewById(R.id.tabs_detalles_pension);
        contFragmentsTabs = findViewById(R.id.cont_fragment_tabs_pension);

    }

    private class ImgsPagerAdapter(fm: FragmentManager, val urlsImg: List<String>) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {

            return ImagenFragment.newInstance( urlsImg.get(position) );
        }

        override fun getCount(): Int {

            return urlsImg.size;
        }
    }

    private fun setImgs() {
        val urlImagenes = listOf(
            "https://i.ebayimg.com/images/g/TfEAAOSw3UZa65MA/s-l400.jpg",
            "https://vida-spyqpdxrgyld6rrkjib.netdna-ssl.com/wp-content/uploads/2018/10/Casas-en-Atia-Residencial-Queretaro-436x300.jpg",
            "https://i.ebayimg.com/images/g/0NUAAOSwQN5aejnd/s-l800.jpg"
        )

        imgPageAdapter = ImgsPagerAdapter(supportFragmentManager, urlImagenes)
        viewPagerImgs?.adapter = imgPageAdapter;
    }

    private fun setDatos(pension: Pension?) {

        pension?.let {

            setImgs()

            txtDireccion?.text = it.direccion;

            var precios: String = it.precios[0].toString();

            if ( it.precios.size > 1 )
                precios += " - ${ it.precios [ it.precios.size - 1 ] }"

            txtPrecios?.text = precios

            txtNumCupos?.text = "${ it.cupos } Cupos"

            txtSexo?.text = when (it.sexos) {
                Pension.Sexos.MASCULINO -> "Hombres"
                Pension.Sexos.FEMENINO -> "Mujeres"
                Pension.Sexos.MIXTO -> "Mixto"
                else -> "Mixto"
            }

            txtNombrePropietario?.text = "${ pension.propietario.nombres } ${ pension.propietario.apellidos }"

            imgPropietario?.let {

                GlideApp
                    .with(this)
                    .load("")
                    .into(it)
            }

            txtDescripcion?.text = it.descripion

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when ( item.itemId ) {
            android.R.id.home -> {
                finish()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        googleMap?.uiSettings?.isScrollGesturesEnabled = false;
    }

    override fun onDestroy() {
        super.onDestroy()

        mapviewPension?.onDestroy();
    }
}
