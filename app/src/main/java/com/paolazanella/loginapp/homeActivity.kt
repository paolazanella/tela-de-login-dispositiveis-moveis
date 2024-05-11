package com.paolazanella.loginapp

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AlertDialog



class homeActivity : AppCompatActivity() {

    lateinit var cpfInput: EditText
    lateinit var passwordInput: EditText // Corrigindo o nome da variável
    lateinit var loginBtn: Button

    // Credenciais corretas
    val CORRECT_CPF = "08993356912"
    val CORRECT_SENHA = "123456"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        cpfInput = findViewById(R.id.cpf_input)
        passwordInput = findViewById(R.id.password_input)
        loginBtn = findViewById(R.id.login_btn)

        loginBtn.setOnClickListener{
            val username= cpfInput.text.toString()
            val senha = passwordInput.text.toString()

            if (username == CORRECT_CPF && senha == CORRECT_SENHA){
                TerceiraTela()
            } else {mostrarAlertaInformativo()}
        }

        val mostrarSenhaCheckbox = findViewById<CheckBox>(R.id.ckb_mostrar_senha)
        mostrarSenhaCheckbox.setOnCheckedChangeListener { buttonView, isChecked ->
            // Chama a função para mostrar ou ocultar a senha quando o estado do CheckBox é alterado
            mostrarOcultarSenha(isChecked)
        }


    }
    private fun TerceiraTela(){
        val terceiraTela = Intent(this, terceiraTelaActivity::class.java)
        startActivity(terceiraTela)
    }

    private fun mostrarAlertaInformativo() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Credenciais Incorretas")
        builder.setMessage("CPF ou senha incorretos. Por favor, tente novamente.")
        builder.setPositiveButton("OK") { dialog, which ->
            // Este bloco de código será executado quando o usuário clicar no botão "OK"
            dialog.dismiss() // Fecha o diálogo
        }
        val dialog = builder.create()
        dialog.show()
    }
    private fun mostrarOcultarSenha(mostrar: Boolean) {
        if (mostrar) {
            // Se o CheckBox estiver marcado, mostra a senha
            passwordInput.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        } else {
            // Se o CheckBox estiver desmarcado, oculta a senha
            passwordInput.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
    }

}
