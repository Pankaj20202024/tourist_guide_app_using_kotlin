package com.example.touristguideapp.Activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.touristguideapp.Domains.PopularDomain

import com.example.touristguideapp.Adapters.CategoryAdapter
import com.example.touristguideapp.Adapters.PuploarAdapter
import com.example.touristguideapp.Domains.CategoryDomain
import com.example.touristguideapp.databinding.ActivityHomePageBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HomePageActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var binding: ActivityHomePageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        binding.Logout.setOnClickListener{
            firebaseAuth.signOut();
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }


        initCategory()
        initPopularPlaces()
        setVariable()
    }

    private fun setVariable() {
        binding.searchBtn.setOnClickListener {
            val text = binding.searchText.text.toString()


            if (text.isNotEmpty()) {
                val intent = Intent(this, ListCategoryPlaceActivity::class.java)
                intent.putExtra("text", text)
                intent.putExtra("isSearch", true)
                startActivity(intent)
            }
        }
    }



    private fun initCategory() {
        val myRef = FirebaseDatabase.getInstance().getReference("Category")
        binding.progrssbarCagtegory.visibility = View.VISIBLE
        val list = ArrayList<CategoryDomain>()

        myRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (issue in snapshot.children) {
                        list.add(issue.getValue(CategoryDomain::class.java)!!)
                    }
                }
                if (list.size > 0) {
                    binding.viewCat.layoutManager = LinearLayoutManager(this@HomePageActivity, LinearLayoutManager.HORIZONTAL, false)
                    val adapterCategory = CategoryAdapter(list)
                    binding.viewCat.adapter = adapterCategory
                }
                binding.progrssbarCagtegory.visibility = View.GONE
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle onCancelled event if needed
            }
        })
    }

    private fun initPopularPlaces() {
        val myRef = FirebaseDatabase.getInstance().getReference("Places")
        binding.progrssbarPopular.visibility = View.VISIBLE
        val list = ArrayList<PopularDomain>()
        val query = myRef.orderByChild("BestPlace").equalTo(true)
        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (issue in snapshot.children) {
                        list.add(issue.getValue(PopularDomain::class.java)!!)
                    }
                }
                if (list.size > 0) {
                    binding.viewPop.layoutManager = LinearLayoutManager(this@HomePageActivity, LinearLayoutManager.HORIZONTAL, false)
                    val adapterBestFood = PuploarAdapter(list)
                    binding.viewPop.adapter = adapterBestFood
                }
                binding.progrssbarPopular.visibility = View.GONE
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

}
