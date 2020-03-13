package com.martingrzzler.oboeteandroid.main.util

class SearchWordAdapter {
    companion object{
        fun formatTranslationList(list: List<String>?): String {
            val listString = list.toString().replace("[","").replace("]","")
            return if(listString.length > 50) {
                listString.substring(0, 49) + "..."
            } else {
                listString
            }


        }
    }
}