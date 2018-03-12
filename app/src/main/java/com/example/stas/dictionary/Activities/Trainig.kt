package com.example.stas.dictionary.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.stas.dictionary.R
import kotlinx.android.synthetic.main.activity_trainig.*

class Trainig : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trainig)

        var btn1 = btnAnswer1
        var btn2 = btnAnswer2
        var btn3 = btnAnswer3
        var btn4 = btnAnswer4
        var btnStop = btnStop

        btn1?.setOnClickListener({
            Toast.makeText(this, "answer 1", Toast.LENGTH_SHORT).show()
        })

        btn2?.setOnClickListener({
            Toast.makeText(this, "answer 2", Toast.LENGTH_SHORT).show()
        })

        btn3?.setOnClickListener({
            Toast.makeText(this, "answer 3", Toast.LENGTH_SHORT).show()
        })

        btn4?.setOnClickListener({
            Toast.makeText(this, "answer 4", Toast.LENGTH_SHORT).show()
        })

        btnStop?.setOnClickListener({
            finish()
        })

    }
}
