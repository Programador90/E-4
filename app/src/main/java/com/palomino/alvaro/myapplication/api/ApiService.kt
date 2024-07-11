package com.palomino.alvaro.myapplication.api

import com.palomino.alvaro.myapplication.model.Post
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    fun getPosts(): Call<List<Post>>
}
