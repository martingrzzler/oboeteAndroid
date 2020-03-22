package com.martingrzzler.oboeteandroid.main.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DataResponse<T>(

    @SerializedName("data")
    @Expose
    val data: List<Word>
)