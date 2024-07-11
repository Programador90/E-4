package com.palomino.alvaro.myapplication.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.palomino.alvaro.myapplication.databinding.ItemPostBinding
import com.palomino.alvaro.myapplication.model.Post

class PostAdapter(private val postList: List<Post>, private val clickListener: (Post) -> Unit, private val longClickListener: (Post) -> Unit) :
    RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    inner class PostViewHolder(val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = postList[position]
        holder.binding.apply {
            title.text = post.title
            body.text = post.body
            root.setOnClickListener { clickListener(post) }
            root.setOnLongClickListener { longClickListener(post); true }
        }
    }

    override fun getItemCount(): Int = postList.size
}
