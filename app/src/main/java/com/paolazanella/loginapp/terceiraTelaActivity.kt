package com.paolazanella.loginapp

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class terceiraTelaActivity : AppCompatActivity() {

    lateinit var btn: Button
    lateinit var img: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terceira_tela)

        img = findViewById(R.id.imagem)
        img.setImageResource(R.drawable.mcqueen)

        btn = findViewById(R.id.tirar_foto_btn)
        btn.setOnClickListener {
            val tirarFoto = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            resultContract.launch(tirarFoto)
        }

        val btnSeleciarFoto = findViewById<Button>(R.id.select_image_button)

        btnSeleciarFoto.setOnClickListener {
            abrirGallery()
        }
    }

    private val resultContract =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val bitmap = data?.extras?.get("data") as? Bitmap
                if (bitmap != null) {
                    // Mostrar a imagem na ImageView
                    img.setImageBitmap(bitmap)
                } else {
                    // Se a bitmap for nula, há algum problema na captura da foto
                    Log.e("terceiraTelaActivity", "Erro: Foto não foi capturada corretamente.")
                }
            }
        }

    private val resultContractGaçeria = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val imageUri: Uri? = data?.data
                img.setImageURI(imageUri)

            }
        }
    private fun abrirGallery(){
        val galeria = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI )
        resultContractGaçeria.launch(galeria)
    }
}

