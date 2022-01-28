package com.example.notepadshared.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notepadshared.R
import com.example.notepadshared.model.User
import java.util.ArrayList

class MyRecycleAdapter(private val list: ArrayList<User>, private val onClick: OnClick) :
    RecyclerView.Adapter<MyRecycleAdapter.ViewHolder>() {


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val editText: TextView = itemView.findViewById(R.id.item_title)
        val editText2: TextView = itemView.findViewById(R.id.item_discreption)

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                onClick.onClickListener(position)
            }
        }

        init {
            itemView.setOnClickListener(this)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_lists, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.editText.text = list[position].title
        holder.editText2.text = list[position].description
    }

    override fun getItemCount(): Int = list.size

    interface OnClick {
        fun onClickListener(position: Int)
    }
}