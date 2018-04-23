package com.example.stas.dictionary.Activities

/**
 * Created by stas on 04.03.18.
 */
import android.content.ContentValues
import android.content.Intent
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.*
import android.widget.*
import com.example.stas.dictionary.Data.WordList
import com.example.stas.dictionary.Data.WordPair
import com.example.stas.dictionary.MyDatabaseOpenHelper
import com.example.stas.dictionary.R
import com.example.stas.dictionary.database
import org.jetbrains.anko.db.*

class Tab2Training : Fragment() {

    fun getAllWords(database : MyDatabaseOpenHelper): List<WordPair> {
        return database.use {
            select(WordPair.TABLE_NAME).exec {
                parseList(classParser())
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val database = context.database

        var allWordsPair = getAllWords(database)
        var allSets : List<WordList> = database.use {
            select(WordList.TABLE_NAME).exec {
                parseList(classParser())
            }
        }
        val rootView: View? = inflater?.inflate(R.layout.tab2training, container, false)
        val btnTraining = rootView?.findViewById<Button>(R.id.btnTraining)
        val tvNext = rootView?.findViewById<TextView>(R.id.tvNext)
        val tvWord = rootView?.findViewById<TextView>(R.id.tvWord)
        val btnNo = rootView?.findViewById<Button>(R.id.btnNo)
        val btnYes = rootView?.findViewById<Button>(R.id.btnYes)
        var indx = 0

//        val interestingFactName = rootView?.findViewById<TextView>(R.id.textViewInterestFactName)
        val textInter = rootView?.findViewById<TextView>(R.id.textViewInterestFact)
        textInter?.text = getString(R.string.interestFact0)
//        interestingFactName?.text = getString(R.string.interestingFactName)

        var allWords = allWordsPair.map { it.word }
        tvWord?.text = allWords[indx]
        btnTraining?.setOnClickListener({

            val display : Display = activity.windowManager.defaultDisplay
            val size = Point()
            display.getRealSize(size)
            val width = size.x

            val view = layoutInflater.inflate(R.layout.popup_training, null)

            val popupWindow = PopupWindow(view)

            popupWindow.width = (width * 0.85).toInt()
            popupWindow.height = LinearLayout.LayoutParams.WRAP_CONTENT
            popupWindow.isFocusable = true
            // If you need the PopupWindow to dismiss when when touched outside
            popupWindow.setBackgroundDrawable(ColorDrawable())
            popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0)

            var listView = view.findViewById<ListView>(R.id.lvTraining)
            var adapter = ArrayAdapter(context, android.R.layout.simple_list_item_multiple_choice, allSets.map { it.name })

            listView?.choiceMode = ListView.CHOICE_MODE_MULTIPLE
            listView?.adapter = adapter

            var btnOk = view.findViewById<Button>(R.id.btnPopUpOk)

            btnOk.setOnClickListener({
                //TODO переход к тренировке
                var intent = Intent(context, Training::class.java)
                //TODO putExtra
                startActivity(intent)
                popupWindow.dismiss()
            })

            popupWindow.showAsDropDown(view)

        })

        tvNext?.setOnClickListener {
            if (indx == allWords.size)
                indx = 0
            tvWord?.text = allWords[indx++]
        }

        btnNo?.setOnClickListener({
            Toast.makeText(activity, "Sad!", Toast.LENGTH_SHORT).show()
            if (indx == allWords.size)
                indx = 0
            tvWord?.text = allWords[indx++]

        })
        btnYes?.setOnClickListener({
            Toast.makeText(activity, "Great!", Toast.LENGTH_SHORT).show()
            if (indx == allWords.size)
                indx = 0
            tvWord?.text = allWords[indx++]
        })
        return rootView
    }

//    fun showNextWord(index: Int, ourView: TextView): Void {
//        if(index == 2) {
//            index = 0
//        }
//        ourView?.text = allWords.get(++index)
//    }


}