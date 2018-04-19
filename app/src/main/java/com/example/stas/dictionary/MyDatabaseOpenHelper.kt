package com.example.stas.dictionary

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.stas.dictionary.Data.WordList
import com.example.stas.dictionary.Data.WordPair
import com.example.stas.dictionary.Data.WordPairList
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "MyDatabase", null, 1) {
    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Here you create tables
        db.createTable(WordPair.TABLE_NAME, true,
                WordPair.COLUMN_ID to INTEGER + PRIMARY_KEY,
                WordPair.COLUMN_WORD to TEXT,
                WordPair.COLUMN_TRANSLATE to TEXT,
                WordPair.COLUMN_DATE to TEXT)
        db.createTable(WordList.TABLE_NAME, true,
                WordList.COLUMN_ID to INTEGER + PRIMARY_KEY,
                WordList.COLUMN_NAME to TEXT,
                WordList.COLUMN_IS_FAVOURITE to INTEGER,
                WordList.COLUMN_COUNT_WORDS to TEXT,
                WordList.COLUMN_DATE to TEXT)
        db.createTable(WordPairList.TABLE_NAME, true,
                WordPairList.COLUMN_ID to INTEGER + PRIMARY_KEY,
                WordPairList.COLUMN_WORD_ID to INTEGER,
                WordPairList.COLUMN_LIST_ID to INTEGER,
                FOREIGN_KEY(WordPairList.COLUMN_WORD_ID, WordPair.TABLE_NAME, WordPair.COLUMN_ID),
                FOREIGN_KEY(WordPairList.COLUMN_LIST_ID, WordList.TABLE_NAME, WordList.COLUMN_ID))
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable(WordPair.TABLE_NAME, true)
        db.dropTable(WordList.TABLE_NAME, true)
        db.dropTable(WordPairList.TABLE_NAME, true)
    }

}

// Access property for Context
val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)
