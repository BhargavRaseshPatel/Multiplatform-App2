package adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hanson.group_project.myapplication.ModifyActivity
import hanson.group_project.myapplication.R
import model.Items

class ItemAdapter(private val itemList: List<Items>) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    class ItemViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        val textItemName: TextView = itemView.findViewById(R.id.rvItemName)
        val textItemDescription: TextView = itemView.findViewById(R.id.rvItemDescription)
        val modifyButton: Button = itemView.findViewById(R.id.modifyButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_item,parent,false)
        return ItemViewHolder(itemView)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.textItemName.text = currentItem.name
        holder.textItemDescription.text = currentItem.description
        Log.d("DocumentID",currentItem.documentID)
        holder.modifyButton.setOnClickListener {
            var intent = Intent(holder.itemView.context, ModifyActivity::class.java)
            intent.putExtra("documentID",currentItem.documentID)
            intent.putExtra("name",currentItem.name)
            intent.putExtra("description",currentItem.description)
            holder.itemView.context.startActivity(intent)
        }
    }
}