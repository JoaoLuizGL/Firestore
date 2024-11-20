package br.unisanta.firestore

import android.icu.text.DateFormat
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.unisanta.firestore.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import java.time.Instant
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = Firebase.firestore
        binding.btnSalvar.setOnClickListener{
            val nome = binding.edtNome.text.toString()
            val idade = binding.edtIdade.text.toString().toInt()
            val aluno = hashMapOf(
                "Nome" to nome,
                "Idade" to idade
            )
            db.collection("Alunos").document(nome)
                .set(aluno)
                .addOnSuccessListener{
                    Toast.makeText(this, "Gravou!", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener{
                    Toast.makeText(this, "Deu ruim...", Toast.LENGTH_SHORT).show()
                }
        }
    }
}