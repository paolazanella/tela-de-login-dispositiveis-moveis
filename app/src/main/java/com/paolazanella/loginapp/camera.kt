package com.paolazanella.loginapp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
class camera : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var cpfTextView: TextView // Declarar a TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        val btnCamera = findViewById<Button>(R.id.btnCamera)
        imageView = findViewById(R.id.imageView)
        cpfTextView = findViewById(R.id.cpf_value_textview) // Inicializar a TextView

        // Obtém o CPF do extra da Intent
        val cpf = intent.getStringExtra("CPF")
        val cpfFormatado = cpf?.formatarCPF() ?: ""

        // Define o CPF formatado na TextView
        cpfTextView.text = cpfFormatado


        btnCamera.setOnClickListener {
            openGallery()
        }
    }

    private val resultContract = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val imageUri: Uri? = data?.data
            imageView.setImageURI(imageUri)
        }
    }

    private fun openGallery() {
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        resultContract.launch(gallery)
    }
    fun String.formatarCPF(): String {
        return if (length == 11) {
            "${substring(0, 3)}.${substring(3, 6)}.${substring(6, 9)}-${substring(9)}"
        } else {
            this
        }
    }
}