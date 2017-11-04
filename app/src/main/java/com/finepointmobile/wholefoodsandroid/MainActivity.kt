package com.finepointmobile.wholefoodsandroid

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log.d
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import java.net.URL

class MainActivity : AppCompatActivity() {

    val foods: ArrayList<Food> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = FoodAdapter(generateFoods())

        doAsync {
            val content = URL("https://jsonplaceholder.typicode.com/albums").readText()

            d("daniel", content)

            val moshi: Moshi = Moshi.Builder().build()
            val jsonAdapter: JsonAdapter<Album> = moshi.adapter(Album::class.java)
            val album: Album? = jsonAdapter.fromJson(content)
            d("daniel", album.toString())
        }
    }

    fun generateFoods(): ArrayList<Food> {
        for (i in 1..200) {
            foods.add(Food("Orange Banana Smoothie #$i", 2.4))
        }
        return foods
    }
}
