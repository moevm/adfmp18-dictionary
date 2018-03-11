package com.example.stas.dictionary.Activities

/**
 * Created by stas on 04.03.18.
 */
import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.stas.dictionary.R

class Tab2Training : Fragment(){
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView: View? = inflater?.inflate(R.layout.tab2recent, container, false)
        return rootView
    }
}