package com.paolazanella.loginapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    lateinit var btn_login: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        btn_login= findViewById(R.id.btn_login)
        btn_login.setOnClickListener {

            IrParaLogin()
        }
    }
    private fun IrParaLogin(){
        val telaLogin = Intent(this, homeActivity::class.java)
        startActivity(telaLogin)
    }
}

