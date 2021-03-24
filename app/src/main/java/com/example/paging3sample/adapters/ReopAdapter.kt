package com.example.paging3sample.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.paging3sample.R
import com.example.paging3sample.entities.Reop

class ReopAdapter : PagingDataAdapter<Reop, ReopAdapter.ViewHolder>(COMPARATOR) {

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Reop>() {
            override fun areItemsTheSame(oldItem: Reop, newItem: Reop): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Reop, newItem: Reop): Boolean {
                return oldItem == newItem
            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.name_text)
        val description : TextView = itemView.findViewById(R.id.description_text)
        val starsCount : TextView = itemView.findViewById(R.id.star_count_text)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repo = getItem(position)
        repo?.also{
            holder.name.text = it.name
            holder.description.text = it.description
            holder.starsCount.text = it.starCounts.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.reop_item, parent, false)
        return ViewHolder(view)
    }
}