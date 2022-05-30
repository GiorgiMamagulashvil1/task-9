package com.example.workerexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.workerexample.data.model.User

class UserAdapter(private val data:List<User>):RecyclerView.Adapter<UserAdapter.VH>() {

    class VH(itemView:View):RecyclerView.ViewHolder(itemView){
        fun onBind(item:User){
            itemView.findViewById<AppCompatTextView>(R.id.usernameTextView).apply {
                text = item.first_name
            }
            itemView.findViewById<AppCompatTextView>(R.id.lastNameTextView).apply {
                text = item.last_name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.user_item,parent,false)
        return VH(itemView)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
      holder.onBind(data[position])
    }

    override fun getItemCount(): Int {
      return data.size
    }
}