package com.martingrzzler.oboeteandroid.main.model


data class Word(

    val word: String,

    val reading: String,

    val translation: List<String>

){
    override fun toString(): String {
        return "Word(word=$word, reading=$reading, translation=$translation)"
    }
}