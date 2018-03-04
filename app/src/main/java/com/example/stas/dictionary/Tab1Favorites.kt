package com.example.stas.dictionary

/**
 * Created by stas on 04.03.18.
 */
import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import kotlinx.android.synthetic.main.tab1favorites.*


class Tab1Favorites: Fragment(){
    private var favorites = arrayOf("Продукты", "Кухня", "Поездка")
    private var adapter: ArrayAdapter<String> ?= null
    private var listView: ListView ?= null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView: View? = inflater?.inflate(R.layout.tab1favorites, container, false)

        //TODO вытягивать данные из БД
        adapter = ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, favorites)
        listView = rootView?.findViewById<ListView>(R.id.favoritesListView)
        listView?.adapter = adapter

        return rootView
    }
}