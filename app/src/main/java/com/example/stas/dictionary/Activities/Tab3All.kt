package com.example.stas.dictionary.Activities

/**
 * Created by stas on 04.03.18.
 */
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.view.*
import android.widget.*
import com.example.stas.dictionary.Data.WordPair
import com.example.stas.dictionary.MyDatabaseOpenHelper
import com.example.stas.dictionary.R
import org.jetbrains.anko.db.classParser
import com.example.stas.dictionary.database
import org.jetbrains.anko.db.parseList
import org.jetbrains.anko.db.select


class Tab3All : Fragment() {
    private var Dates = arrayOf("14.12.96", "09.10.98", "04.01.16")
//    val words0 = ArrayList<String>(arrayListOf("Cat - кошка", "House - дом", "Kitchen - кухня"))
//    private var words1 = ArrayList<String>(arrayListOf("Dog - собака", "Car - машина", "Picture - картина"))
//    private var words2 = ArrayList<String>(arrayListOf("Orange - апельсин", "Bed - кровать", "Door - дверь"))
//    private var wordsPerDay0 = WordsPerDay(Dates[0], words0)
//    private var wordsPerDay1 = WordsPerDay(Dates[1], words1)
//    private var wordsPerDay2 = WordsPerDay(Dates[2], words2)
//    private var wordsPerDay3 = WordsPerDay(Dates[2], words2)
//    private var wordsPerDay4 = WordsPerDay(Dates[2], words2)
    private var linearLayout: LinearLayout? = null
    private var addWordButton: FloatingActionButton? = null
    private var arrayOfAllWords: List<WordPair> = ArrayList()


    fun updateContent(database: MyDatabaseOpenHelper) {
        arrayOfAllWords = database.use {
            select(WordPair.TABLE_NAME).exec {
                parseList(classParser())
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val database = context.database

        var rootView: View? = inflater?.inflate(R.layout.tab3all, container, false)

        linearLayout = rootView?.findViewById(R.id.tab3mainLinLayout)

        updateContent(database)

        for (word in arrayOfAllWords) {
            var item = inflater?.inflate(R.layout.tab3all_item_of_list, linearLayout, false)
            var tvDate = item?.findViewById<TextView>(R.id.tvDate)
            tvDate?.text = word.date

            var tab3_linLayout_words_per_day = item?.findViewById<LinearLayout>(R.id.tab3_linLayout_words_per_day)
//            for (word in wordsPerDay.word) {
                var item_tab3_linLayout_words_per_day = inflater?.inflate(R.layout.words_per_day,
                        tab3_linLayout_words_per_day, false)
                var tvWord = item_tab3_linLayout_words_per_day?.findViewById<TextView>(R.id.tvWord)
                tvWord?.text = "${word.word}   -  ${word.translate}"
                tab3_linLayout_words_per_day?.addView(item_tab3_linLayout_words_per_day)

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
                        updateContent(database)
                        popupWindow.dismiss()
                    })
                })

                var deleteImg = item_tab3_linLayout_words_per_day?.findViewById<ImageView>(R.id.tvDeleteImage)
                deleteImg?.setOnClickListener({
                    //TODO добавить связь с бд
                    Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show()
//                    wordsPerDay.words.remove(word)
                    //TODO Delete from db
                })
//            }
            linearLayout?.addView(item)
        }

        addWordButton = rootView?.findViewById(R.id.fabAddWord)
        addWordButton?.setOnClickListener({
            val intent = Intent(activity, WordsAddActivity::class.java)
            startActivity(intent)
            updateContent(database)
        })

        return rootView
    }
}