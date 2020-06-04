package com.terentiev.recipeplanner.ui.login

import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import com.terentiev.recipeplanner.MainActivity

import com.terentiev.recipeplanner.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        sign_in.setOnClickListener {
            loading.visibility = View.VISIBLE
            viewModel.loginAttempt(username.text.toString(), password.text.toString())
        }

        viewModel.getLoginResultLiveData().observe(this, Observer { result ->
            loading.visibility = View.GONE
            if (result) {
                val i = Intent(this, MainActivity::class.java)
                startActivity(i)
            } else {
                Toast.makeText(this, "User credentials are incorrect!", Toast.LENGTH_SHORT).show();
            }
        })
    }
}