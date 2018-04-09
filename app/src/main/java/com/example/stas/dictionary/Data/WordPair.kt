package com.example.stas.dictionary.Data

import java.util.*

/**
 * Created by Julia on 09.04.2018.
 */
data class WordPair(val id: Int, val word: String, val translate: String, val date: String) {
    companion object {
        val TABLE_NAME = "WordPair"
        val COLUMN_ID = "id"
        val COLUMN_WORD = "word"
        val COLUMN_TRANSLATE = "translate"
        val COLUMN_DATE = "date"
    }
}