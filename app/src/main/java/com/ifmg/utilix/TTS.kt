package com.ifmg.utilix

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.ifmg.utilix.databinding.ActivityTtsBinding
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfReader
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor
import org.apache.poi.xwpf.usermodel.XWPFDocument
import java.util.Locale

class TTS : AppCompatActivity(), TextToSpeech.OnInitListener {
    private lateinit var binding: ActivityTtsBinding
    private lateinit var textToSpeech: TextToSpeech
    private var ttsReady = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTtsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        textToSpeech = TextToSpeech(this, this)

        // Clique no botão para escolher o arquivo
        binding.buttonTTS.setOnClickListener {
            openFilePicker()
        }

        // Evento de clique no botão de voltar para a tela Select
        binding.backButton.setOnClickListener {
            finish() // Apenas fecha a tela atual e retorna para Select
        }
    }

    // Abre o seletor de arquivos
    private fun openFilePicker() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "*/*"
            putExtra(
                Intent.EXTRA_MIME_TYPES,
                arrayOf("text/plain", "application/pdf", "application/vnd.openxmlformats-officedocument.wordprocessingml.document")
            )
        }
        filePickerLauncher.launch(intent)
    }

    // Recebe o arquivo selecionado
    private val filePickerLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.let { uri ->
                    readTextFromUri(uri)
                }
            }
        }

    // Lê o conteúdo do arquivo e inicia o TTS
    private fun readTextFromUri(uri: Uri) {
        try {
            val mimeType = contentResolver.getType(uri)

            val text = when (mimeType) {
                "text/plain" -> readTxt(uri)
                "application/pdf" -> readPdfWithIText(uri)
                "application/vnd.openxmlformats-officedocument.wordprocessingml.document" -> readDocx(uri)
                else -> "Formato não suportado"
            }

            speakOut(text)
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Erro ao processar o arquivo!", Toast.LENGTH_SHORT).show()
        }
    }

    // Lê arquivos TXT
    private fun readTxt(uri: Uri): String {
        return contentResolver.openInputStream(uri)?.bufferedReader()?.use { it.readText() }
            ?: "Erro ao ler TXT"
    }

    // Lê arquivos PDF com iText PDF
    private fun readPdfWithIText(uri: Uri): String {
        return contentResolver.openInputStream(uri)?.use { inputStream ->
            val reader = PdfReader(inputStream)
            val pdfDocument = PdfDocument(reader)
            val text = StringBuilder()

            for (i in 1..pdfDocument.numberOfPages) {
                text.append(PdfTextExtractor.getTextFromPage(pdfDocument.getPage(i))).append("\n")
            }

            pdfDocument.close()
            reader.close()
            text.toString()
        } ?: "Erro ao ler PDF"
    }

    // Lê arquivos DOCX
    private fun readDocx(uri: Uri): String {
        return contentResolver.openInputStream(uri)?.use { inputStream ->
            XWPFDocument(inputStream).use { document ->
                document.paragraphs.joinToString("\n") { it.text }
            }
        } ?: "Erro ao ler DOCX"
    }

    // Inicialização do TTS
    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val localeBR = Locale("pt", "BR")
            if (textToSpeech.isLanguageAvailable(localeBR) >= TextToSpeech.LANG_AVAILABLE) {
                val result = textToSpeech.setLanguage(localeBR)

                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Toast.makeText(this, "Português do Brasil não está instalado no TTS!", Toast.LENGTH_LONG).show()
                } else {
                    ttsReady = true
                }
            } else {
                Toast.makeText(this, "Português do Brasil não disponível no dispositivo!", Toast.LENGTH_LONG).show()
            }
        } else {
            println("Falha ao inicializar TTS")
        }
    }

    // Executa o TTS
    private fun speakOut(text: String) {
        if (ttsReady) {
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
        } else {
            Toast.makeText(this, "Aguarde, TTS ainda não está pronto!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        if (::textToSpeech.isInitialized) {
            textToSpeech.stop()
            textToSpeech.shutdown()
        }
        super.onDestroy()
    }
}
