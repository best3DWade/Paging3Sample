package com.example.paging3sample.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.paging3sample.R

class FooterAdapter(val retry: () -> Unit) : LoadStateAdapter<FooterAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val progressBar : ProgressBar = itemView.findViewById(R.id.progress_footer_bar)
        val button : Button = itemView.findViewById(R.id.footer_button)
    }

    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        holder.progressBar.isVisible = loadState is LoadState.Loading
        holder.button.isVisible = loadState is LoadState.Error
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.footer_item, parent, false)
        val holder = ViewHolder(view)
        holder.button.setOnClickListener {
            retry
        }
        return holder
    }
}
