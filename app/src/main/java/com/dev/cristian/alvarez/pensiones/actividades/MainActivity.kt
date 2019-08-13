package com.dev.cristian.alvarez.pensiones.actividades

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.dev.cristian.alvarez.pensiones.R
import com.dev.cristian.alvarez.pensiones.fragments.*
import com.facebook.AccessToken
import com.facebook.AccessTokenTracker
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val TAG = "ActividadPrincipal";
    private val ARG_TAB_INDEX = "tab_index";

    private var viewpager: ViewPager? = null;
    private var fragmentsAdapter: TabsPagerAdapter? = null;
    private var bottomMenu: BottomNavigationView? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Seteamos el tab por defecto que venga en la extras
        val indexTab = intent.getIntExtra(ARG_TAB_INDEX, 0);

        bottomMenu = findViewById<BottomNavigationView>(R.id.bottom_menu_main);
        viewpager = findViewById(R.id.viewpager);

        setFragmentEnViewpager();

        setListeners();

        viewpager?.setCurrentItem(indexTab);

        accesTokenTracker.startTracking();
    }

    private fun setListeners() {
        bottomMenu?.setOnNavigationItemSelectedListener {
                item ->

            val itemSelected: Int = when(item.itemId) {
                R.id.item_bottom_home -> 0
                R.id.item_bottom_mapa -> 1
                R.id.item_bottom_favoritos -> 2
                R.id.item_bottom_perfil -> 3
                else -> 0
            }

            this.viewpager?.setCurrentItem(itemSelected);

            true;
        }

        this.viewpager?.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                val itemId = when (position) {
                    0 -> R.id.item_bottom_home;
                    1 -> R.id.item_bottom_mapa;
                    2 -> R.id.item_bottom_favoritos;
                    3 -> R.id.item_bottom_perfil;
                    else -> R.id.item_bottom_home;
                }

                bottomMenu?.selectedItemId = itemId;
            }
        })
    }

    private fun setFragmentEnViewpager() {

        val fragments = mutableListOf<Fragment>(
            InicioFragment(),
            MapaFragment(),
            FavoritosFragment(),
            this.definirFragmentLoguin()
        );

        fragmentsAdapter = TabsPagerAdapter(
            fragments,
            supportFragmentManager
        );

        this.viewpager?.adapter = fragmentsAdapter;
    }

    private fun definirFragmentLoguin(): Fragment {
        val accessToken = AccessToken.getCurrentAccessToken()
        val isLoggedIn = accessToken != null && !accessToken.isExpired

        val fragmentPefil: Fragment;

        if (isLoggedIn) {
            fragmentPefil = PerfilArrendatarioFragment();
        } else {
            fragmentPefil = LoguinFragment();
        }

        return fragmentPefil;
    }

    private var accesTokenTracker: AccessTokenTracker = object: AccessTokenTracker() {

        override fun onCurrentAccessTokenChanged(oldAccessToken: AccessToken?, currentAccessToken: AccessToken?) {

            // Si el currentAccesToken está nulo quiere decir que no está logueado, por lo que cambiamos el fragment
            // que muestra el pelfil de usuario, para esto reiniciamos la actividad
            if ( currentAccessToken == null || oldAccessToken == null ) {
//                fragmentsAdapter?.cambiarFragment( // TODO Esto no funciona, no setea y actualiza el fragment
//                    definirFragmentLoguin(),
//                    3
//                );

                val reiniciarActividad = Intent(applicationContext, MainActivity::class.java);
                reiniciarActividad.putExtra(ARG_TAB_INDEX, 3);// Ponemos a que seleccione el fragment 3 del perfil
                startActivity(reiniciarActividad);
                finish();
            }

            Log.e(TAG, "oldAccessToken: "+oldAccessToken.toString()+", currentAccessToken: "+currentAccessToken.toString());

        }
    }

    class TabsPagerAdapter(val fragments: MutableList<Fragment>, fm: FragmentManager?) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return fragments.get(position);
        }

        override fun getCount(): Int {
            return this.fragments.size;
        }

        fun cambiarFragment(fragment: Fragment, posicion: Int) {
            this.fragments.set(posicion, fragment);

            notifyDataSetChanged();
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        accesTokenTracker.stopTracking();
    }
}
