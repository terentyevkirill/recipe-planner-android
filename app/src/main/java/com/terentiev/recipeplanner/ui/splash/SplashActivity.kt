package com.terentiev.recipeplanner.ui.splash

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.transition.Explode
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.terentiev.recipeplanner.MainActivity
import com.terentiev.recipeplanner.R
import com.terentiev.recipeplanner.ui.login.LoginActivity


class SplashActivity : AppCompatActivity() {
    private lateinit var viewModel: SplashViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        val window: Window = window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.setStatusBarColor(Color.TRANSPARENT)
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        getWindow().exitTransition = Explode()
        setTheme(R.style.Theme_AppCompat_NoActionBar)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        viewModel =
            ViewModelProviders.of(this).get(SplashViewModel::class.java)
        Handler().postDelayed({
            viewModel.checkToken()
            viewModel.getAuthResultLiveData().observe(this, Observer { result ->
                val i: Intent = if (result) {
                    Intent(this, MainActivity::class.java)
                } else {
                    Intent(this, LoginActivity::class.java)
                }
                startActivity(i)
            })
        }, 500)
    }
}