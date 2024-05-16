package com.example.touristguideapp.Adapters


import android.content.Context
import android.content.Intent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

import com.example.touristguideapp.DetailActivity

import com.example.touristguideapp.Domains.PopularDomain
import com.example.touristguideapp.R


class ListPlacesAdapter(private val items: ArrayList<PopularDomain>) : RecyclerView.Adapter<ListPlacesAdapter.ViewHolder>() {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val inflate: View = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_popular, parent, false)
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.titleTxt.text = item.Title
        holder.locationTxt.text = item.Location
        holder.scoreTxt.text = item.Star.toString()



        Glide.with(context)
            .load(item.ImagePath)
            .transform(RoundedCorners(30))
            .into(holder.pic)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("object", item)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTxt: TextView = itemView.findViewById(R.id.titleTxt)
        val locationTxt: TextView = itemView.findViewById(R.id.locationTxt)
        val scoreTxt: TextView = itemView.findViewById(R.id.scoreTxt)
        val pic: ImageView = itemView.findViewById(R.id.picImg)
    }
}
