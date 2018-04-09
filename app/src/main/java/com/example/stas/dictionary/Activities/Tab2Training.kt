package com.example.stas.dictionary.Activities

/**
 * Created by stas on 04.03.18.
 */
import android.content.ContentValues
import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.*
import android.widget.*
import com.example.stas.dictionary.Data.WordPair
import com.example.stas.dictionary.MyDatabaseOpenHelper
import com.example.stas.dictionary.R
import com.example.stas.dictionary.database
import org.jetbrains.anko.db.*

class Tab2Training : Fragment() {

    private var allSets = arrayOf("set1", "set2", "set3")
    private var allWords = ArrayList<String>(arrayListOf("Cat", "Dog", "House"))
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView: View? = inflater?.inflate(R.layout.tab2training, container, false)
        val btnTraining = rootView?.findViewById<Button>(R.id.btnTraining)
        val tvNext = rootView?.findViewById<TextView>(R.id.tvNext)
        val tvWord = rootView?.findViewById<TextView>(R.id.tvWord)
        val btnNo = rootView?.findViewById<Button>(R.id.btnNo)
        val btnYes = rootView?.findViewById<Button>(R.id.btnYes)
        var indx = 0

        val database = context.database

//        val interestingFactName = rootView?.findViewById<TextView>(R.id.textViewInterestFactName)
        val textInter = rootView?.findViewById<TextView>(R.id.textViewInterestFact)
        textInter?.text = getString(R.string.interestFact0)
//        interestingFactName?.text = getString(R.string.interestingFactName)

        tvWord?.text = allWords[indx]
        btnTraining?.setOnClickListener({


            database.use {
                insert(WordPair.TABLE_NAME, WordPair.COLUMN_ID to 1,
                        WordPair.COLUMN_WORD to "Apple", WordPair.COLUMN_TRANSLATE to "Яблоко",
                        WordPair.COLUMN_DATE to "2018-04-04")
            }
            database.use {
                insert(WordPair.TABLE_NAME, WordPair.COLUMN_ID to 1,
                        WordPair.COLUMN_WORD to "Cat", WordPair.COLUMN_TRANSLATE to "Кот",
                        WordPair.COLUMN_DATE to "2018-04-04")
            }

//            val display : Display = activity.windowManager.defaultDisplay
//            val size = Point()
//            display.getRealSize(size)
//            val width = size.x
//
//            val view = layoutInflater.inflate(R.layout.popup_training, null)
//
//            val popupWindow = PopupWindow(view)
//
//            popupWindow.width = (width * 0.85).toInt()
//            popupWindow.height = LinearLayout.LayoutParams.WRAP_CONTENT
//            popupWindow.isFocusable = true
//            // If you need the PopupWindow to dismiss when when touched outside
//            popupWindow.setBackgroundDrawable(ColorDrawable())
//            popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0)
//
//            var listView = view.findViewById<ListView>(R.id.lvTraining)
//            var adapter = ArrayAdapter(context, android.R.layout.simple_list_item_multiple_choice, allSets)
//
//            listView?.choiceMode = ListView.CHOICE_MODE_MULTIPLE
//            listView?.adapter = adapter
//
//            var btnOk = view.findViewById<Button>(R.id.btnPopUpOk)
//
//            btnOk.setOnClickListener({
//                //TODO переход к тренировке
//                var intent = Intent(context, Training::class.java)
//                //TODO putExtra
//                startActivity(intent)
//                popupWindow.dismiss()
//            })
//
//            popupWindow.showAsDropDown(view)

        })

        tvNext?.setOnClickListener {
            if (indx == 2)
                indx = 0
            tvWord?.text = allWords[++indx]
        }

        btnNo?.setOnClickListener({

            val text = database.use {
                select(WordPair.TABLE_NAME).exec {
                    parseList(classParser<WordPair>())
                }
            }

            Toast.makeText(activity, text[0].word, Toast.LENGTH_SHORT).show()
//            Toast.makeText(activity, "Great!", Toast.LENGTH_SHORT).show()
            if (indx == 2)
                indx = 0
            tvWord?.text = allWords[++indx]

        })
        btnYes?.setOnClickListener({
            Toast.makeText(activity, "Sad!", Toast.LENGTH_SHORT).show()
            if (indx == 2)
                indx = 0
            tvWord?.text = allWords.get(++indx)
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