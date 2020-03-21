package com.martingrzzler.oboeteandroid.main.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Word(

    var word: String?,

    val reading: String?,

    val translation: List<String>?

) : Parcelable {
    override fun toString(): String {
        return "Word(word=$word, reading=$reading, translation=$translation)"
    }
}