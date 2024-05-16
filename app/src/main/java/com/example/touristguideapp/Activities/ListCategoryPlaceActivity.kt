package com.example.touristguideapp.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.touristguideapp.Adapters.ListPlacesAdapter

import com.example.touristguideapp.Domains.PopularDomain

import com.example.touristguideapp.databinding.ActivityListCategoryPlaceBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener

class ListCategoryPlaceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListCategoryPlaceBinding
    private lateinit var adapterListFood: RecyclerView.Adapter<*>
    private var categoryId: Int = 0
    private lateinit var categoryName: String
    private lateinit var searchText: String
    private var isSearch: Boolean = false
    private lateinit var databaseRef: DatabaseReference
    private val list: ArrayList<PopularDomain> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListCategoryPlaceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getIntentExtra()
        initList()
    }

    private fun getIntentExtra() {
        categoryId = intent.getIntExtra("CategoryId", 0)
        categoryName = intent.getStringExtra("CategoryName") ?: ""
        searchText = intent.getStringExtra("text") ?: ""
        isSearch = intent.getBooleanExtra("isSearch", false)
        binding.titleTxt.text = categoryName
        binding.backBtn.setOnClickListener { finish() }

    }

    private fun initList() {
        databaseRef = FirebaseDatabase.getInstance().getReference("Places")
        binding.progressBar.visibility = View.VISIBLE

        val query: Query = if (isSearch) {
            databaseRef.orderByChild("Title").startAt(searchText).endAt(searchText + "\uf8ff")
        } else {
            databaseRef.orderByChild("CategoryId").equalTo(categoryId.toDouble())
        }

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (issue in snapshot.children) {
                        issue.getValue(PopularDomain::class.java)?.let { list.add(it) }
                    }
                    if (list.isNotEmpty()) {
                        showRecyclerView()
                    }
                } else {
                    hideProgressBar()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                hideProgressBar()
                // Handle errors here
            }
        })
    }

    private fun showRecyclerView() {
        binding.progressBar.visibility = View.GONE
        binding.foodListView.layoutManager = GridLayoutManager(this, 1)
        adapterListFood = ListPlacesAdapter(list)
        binding.foodListView.adapter = adapterListFood
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }
}
