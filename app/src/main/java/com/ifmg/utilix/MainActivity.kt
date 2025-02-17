package com.ifmg.utilix

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ifmg.utilix.databinding.ActivityMainBinding
import com.ifmg.utilix.localdata.UserDAO

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var userDAO: UserDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Ativar View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userDAO = UserDAO(this)

        // Evento de clique no botão de login
        binding.loginBtn.setOnClickListener {
            val email = binding.usernameText.text.toString()
            val password = binding.passwordText.text.toString()

            if (userDAO.checkLogin(email, password)) {
                Toast.makeText(this, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Select::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Credenciais inválidas!", Toast.LENGTH_SHORT).show()
            }
        }

        // Ir para a tela de registro
        binding.signupBtn.setOnClickListener {
            val intent = Intent(this, Signup::class.java)
            startActivity(intent)
        }
    }
}
