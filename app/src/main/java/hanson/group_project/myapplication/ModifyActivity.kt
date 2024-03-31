package hanson.group_project.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.transition.Visibility
import com.google.firebase.firestore.FirebaseFirestore
import hanson.group_project.myapplication.databinding.ActivityModifyBinding
import hanson.group_project.myapplication.databinding.ActivityUploadBinding

class ModifyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityModifyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModifyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val documentedID = intent.getStringExtra("documentID")
        val itemName = intent.getStringExtra("name")
        val itemDescription = intent.getStringExtra("description")
        val db = FirebaseFirestore.getInstance()

        binding.updateItemName.isEnabled = false
        binding.updateItemName.setText(itemName)
        binding.updateItemDescription.isEnabled = false
        binding.updateItemDescription.setText(itemDescription)

        binding.deleteButton.setOnClickListener {
            db.collection("ITEMS").document(documentedID.toString()).delete()
                .addOnSuccessListener {
                    Toast.makeText(this, "Deleted Successfully",Toast.LENGTH_SHORT).show()
                    var intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                }.addOnFailureListener { e ->
                    // Handle any errors
                    Log.w("Error deleting document", e.message.toString())
                }
        }

        binding.updateButton.setOnClickListener {
            if(binding.updateItemName.text.toString().trim().isEmpty() || binding.updateItemDescription.text.toString().trim().isEmpty()){
                Toast.makeText(this,"Please enter the field",Toast.LENGTH_SHORT).show()
            }
            val updates = hashMapOf<String, Any>(
                "name" to binding.updateItemName.text.toString(),
                "description" to binding.updateItemDescription.text.toString()
            )
            db.collection("ITEMS").document(documentedID.toString()).update(updates).addOnSuccessListener {
                Toast.makeText(this, "Updated Successfully",Toast.LENGTH_SHORT).show()
                var intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }.addOnFailureListener { e ->
                // Handle any errors
                Log.w("Error updating document", e)
            }
        }

        binding.editButton.setOnClickListener {
            binding.editButton.visibility = View.GONE
            binding.updateButton.visibility = View.VISIBLE
            binding.updateItemName.isEnabled = true
            binding.updateItemDescription.isEnabled = true
        }
    }
}