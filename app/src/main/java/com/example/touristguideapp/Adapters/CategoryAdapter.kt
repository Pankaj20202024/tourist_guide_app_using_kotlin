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
import com.example.touristguideapp.Activities.ListCategoryPlaceActivity
import com.example.touristguideapp.Domains.CategoryDomain
import com.example.touristguideapp.R

class CategoryAdapter(private val items: ArrayList<CategoryDomain>) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_category, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.titleTxt.text = item.Name
        val drawableResourceId = holder.itemView.resources.getIdentifier(item.ImagePath, "drawable", holder.itemView.context.packageName)
        Glide.with(holder.itemView.context)
            .load(drawableResourceId)
            .into(holder.picImg)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, ListCategoryPlaceActivity::class.java)
            intent.putExtra("CategoryId", items[position].Id)
            intent.putExtra("CategoryName", items[position].Name)

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTxt: TextView = itemView.findViewById(R.id.titleTxt)
        val picImg: ImageView = itemView.findViewById(R.id.catImg)
    }
}
