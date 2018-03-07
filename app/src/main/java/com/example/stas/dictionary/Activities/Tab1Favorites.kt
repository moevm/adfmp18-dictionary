package com.example.stas.dictionary.Activities

/**
 * Created by stas on 04.03.18.
 */
import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.ListView
import android.widget.Toast
import com.example.stas.dictionary.R
import com.example.stas.dictionary.Data.WordsSet


class Tab1Favorites: Fragment(){
    //TODO вытягивать данные из БД
    private var favorites = arrayOf("Продукты", "Кухня", "Поездка")
    private var words0 = arrayOf("Cat - кошка", "House - дом", "Kitchen - кухня")
    private var words1 = arrayOf("Dog - собака", "Car - машина", "Picture - картина")
    private var words2 = arrayOf("Orange - апельсин", "Bed - кровать", "Door - дверь")
    private var wordsSet0 = WordsSet(favorites[0], words0)
    private var wordsSet1 = WordsSet(favorites[1], words1)
    private var wordsSet2 = WordsSet(favorites[2], words2)
    private var adapter: ListAdapter ?= null
    private var listView: ListView ?= null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView: View? = inflater?.inflate(R.layout.tab1favorites, container, false)


        adapter = ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, favorites)
        listView = rootView?.findViewById<ListView>(R.id.favoritesListView)
        listView?.adapter = adapter
        registerForContextMenu(listView)


        listView?.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(context, "Position Clicked:"+" "+position,Toast.LENGTH_SHORT).show()
        }

        return rootView
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu!!.setHeaderTitle("Select the action")
        menu!!.add(0, v!!.id, 0, "Delete")
        menu!!.add(0, v!!.id, 0, "Like")
    }
}