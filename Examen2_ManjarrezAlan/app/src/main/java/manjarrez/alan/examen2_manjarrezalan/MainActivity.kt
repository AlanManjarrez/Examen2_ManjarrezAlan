package manjarrez.alan.examen2_manjarrezalan

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    var contact = ArrayList<Contact>()
    private lateinit var listView: ListView
    private val NEW_CONTACT_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.listView)

        val newContact: Button = findViewById(R.id.btnNewContact)
        newContact.setOnClickListener{
            val intent = Intent(this@MainActivity, RegisterActivity::class.java)
            startActivityForResult(intent, NEW_CONTACT_REQUEST)
        }

        agregarContacto()
        var contactosMostrados = contact


        var adaptador: AdaptadorContactos = AdaptadorContactos(this, contactosMostrados)
        listView.adapter = adaptador

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == NEW_CONTACT_REQUEST && resultCode == RESULT_OK) {
            val newContact = data?.getSerializableExtra("newContact") as? Contact
            newContact?.let {
                contact.add(it)

                val adaptador = listView.adapter as? AdaptadorContactos
                adaptador?.notifyDataSetChanged()
            }
        }
    }


    fun agregarContacto() {
        contact.add(
            Contact(
                "blue",
                "Lucía Peña",
                "Secretaría de Salud Pública",
                "31851985",
                "lucia@gmail.com"
            )
        )
        contact.add(
            Contact(
                "yellow",
                "Gilberto Borrego",
                "Gamesa",
                "97834542",
                "gilberto@gmail.com"
            )
        )
        contact.add(
            Contact(
                "purple",
                "Beatriz Vizcarra",
                "Gelattina",
                "478237842",
                "beatriz@gmail.com"
            )
        )
        contact.add(
            Contact(
                "green",
                "Karla Monreal",
                "ITSON",
                "324723578",
                "karla@gmail.com"
            )
        )
    }

    private class AdaptadorContactos : BaseAdapter {
        var contactos = ArrayList<Contact>()
        var contexto: Context? = null

        constructor(contexto: Context, contactos: ArrayList<Contact>) {
            this.contactos = contactos
            this.contexto = contexto
        }

        override fun getCount(): Int {
            return contactos.size
        }

        override fun getItem(position: Int): Any {
            return contactos[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val cont = contactos[position]
            val inflador = LayoutInflater.from(contexto)
            val vista = inflador.inflate(R.layout.contact_view, null)

            val colorView = vista.findViewById<ImageView>(R.id.ivImageContact)
            val name = vista.findViewById<TextView>(R.id.tvContactName)
            val work = vista.findViewById<TextView>(R.id.tvContactWork)

            val colorResId = try {
                Color.parseColor(cont.color)
            } catch (e: IllegalArgumentException) {
                Color.BLACK
            }
            colorView.setBackgroundColor(colorResId)

            name.text = cont.name
            work.text = cont.work

            vista.setOnClickListener {
                val intent = Intent(contexto, ContactInfoActivity::class.java)
                intent.putExtra("contact", contactos[position])
                contexto?.startActivity(intent)
            }
            return vista
        }
    }
}
