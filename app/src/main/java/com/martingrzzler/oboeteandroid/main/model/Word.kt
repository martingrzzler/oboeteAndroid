package com.martingrzzler.oboeteandroid.main.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Word(

    @Expose
    @SerializedName("word")
    val word: String,

    @Expose
    @SerializedName("reading")
    val reading: String,

    @Expose
    @SerializedName("translation")
    val translation: List<String>

){
    override fun toString(): String {
        return "Word(word=$word, reading=$reading, translation=$translation)"
    }
}