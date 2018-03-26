package com.example.stas.dictionary.Data

/**
 * Created by stas on 06.03.18.
 */
class WordsPerDay{
    var date: String
    var words: ArrayList<String>

    constructor(date: String, words: ArrayList<String>){
        this.date = date
        this.words = words
    }
}