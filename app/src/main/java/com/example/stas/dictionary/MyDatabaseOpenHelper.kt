package com.example.stas.dictionary

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.stas.dictionary.Data.WordPair
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
                WordPair.COLUMN_ID to INTEGER + PRIMARY_KEY + UNIQUE,
                WordPair.COLUMN_WORD to TEXT,
                WordPair.COLUMN_TRANSLATE to TEXT,
                WordPair.COLUMN_DATE to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable(WordPair.TABLE_NAME, true)

    }

}

// Access property for Context
val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)
