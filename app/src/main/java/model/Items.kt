package model
import com.google.firebase.firestore.PropertyName

data class Items(
    @PropertyName("documentID") var documentID: String = "",
    @PropertyName("name") val name: String = "",
    @PropertyName("description") val description: String =""
)