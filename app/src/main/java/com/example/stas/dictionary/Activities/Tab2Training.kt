package com.example.stas.dictionary.Activities

/**
 * Created by stas on 04.03.18.
 */
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.*
import android.widget.*
import com.example.stas.dictionary.Data.WordsSet
import com.example.stas.dictionary.R

class Tab2Training : Fragment(){

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView: View? = inflater?.inflate(R.layout.tab2training, container, false)

        var btnTraining = rootView?.findViewById<Button>(R.id.btnTraining)

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

            popupWindow.showAsDropDown(view)




        })

        return rootView
    }


}