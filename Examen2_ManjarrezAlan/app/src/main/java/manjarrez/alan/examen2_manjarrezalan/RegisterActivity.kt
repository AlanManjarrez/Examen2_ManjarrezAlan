package manjarrez.alan.examen2_manjarrezalan

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        val etName: EditText = findViewById(R.id.etName)
        val etLastName: EditText = findViewById(R.id.etLastName)
        val etNumber: EditText = findViewById(R.id.etNumber)
        val etEmail: EditText = findViewById(R.id.etEmail)
        val ivRandomColor: ImageView = findViewById(R.id.ivRandomColor)
        val btnSaveContact: Button = findViewById(R.id.btnSaveContact)

        val randomColor = getRandomColor()

        ivRandomColor.setBackgroundColor(Color.parseColor(randomColor))

        btnSaveContact.setOnClickListener {
            val name = etName.text.toString()
            val lastName = etLastName.text.toString()
            val phone = etNumber.text.toString()
            val email = etEmail.text.toString()

            if (name.isNotEmpty() && lastName.isNotEmpty() && phone.isNotEmpty() && email.isNotEmpty()) {
                val contact = Contact(name, lastName, phone, email, randomColor)

                Toast.makeText(this, "Contacto guardado: ${contact.name}", Toast.LENGTH_SHORT).show()

                finish()
            } else {
                Toast.makeText(this, "Por favor completa todos los campos.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getRandomColor(): String {
        val red = Random.nextInt(256)
        val green = Random.nextInt(256)
        val blue = Random.nextInt(256)
        return String.format("#%02X%02X%02X", red, green, blue)
    }
}