package com.example.touristguideapp.Adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

import com.example.touristguideapp.DetailActivity
import com.example.touristguideapp.Domains.PopularDomain
import com.example.touristguideapp.R

class PuploarAdapter(private val items: ArrayList<PopularDomain>) : RecyclerView.Adapter<PuploarAdapter.ViewHolder>() {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_popular, parent, false)
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = items[position]
        Log.d("PuploarAdapter", "Title: ${currentItem.Title}, Location: ${currentItem.Location}, Star: ${currentItem.Star}, ImagePath: ${currentItem.ImagePath}")
        holder.titleTxt.text = currentItem.Title
        holder.locationTxt.text = currentItem.Location
        holder.scoreTxt.text = currentItem.Star.toString()



        Glide.with(context)
            .load(items[position].ImagePath)
            .transform(CenterCrop(), RoundedCorners(30))
            .into(holder.pic)



        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("object", items[position])
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
