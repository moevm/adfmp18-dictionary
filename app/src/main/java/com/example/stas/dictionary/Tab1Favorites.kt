package com.example.stas.dictionary

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
import android.widget.ListView
import android.widget.Toast
import kotlinx.android.synthetic.main.tab1favorites.*


class Tab1Favorites: Fragment(){
    //TODO вытягивать данные из БД
    private var favorites = arrayOf("Продукты", "Кухня", "Поездка")
    private var adapter: ArrayAdapter<String> ?= null
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