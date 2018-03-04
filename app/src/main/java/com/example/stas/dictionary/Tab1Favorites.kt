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
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView: View? = inflater?.inflate(R.layout.tab1favorites, container, false)

        //TODO вытягивать данные из БД
        val favorites = arrayOf("Продукты", "Кухня", "Поездка")
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(activity, android.R.layout.simple_list_item_1, favorites)
        val listView: ListView = activity.favoritesListView
        listView.adapter = adapter

        

        return rootView
    }
}