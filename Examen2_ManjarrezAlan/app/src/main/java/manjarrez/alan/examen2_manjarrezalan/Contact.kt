package manjarrez.alan.examen2_manjarrezalan

import java.io.Serializable

data class Contact(
    val color: String,
    val name: String,
    val work: String,
    val number: String,
    val email: String
) :
    Serializable
