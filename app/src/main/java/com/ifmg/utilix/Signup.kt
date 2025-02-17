package com.ifmg.utilix

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ifmg.utilix.databinding.ActivitySignupBinding
import com.ifmg.utilix.localdata.UserDAO

class Signup : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var userDAO: UserDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Ativar View Binding
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userDAO = UserDAO(this)

        // Evento de clique para registrar usuário
        binding.entrarBtn.setOnClickListener {
            val name = binding.nameText.text.toString()
            val email = binding.emailText.text.toString()
            val password = binding.passwordText.text.toString()

            if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                if (userDAO.registerUser(name, email, password)) {
                    Toast.makeText(this, "Usuário registrado com sucesso!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Erro ao registrar usuário!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            }
        }

        // Evento de clique para voltar à tela de login
        binding.backButton.setOnClickListener {
            finish() // Apenas fecha a tela atual e volta para a MainActivity
        }
    }
}
