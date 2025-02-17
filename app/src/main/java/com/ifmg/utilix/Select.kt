package com.ifmg.utilix

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.ifmg.utilix.databinding.ActivitySelectBinding

class Select : AppCompatActivity() {
    private lateinit var binding: ActivitySelectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Navega para a tela de transcrição de documentos
        binding.button2.setOnClickListener {
            val intent = Intent(this, TTS::class.java)
            startActivity(intent)
        }

        // Navega para a tela de Text-to-Speech
        binding.button1.setOnClickListener {
            val intent = Intent(this, Transcript::class.java)
            startActivity(intent)
        }

        // Botão de ajuda - Exibe um popup com instruções
        binding.helpBtn.setOnClickListener {
            showHelpDialog()
        }
    }

    private fun showHelpDialog() {
        val alertDialog = AlertDialog.Builder(this)
            .setTitle("Help")
            .setMessage("Welcome to Utilix!\n\n" +
                    "- To convert documents into speech, click on 'Text-to-Speech'.\n" +
                    "- To convert files into plain text, click on 'Transcribe Documents'.\n" +
                    "- If you need more help, contact support.")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss() // Fecha o popup ao clicar em OK
            }
            .create()

        alertDialog.show()
    }
}
