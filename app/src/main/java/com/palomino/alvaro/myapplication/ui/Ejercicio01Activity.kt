package com.palomino.alvaro.myapplication.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.palomino.alvaro.myapplication.api.RetrofitClient
import com.palomino.alvaro.myapplication.databinding.ActivityEjercicio01Binding
import com.palomino.alvaro.myapplication.model.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Ejercicio01Activity : AppCompatActivity() {

    private lateinit var binding: ActivityEjercicio01Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEjercicio01Binding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        fetchPosts()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun fetchPosts() {
        RetrofitClient.instance.getPosts().enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    val posts = response.body()
                    posts?.let {
                        binding.recyclerView.adapter = PostAdapter(it, ::onItemClick, ::onItemLongClick)
                    }
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Toast.makeText(this@Ejercicio01Activity, "Error fetching data", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun onItemClick(post: Post) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse("smsto:+51925137361")
            putExtra("sms_body", post.title)
        }
        startActivity(intent)
    }

    private fun onItemLongClick(post: Post) {
        val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:victor.saico@tecsup.edu.pe")
            putExtra(Intent.EXTRA_SUBJECT, "Post Body")
            putExtra(Intent.EXTRA_TEXT, post.body)
        }
        startActivity(emailIntent)
    }
}
