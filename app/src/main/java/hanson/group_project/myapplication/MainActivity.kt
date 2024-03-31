package hanson.group_project.myapplication

import adapter.ItemAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import hanson.group_project.myapplication.databinding.ActivityMainBinding
import model.Items

class MainActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var itemAdapter: ItemAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var localItemArrayList: ArrayList<Items>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fab.setOnClickListener {
            var intent = Intent(this, UploadActivity::class.java)
            startActivity(intent)
        }

        binding.recycleView.layoutManager = LinearLayoutManager(this)
        binding.recycleView.setHasFixedSize(true)
        localItemArrayList = arrayListOf()

        itemAdapter = ItemAdapter(localItemArrayList)
        binding.recycleView.adapter = itemAdapter

        db = FirebaseFirestore.getInstance()

        getItems()
    }

    private fun getItems() {
        db.collection("ITEMS").get().addOnSuccessListener { documents ->
            for (document in documents) {
                var singleItem = document.toObject(Items::class.java)
                singleItem.documentID = document.id
                localItemArrayList.add(singleItem)
                Log.e("DocumentID",document.id)
            }
            itemAdapter.notifyDataSetChanged()
        }.addOnFailureListener { exception ->
            Log.w("Firebase", "Error getting documents: ", exception)
        }
        itemAdapter.notifyDataSetChanged()
    }
}