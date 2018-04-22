package com.example.stas.dictionary.Activities

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.TextView
import com.example.stas.dictionary.Data.WordList
import com.example.stas.dictionary.Data.WordPair
import com.example.stas.dictionary.Data.WordPairList
import com.example.stas.dictionary.Data.WordsSet
import com.example.stas.dictionary.R
import com.example.stas.dictionary.database
import kotlinx.android.synthetic.main.activity_words_set.*
import kotlinx.android.synthetic.main.tab1favorites.*
import org.jetbrains.anko.db.*

class WordsSetActivity : AppCompatActivity() {

    private var listView: ListView? = null
    private var ATTRIBUTE_WORD_TEXT = "text"
    private var simpleAdapter: MySimpleAdapter? = null
    private var from = arrayOf(ATTRIBUTE_WORD_TEXT)
    private var to = IntArray(1, { R.id.tvWordSetItem })
    private var wordsSet: WordsSet = WordsSet("defalt", arrayOf("default word - default translate"))
    private var data: ArrayList<Map<String, Any>> = ArrayList<Map<String, Any>>(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_words_set)

        wordsSet = intent.getParcelableExtra<WordsSet>("test")
        tvWordsSetName.text = wordsSet.name

        data = ArrayList<Map<String, Any>>(wordsSet.words.size)
        var m: Map<String, Any>
        for (word in wordsSet.words) {
            m = HashMap<String, Any>()
            m.put(ATTRIBUTE_WORD_TEXT, word)
            data.add(m)
        }

        simpleAdapter = MySimpleAdapter(this, data, R.layout.words_set_item, from, to)

        listView = lvWordsSet
        listView?.adapter = simpleAdapter

    }

    inner class MySimpleAdapter : SimpleAdapter {
        constructor(context: Context?, data: List<out Map<String, *>>?,
                    resource: Int, from: Array<out String>?, to: IntArray?) : super(context, data, resource, from, to)

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var view = super.getView(position, convertView, parent)
            var imageView = view.findViewById<ImageView>(R.id.tvWordSetItemDelete)
            var textView = view.findViewById<TextView>(R.id.tvWordSetItem)
            val values = (textView as TextView).text.toString()
            imageView.setTag(position)
            imageView.setOnClickListener {
//                val word = ""
                val word = values.split(" - ")[0]
                val wordPair: WordPair = database.use {
                    select(WordPair.TABLE_NAME).whereArgs("(${WordPair.COLUMN_WORD} = {name})",
                            "name" to word).exec {
                        parseSingle(classParser())
                    }
                }
                val wordList: WordList = database.use {
                    select(WordList.TABLE_NAME).whereArgs("(${WordList.COLUMN_NAME} = {name})",
                            "name" to wordsSet.name).exec {
                        parseSingle(classParser())
                    }
                }
                val whereArgs = arrayOf(wordList.id.toString(), wordPair.id.toString())
                database.use {
                    delete(WordPairList.TABLE_NAME, "${WordPairList.COLUMN_LIST_ID} = ?" +
                            " AND ${WordPairList.COLUMN_WORD_ID} = ? ", whereArgs)
                }
                val wordPairList: List<WordPairList> = database.use {
                    select(WordPairList.TABLE_NAME).exec {
                        parseList(classParser())
                    }
                }
                data.removeAt(position)
                notifyDataSetChanged()
            }
            return view
        }


    }
}
