package com.example.stas.dictionary.Activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.example.stas.dictionary.Data.WordList
import com.example.stas.dictionary.Data.WordPair
import com.example.stas.dictionary.Data.WordPairList
import com.example.stas.dictionary.R
import com.example.stas.dictionary.database
import kotlinx.android.synthetic.main.activity_new_set.*
import org.jetbrains.anko.db.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Created by stas on 11.03.18.
 */
class NewWordsSetActivity : AppCompatActivity() {

    private var listView: ListView? = null
    private var btnCreate: Button? = null


    fun getAllWords(): List<WordPair> {
        return database.use {
            select(WordPair.TABLE_NAME).exec {
                parseList(classParser())
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_set)

        var nameForNewSet = intent.getStringExtra("newWordsSetName")
        tvNewWordsSetName.text = nameForNewSet
        var allWords = getAllWords()

        val formatter = DateTimeFormatter.ISO_DATE
        val current = LocalDateTime.now()
        val formatted = current.format(formatter)

        database.use {
            insert(WordList.TABLE_NAME, WordList.COLUMN_NAME to nameForNewSet.toString(),
                    WordList.COLUMN_COUNT_WORDS to allWords.size,
                    WordList.COLUMN_DATE to formatted.toString(),
                    WordList.COLUMN_IS_FAVOURITE to 1)
        }

        var newSet: WordList = database.use {
            rawQuery("SELECT ${WordList.TABLE_NAME}.* FROM ${WordList.TABLE_NAME} where ${WordList.COLUMN_NAME} = '$nameForNewSet'", null)
                    .parseSingle(classParser())
        }
        var adapter = ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, allWords.map { "${it.word} - ${it.translate}" })

        listView = lvNewWordsSet
        listView?.choiceMode = ListView.CHOICE_MODE_MULTIPLE
        listView?.adapter = adapter

        btnCreate = btnNewSetCreate
        btnCreate?.setOnClickListener({
            var checkedArray = listView?.checkedItemPositions
            var listOfCheckedWords = ArrayList<WordPair>()
            for (i in 0 until checkedArray!!.size()) {
                var key = checkedArray.keyAt(i)
                if (checkedArray.get(key)) {
                    listOfCheckedWords.add(allWords[key])
                    database.use {
                        insert(WordPairList.TABLE_NAME, WordPairList.COLUMN_WORD_ID to allWords[key].id,
                                WordPairList.COLUMN_LIST_ID to newSet.id)
                    }
                }
            }

            this.finish()
        })
    }


}