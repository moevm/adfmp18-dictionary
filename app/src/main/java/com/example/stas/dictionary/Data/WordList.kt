package com.example.stas.dictionary.Data

/**
 * Created by Julia on 19.04.2018.
 */
data class WordList(val id: Int, val name: String, val isFavourite: Boolean, val countWords: Int, val date: String) {
    companion object {
        val TABLE_NAME = "WordList"
        val COLUMN_ID = "id"
        val COLUMN_NAME = "name"
        val COLUMN_IS_FAVOURITE = "isFavourite"
        val COLUMN_COUNT_WORDS = "countWords"
        val COLUMN_DATE = "date"
    }
}