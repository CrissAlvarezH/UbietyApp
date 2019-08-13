package com.dev.cristian.alvarez.pensiones.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.dev.cristian.alvarez.pensiones.R
import com.facebook.*


class LoguinFragment : Fragment() {

    // Callback que se encarga de escuchar los inicio de sesión
    private var callbackManagerFacebook: CallbackManager? = null;
    // Tracker de cuando cambian los datos del usuario
    private var profileTrackerFacebook: ProfileTracker? = null;
    // Perfil actualmente logueado (si es que lo hay)
    private var profileFacebook: Profile? = null;

    private var loginButtonFacebook: LoginButton? = null;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val vista: View = inflater.inflate(R.layout.fragment_loguin, container, false);

        loginButtonFacebook = vista.findViewById(R.id.login_button_facebook);
        loginButtonFacebook?.setReadPermissions("email");
        loginButtonFacebook?.setFragment(this);

        setUpCallbackManagerFacebook();


        return vista;
    }

    private fun setUpCallbackManagerFacebook() {
        callbackManagerFacebook = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManagerFacebook, object: FacebookCallback<LoginResult> {

            override fun onSuccess(result: LoginResult?) {

                val accessToken = AccessToken.getCurrentAccessToken()
                val isLoggedIn = accessToken != null && !accessToken.isExpired

                if (isLoggedIn) {

                    if ( Profile.getCurrentProfile() == null ) {

                        profileTrackerFacebook = object: ProfileTracker() {

                            override fun onCurrentProfileChanged(oldProfile: Profile?, currentProfile: Profile?) {

                                profileFacebook = currentProfile;

                                profileTrackerFacebook?.startTracking();
                            }
                        }

                    } else {

                        profileFacebook = Profile.getCurrentProfile();
                    }

                }
            }

            override fun onCancel() {
                Toast.makeText(context, "Login cancelado", Toast.LENGTH_SHORT).show();
            }

            override fun onError(error: FacebookException?) {
                Toast.makeText(context, "Ocurrió un inconveniente en el loguin, vuelva a intentar", Toast.LENGTH_SHORT).show();

                error?.printStackTrace()
            }

        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManagerFacebook?.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data)
    }
}
