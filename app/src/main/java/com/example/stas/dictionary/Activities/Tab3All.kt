package com.example.stas.dictionary.Activities

/**
 * Created by stas on 04.03.18.
 * Класс, отвечающий за отображение табы All и ее логику
 */
import android.content.ContentValues
import android.content.Intent
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.util.Log
import android.view.*
import android.widget.*
import com.example.stas.dictionary.Data.WordPair
import com.example.stas.dictionary.MyDatabaseOpenHelper
import com.example.stas.dictionary.R
import com.example.stas.dictionary.database
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.parseList
import org.jetbrains.anko.db.select
import java.util.*
import android.widget.LinearLayout
import kotlin.collections.ArrayList


class Tab3All : Fragment() {
    private var linearLayout: LinearLayout? = null
    private var addWordButton: FloatingActionButton? = null
    private var arrayOfAllWords: List<WordPair> = ArrayList()
    private lateinit var database: MyDatabaseOpenHelper


    fun updateContent(database: MyDatabaseOpenHelper) {
        arrayOfAllWords = ArrayList()
        arrayOfAllWords = database.use {
            select(WordPair.TABLE_NAME).exec {
                parseList(classParser())
            }
        }
    }

    fun updateView(inflater: LayoutInflater?) {
        updateContent(database)
        var itemList: MutableList<View> = ArrayList()
        for (word in arrayOfAllWords) {
            var item = inflater?.inflate(R.layout.tab3all_item_of_list, linearLayout, false)
            var tvDate = item?.findViewById<TextView>(R.id.tvDate)
            tvDate?.text = word.date

            var tab3_linLayout_words_per_day = item?.findViewById<LinearLayout>(R.id.tab3_linLayout_words_per_day)
            var item_tab3_linLayout_words_per_day = inflater?.inflate(R.layout.words_per_day,
                    tab3_linLayout_words_per_day, false)
            var tvWord = item_tab3_linLayout_words_per_day?.findViewById<TextView>(R.id.tvWord)
            tvWord?.text = "${word.word}   -  ${word.translate}"
            tab3_linLayout_words_per_day?.addView(item_tab3_linLayout_words_per_day)
            bind(inflater, word, tab3_linLayout_words_per_day)
            itemList.add(item!!)
//            linearLayout?.addView(item)
        }
        updateLayout(itemList)
    }

    fun bind(inflater: LayoutInflater?, word: WordPair, item_tab3_linLayout_words_per_day: LinearLayout?) {

        var editImg = item_tab3_linLayout_words_per_day?.findViewById<ImageView>(R.id.tvEditImage)
        editImg?.setOnClickListener({
            val display: Display = activity.windowManager.defaultDisplay
            val size = Point()
            display.getRealSize(size)
            val width = size.x

            val view = layoutInflater.inflate(R.layout.popup_edit_word, null)

            val popupWindow = PopupWindow(view)

            popupWindow.width = (width * 0.85).toInt()
            popupWindow.height = LinearLayout.LayoutParams.WRAP_CONTENT
            popupWindow.isFocusable = true
            // If you need the PopupWindow to dismiss when when touched outside
            popupWindow.setBackgroundDrawable(ColorDrawable())
            popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0)
            popupWindow.showAsDropDown(view)

            val etPopUpEditWord = view.findViewById<TextView>(R.id.etPopUpEditWord)
            val btnPopUpOk = view.findViewById<Button>(R.id.btnPopUpOk)

            etPopUpEditWord.text = word.word

            btnPopUpOk?.setOnClickListener({
                val values = ContentValues()
                values.put(WordPair.COLUMN_WORD, etPopUpEditWord.text.toString())
                val whereArgs = arrayOf(word.word)
                database.use {
                    update(WordPair.TABLE_NAME, values, "${WordPair.COLUMN_WORD} = ?", whereArgs)
                }
                updateView(inflater)
//                updateLayout()
                Log.d("Delete", Date().toString() + " Word " + word.word + " edited ")
                popupWindow.dismiss()

            })
        })

        var deleteImg = item_tab3_linLayout_words_per_day?.findViewById<ImageView>(R.id.tvDeleteImage)
        deleteImg?.setOnClickListener({
            val whereArgs = arrayOf(word.word)
            database.use {
                delete(WordPair.TABLE_NAME, "${WordPair.COLUMN_WORD} = ?", whereArgs)
            }
            Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show()
//            updateContent(database)
            updateView(inflater)
            Log.d("Delete", Date().toString() + " Word " + word.word + " deleted ")
        })


    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView: View? = inflater?.inflate(R.layout.tab3all, container, false)
        database = context.database
        linearLayout = rootView?.findViewById(R.id.tab3mainLinLayout)
        updateView(inflater)

        addWordButton = rootView?.findViewById(R.id.fabAddWord)
        addWordButton?.setOnClickListener({
            val intent = Intent(activity, WordsAddActivity::class.java)
            startActivity(intent)
//            updateView(inflater)
//            updateLayout()
        })

        return rootView
    }

    fun updateLayout(list: MutableList<View>) {
        if (linearLayout != null){
            linearLayout!!.setBackgroundColor(resources.getColor(R.color.transparent))
            for (v: View in list){
                linearLayout!!.addView(v)
            }
            linearLayout!!.requestLayout()
        }

    }
}