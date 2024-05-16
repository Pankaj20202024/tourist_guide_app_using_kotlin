package com.example.touristguideapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.touristguideapp.Domains.PopularDomain

class DetailActivity : AppCompatActivity() {

    private lateinit var titleTxt: TextView
    private lateinit var locationTxt: TextView
    private lateinit var bedTxt: TextView
    private lateinit var guideTxt: TextView
    private lateinit var wifiTxt: TextView
    private lateinit var descriptionTxt: TextView
    private lateinit var scoreTxt: TextView

    private lateinit var item: PopularDomain

    private lateinit var picImg: ImageView
    private lateinit var backBtn: ImageView
    private lateinit var priceTxt: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        initView()
        setVariable()
    }

    private fun setVariable() {
        item = intent.getSerializableExtra("object") as PopularDomain
        titleTxt.text = item.Title
        scoreTxt.text = item.Star.toString()
        locationTxt.text = item.Location
        bedTxt.text = "${item.bed} Bed"
        priceTxt.text = "$ "+item.Price.toString()
        descriptionTxt.text = item.Description
        guideTxt.text = if (item.guide) "Guide" else "No-Guide"
        wifiTxt.text = if (item.wifi) "Wifi" else "No-Wifi"

        Glide.with(this)
            .load(item.ImagePath)
            .into(picImg)
        backBtn.setOnClickListener { finish() }
    }

    private fun initView() {
        titleTxt = findViewById(R.id.titleTxt)
        locationTxt = findViewById(R.id.locationTxt)
        bedTxt = findViewById(R.id.bedTxt)
        guideTxt = findViewById(R.id.guideTxt)
        wifiTxt = findViewById(R.id.wifiTxt)
        descriptionTxt = findViewById(R.id.descriptionTxt)
        scoreTxt = findViewById(R.id.scoreTxt)
        picImg = findViewById(R.id.picImg)
        backBtn = findViewById(R.id.backBtn)
        priceTxt = findViewById(R.id.priceTxt)
    }
}
