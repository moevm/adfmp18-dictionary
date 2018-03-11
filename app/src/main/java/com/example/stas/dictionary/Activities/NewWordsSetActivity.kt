package com.example.stas.dictionary.Activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.example.stas.dictionary.Data.WordsSet
import com.example.stas.dictionary.R
import kotlinx.android.synthetic.main.activity_new_set.*

/**
 * Created by stas on 11.03.18.
 */
class NewWordsSetActivity : AppCompatActivity() {

    private var listView: ListView ?= null
    private var btnCreate: Button ?= null
    private var newWordsSet : WordsSet = WordsSet("defalt", arrayOf("default word - default translate"))

    private var allWords = arrayOf("Cat - кошка", "House - дом", "Kitchen - кухня",
            "Dog - собака", "Car - машина", "Picture - картина",
            "Orange - апельсин", "Bed - кровать", "Door - дверь")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_set)

        newWordsSet.name = intent.getStringExtra("newWordsSetName")
        tvNewWordsSetName.text = newWordsSet.name

        var adapter = ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, allWords)

        listView = lvNewWordsSet
        listView?.choiceMode = ListView.CHOICE_MODE_MULTIPLE
        listView?.adapter = adapter

        btnCreate = btnNewSetCreate
        btnCreate?.setOnClickListener({
            var checkedArray = listView?.checkedItemPositions
            var listOfCheckedWords = ArrayList<String>()
            for(i in 0 until checkedArray!!.size()){
                var key = checkedArray.keyAt(i)
                if(checkedArray.get(key))
                    listOfCheckedWords.add(allWords[key])
            }
            newWordsSet.words = listOfCheckedWords.toTypedArray()
            
            //TODO Create in bd newWordsSet
        })

    }

}