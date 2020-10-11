package com.app.roomexample.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.app.roomexample.R
import com.app.roomexample.models.User
import kotlinx.android.synthetic.main.row_custom_adapter.view.*

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    private var userList = emptyList<User>()


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_custom_adapter, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]

        holder.itemView.index_txt.text = currentItem.id.toString()
        holder.itemView.firstName.text = currentItem.firstName
        holder.itemView.lastName.text = currentItem.lastName
        holder.itemView.age.text = currentItem.age.toString()
        holder.itemView.rowLayout.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }

    }

    fun setData(user: List<User>) {
        this.userList = user
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = userList.size
}