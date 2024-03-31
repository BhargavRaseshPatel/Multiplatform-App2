package hanson.group_project.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import hanson.group_project.myapplication.databinding.ActivityUploadBinding
import model.Items

class UploadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUploadBinding
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener{
            if(binding.uploadItemName.toString().trim().isEmpty() || binding.uploadItemDescription.text.toString().trim().isEmpty()){
                Toast.makeText(this,"Please enter the field",Toast.LENGTH_SHORT).show()
            }
            val item = Items("ABC",binding.uploadItemName.text.toString(),binding.uploadItemDescription.text.toString())

            db.collection("ITEMS").add(item).addOnSuccessListener {
                Toast.makeText(this,"Item has been added successfully.",Toast.LENGTH_SHORT).show()
            }.addOnFailureListener { e ->
                Log.w("FireBase", e.message.toString())
            }
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}