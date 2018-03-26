package com.example.stas.dictionary.Activities

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListView
import android.widget.SimpleAdapter
import com.example.stas.dictionary.Data.WordsSet
import com.example.stas.dictionary.R
import kotlinx.android.synthetic.main.activity_words_set.*

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
            imageView.setTag(position)
            imageView.setOnClickListener {
                data.removeAt(position)
                notifyDataSetChanged()
            }
            return view
        }


    }
}
