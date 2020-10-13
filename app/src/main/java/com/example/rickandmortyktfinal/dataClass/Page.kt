package com.example.rickandmortyktfinal.dataClass

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Page(
    @SerializedName("info")
    @Expose
    val info: Info,

    @SerializedName("results")
    @Expose
    val characters: List<Character>
)