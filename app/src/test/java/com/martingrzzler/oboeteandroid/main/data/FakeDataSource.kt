package com.martingrzzler.oboeteandroid.main.data

import com.martingrzzler.oboeteandroid.main.model.DataResponse
import com.martingrzzler.oboeteandroid.main.model.Word
import com.martingrzzler.oboeteandroid.main.network.ApiService

class FakeDataSource() : ApiService {

    var dataRepsonse: DataResponse<List<Word>> = DataResponse(listOf(
        Word("何","なに", listOf("what", "something")),
        Word("男", "おとこ", listOf("man", "person", "chap"))
    ))
    override suspend fun getWord(query: String): DataResponse<List<Word>> = dataRepsonse

}