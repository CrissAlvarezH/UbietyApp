package com.dev.cristian.alvarez.pensiones.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import com.dev.cristian.alvarez.pensiones.R
import com.dev.cristian.alvarez.pensiones.glide.GlideApp


private const val ARG_URL_IMG = "url_img"

class ImagenFragment : Fragment() {
    private var urlImg: String? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        urlImg = arguments?.getString(ARG_URL_IMG);
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val vista = inflater.inflate(R.layout.fragment_imagen, container, false)

        val img: ImageView = vista.findViewById(R.id.img_fragment);

        GlideApp
            .with(this)
            .load(urlImg)
            .into(img);

        return vista;
    }


    companion object {

        @JvmStatic
        fun newInstance(urlImg: String): ImagenFragment {

            return ImagenFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_URL_IMG, urlImg)
                };
            }
        }
    }
}
