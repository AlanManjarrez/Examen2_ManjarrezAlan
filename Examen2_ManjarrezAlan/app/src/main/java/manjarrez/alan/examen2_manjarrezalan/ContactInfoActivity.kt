package manjarrez.alan.examen2_manjarrezalan

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ContactInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_contact_info)

        val contact = intent.getSerializableExtra("contact") as? Contact

        if (contact != null) {
            findViewById<TextView>(R.id.tvContactNameInformation).text = contact.name
            findViewById<TextView>(R.id.tvContactWorkInformation).text = contact.work
            findViewById<TextView>(R.id.tvContactNumberInformation).text = contact.number
            findViewById<TextView>(R.id.tvContactEmailInformation).text = contact.email

            val colorResId = Color.parseColor(contact.color)
            findViewById<ImageView>(R.id.ivImageContactInformation).setBackgroundColor(colorResId)

            val callButton: Button = findViewById(R.id.btnCall)
            callButton.text = "Llamar a ${contact.name}"

            callButton.setOnClickListener {
                val intent = Intent(this, CallContactActivity::class.java)
                intent.putExtra("contactName", contact.name)
                startActivity(intent)
            }

        }else {
            finish()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}