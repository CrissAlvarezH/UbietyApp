package com.dev.cristian.alvarez.pensiones

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.dev.cristian.alvarez.pensiones.fragments.FavoritosFragment
import com.dev.cristian.alvarez.pensiones.fragments.InicioFragment
import com.dev.cristian.alvarez.pensiones.fragments.MapaFragment
import com.dev.cristian.alvarez.pensiones.fragments.PerfilFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private var viewpager: ViewPager? = null;
    private var fragmentsAdapter: FragmentPagerAdapter? = null;
    private var bottomMenu: BottomNavigationView? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomMenu = findViewById<BottomNavigationView>(R.id.bottom_menu_main);
        viewpager = findViewById(R.id.viewpager);

        setFragmentEnViewpager();

        setListeners();
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
        val fragments = listOf<Fragment>(
            InicioFragment(),
            MapaFragment(),
            FavoritosFragment(),
            PerfilFragment()
        );

        fragmentsAdapter = TabsPagerAdapter(fragments, supportFragmentManager);

        this.viewpager?.adapter = fragmentsAdapter;
    }

    class TabsPagerAdapter(val fragments: List<Fragment>, fm: FragmentManager?) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return fragments.get(position);
        }

        override fun getCount(): Int {
            return this.fragments.size;
        }

    }
}
