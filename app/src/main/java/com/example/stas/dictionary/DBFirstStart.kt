//package com.example.stas.dictionary
//
//import android.database.sqlite.SQLiteDatabase
//import org.jetbrains.anko.db.*
//
//fun main() {
//    val db: SQLiteDatabase = SQLiteDatabase.create()
//    val database: MyDatabaseOpenHelper = MyDatabaseOpenHelper(this.context)
//    database.use {
//        createTable("WordPairs", true,
//                "id" to INTEGER + PRIMARY_KEY + UNIQUE,
//                "word" to TEXT,
//                "transl" to TEXT)
//    }
//}
