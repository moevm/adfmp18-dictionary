package com.example.stas.dictionary.Data

/**
 * Created by Julia on 19.04.2018.
 */
data class WordPairList(val id: Int, val wordId: Int, val listId: Int) {
    companion object {
        val TABLE_NAME = "WordPairList"
        val COLUMN_ID = "id"
        val COLUMN_WORD_ID = "word_id"
        val COLUMN_LIST_ID = "list_id"
    }
}