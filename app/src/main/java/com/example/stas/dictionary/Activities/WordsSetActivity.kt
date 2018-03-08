package com.example.stas.dictionary.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.stas.dictionary.Data.WordsSet
import com.example.stas.dictionary.R
import kotlinx.android.synthetic.main.activity_words_set.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class WordsSetActivity : AppCompatActivity() {

    private var listView: ListView?= null
    private var ATTRIBUTE_WORD_TEXT = "text"
    private var simpleAdapter: SimpleAdapter ?= null
    private var from = arrayOf(ATTRIBUTE_WORD_TEXT)
    private var to = IntArray(1, {R.id.tvWordSetItem})

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_words_set)

        var wordsSet = intent.getParcelableExtra<WordsSet>("test")
        tvWordsSetName.text = wordsSet.name

        var data: ArrayList<Map<String, Any>> = ArrayList<Map<String, Any>>(wordsSet.words.size)
        var m: Map<String, Any>
        for(word in wordsSet.words){
            m = HashMap<String, Any>()
            m.put(ATTRIBUTE_WORD_TEXT, word)
            data.add(m)
        }

        simpleAdapter = SimpleAdapter(this, data, R.layout.words_set_item, from, to)

        listView = lvWordsSet
        listView?.adapter = simpleAdapter


    }
}
