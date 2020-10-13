package com.example.rickandmortyktfinal

import com.example.rickandmortyktfinal.dataClass.Page
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface API {
    @GET("character")
    fun getCharacterPageX(@Query("page") pageId : Int) : Call<Page>
}