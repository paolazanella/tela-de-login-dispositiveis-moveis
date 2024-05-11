package com.paolazanella.loginapp

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
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

        btn = findViewById(R.id.tirar_foto_btn)
        img = findViewById(R.id.imagem)

        btn.setOnClickListener {
            val tirarFoto = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            resultContract.launch(tirarFoto)
        }
    }

    private val resultContract = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
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

}
