package com.example.stas.dictionary.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.stas.dictionary.Data.WordsSet
import com.example.stas.dictionary.R
import kotlinx.android.synthetic.main.activity_words_set.*

class WordsSetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_words_set)

        var wordsSet = intent.getParcelableExtra<WordsSet>("test")
        tvWordsSetName.text = wordsSet.name

    }
}
