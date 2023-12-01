package com.tkpd.devcamp.recycler_view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class NewsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    /*
    TODO("handle data insert from activity to adapter")
    */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("initialize your recyclerview view-holder")
    }

    override fun getItemCount(): Int {
        TODO(
            "this function will represent your recyclerview data size" +
            "return your total data inside recyclerview"
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO(
            "your data from activity get translated into recyclerview item as view-holder" +
                    "insert your list of data in certain position into view-holder"
        )
    }

}