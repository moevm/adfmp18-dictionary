package com.example.stas.dictionary.Activities

/**
 * Created by stas on 04.03.18.
 */
import android.content.Intent
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.view.*
import android.widget.*
import com.example.stas.dictionary.Data.*
import com.example.stas.dictionary.MyDatabaseOpenHelper
import com.example.stas.dictionary.R
import com.example.stas.dictionary.database
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.parseList
import org.jetbrains.anko.db.select
import com.example.stas.dictionary.R.id.textView
import android.widget.TextView
import kotlinx.android.synthetic.main.tab1favorites.*


class Tab1Favorites : Fragment() {
    //TODO вытягивать данные из БД
//    private var favorites = arrayOf("Продукты", "Кухня", "Поездка")
//    private var words0 = arrayOf("Cat - кошка", "House - дом", "Kitchen - кухня")
//    private var words1 = arrayOf("Dog - собака", "Car - машина", "Picture - картина")
//    private var words2 = arrayOf("Orange - апельсин", "Bed - кровать", "Door - дверь")
//    private var wordsSet0 = WordsSet(favorites[0], words0)
//    private var wordsSet1 = WordsSet(favorites[1], words1)
//    private var wordsSet2 = WordsSet(favorites[2], words2)
    private var adapter: ListAdapter? = null
    private var listView: ListView? = null
    private var fab: FloatingActionButton? = null

    fun updateContent(): List<WordList> {
        val database = context.database
        return database.use {
            select(WordList.TABLE_NAME).exec {
                parseList(classParser())
            }
        }
    }

    fun getWordsFromDb(name: String): List<WordPair> {
        val database = context.database
        return database.use {
            rawQuery("SELECT ${WordPair.TABLE_NAME}.* from ${WordPair.TABLE_NAME}" +
                    ", ${WordList.TABLE_NAME}, ${WordPairList.TABLE_NAME} where  " +
                    "${WordList.TABLE_NAME}.${WordList.COLUMN_NAME} = '$name' and " +
                    "${WordList.TABLE_NAME}.${WordList.COLUMN_ID} = " +
                    "${WordPairList.TABLE_NAME}.${WordPairList.COLUMN_LIST_ID} and " +
                    "${WordPair.TABLE_NAME}.${WordPair.COLUMN_ID} = " +
                    "${WordPairList.TABLE_NAME}.${WordPairList.COLUMN_WORD_ID}", null)
                    .parseList(classParser())
            }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView: View? = inflater?.inflate(R.layout.tab1favorites, container, false)

        var wordsSet = ArrayList<WordsSet>()
        var favorites = ArrayList<String>()

        updateContent().forEach({
            favorites.add(it.name)
           var words = getWordsFromDb(it.name)
                   .map { wordPair ->  "${wordPair.word} - ${wordPair.translate}"}
            wordsSet.add(WordsSet(it.name, words.toTypedArray()))
        })


        adapter = ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, favorites)
        listView = rootView?.findViewById<ListView>(R.id.favoritesListView)
        listView?.adapter = adapter
        registerForContextMenu(listView)


        listView?.setOnItemClickListener { parent, view, position, id ->
            var intent = Intent(context, WordsSetActivity::class.java)
            val itemValue = favoritesListView.getItemAtPosition(position) as String
            val values = (view as TextView).text.toString()
            wordsSet.forEach({
                if (it.name == values) {
                    intent.putExtra("test", it)
                }
            })
            startActivity(intent)
        }

        fab = rootView?.findViewById(R.id.fab)
        fab?.setOnClickListener({
            val display: Display = activity.windowManager.defaultDisplay
            val size = Point()
            display.getRealSize(size)
            val width = size.x

            val view = layoutInflater.inflate(R.layout.popup_set_layout, null)

            val popupWindow = PopupWindow(view)

//            popupWindow.width = LinearLayout.LayoutParams.MATCH_PARENT
            popupWindow.width = (width * 0.85).toInt()
            popupWindow.height = LinearLayout.LayoutParams.WRAP_CONTENT
            popupWindow.isFocusable = true
            // If you need the PopupWindow to dismiss when when touched outside
            popupWindow.setBackgroundDrawable(ColorDrawable())
            popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0)

            popupWindow.showAsDropDown(view)

            val etPopUpNewSet = view.findViewById<TextView>(R.id.etPopUpNewSet)
            val btnPopUpOk = view.findViewById<Button>(R.id.btnPopUpOk)

            btnPopUpOk?.setOnClickListener({
                var intent = Intent(context, NewWordsSetActivity::class.java)
                intent.putExtra("newWordsSetName", etPopUpNewSet.text.toString())
                startActivity(intent)
                popupWindow.dismiss()
            })
        })
        return rootView
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu!!.setHeaderTitle("Select the action")
        menu!!.add(0, v!!.id, 0, "Delete")
        menu!!.add(0, v!!.id, 0, "Like")
    }
}