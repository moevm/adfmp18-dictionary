package com.example.stas.dictionary

/**
 * Created by stas on 04.03.18.
 */
import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class Tab3All: Fragment(){
    private var Dates = arrayOf("14.12.96", "09.10.98", "04.01.16")
    private var words0 = arrayOf("Cat - кошка", "House - дом", "Kitchen - кухня")
    private var words1 = arrayOf("Dog - собака", "Car - машина", "Picture - картина")
    private var words2 = arrayOf("Orange - апельсин", "Bed - кровать", "Door - дверь")
    private var wordsPerDay0 = WordsPerDay(Dates[0], words0)
    private var wordsPerDay1 = WordsPerDay(Dates[1], words1)
    private var wordsPerDay2 = WordsPerDay(Dates[2], words2)
    private var wordsPerDay3 = WordsPerDay(Dates[2], words2)
    private var wordsPerDay4 = WordsPerDay(Dates[2], words2)
    private var arrayOfAllWords = arrayOf(wordsPerDay0, wordsPerDay1, wordsPerDay2, wordsPerDay3, wordsPerDay4)
    private var adapter: ArrayAdapter<String> ?= null
    private var linearLayout: LinearLayout ?= null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView: View? = inflater?.inflate(R.layout.tab3all, container, false)

        linearLayout = rootView?.findViewById(R.id.tab3mainLinLayout)


        for(wordsPerDay in arrayOfAllWords){
            var item = inflater?.inflate(R.layout.tab3all_item_of_list, linearLayout, false)
            var tvDate = item?.findViewById<TextView>(R.id.tvDate)
            tvDate?.text = wordsPerDay.date

            var tab3_linLayout_words_per_day = item?.findViewById<LinearLayout>(R.id.tab3_linLayout_words_per_day)
            for(word in wordsPerDay.words){
                var item_tab3_linLayout_words_per_day = inflater?.inflate(R.layout.words_per_day, tab3_linLayout_words_per_day, false)
                var tvWord = item_tab3_linLayout_words_per_day?.findViewById<TextView>(R.id.tvWord)
                tvWord?.text = word
                tab3_linLayout_words_per_day?.addView(item_tab3_linLayout_words_per_day)
            }
            linearLayout?.addView(item)
        }

        return rootView
    }
}