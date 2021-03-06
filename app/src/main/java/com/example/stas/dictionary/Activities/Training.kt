package com.example.stas.dictionary.Activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.stas.dictionary.R
import kotlinx.android.synthetic.main.activity_trainig.*

class Training : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trainig)

        var currQ = 0
        val allQ = 4

        var btn1 = btnAnswer1
        var btn2 = btnAnswer2
        var btn3 = btnAnswer3
        var btn4 = btnAnswer4
        var btnStop = btnStop

        fun refreshProc() {
            textViewProcess.text = currQ.toString() + " / " + allQ.toString()
            progressBar2.incrementProgressBy(Int.MAX_VALUE / allQ)
            progressBar2.progress = 100 * (currQ / allQ)
            if (currQ == allQ) {
                Toast.makeText(this, "Great job!", Toast.LENGTH_SHORT).show()
            }
        }
        refreshProc()

        btn1?.setOnClickListener({
            Toast.makeText(this, "answer 1", Toast.LENGTH_SHORT).show()
            currQ++
            refreshProc()
        })

        btn2?.setOnClickListener({
            Toast.makeText(this, "answer 2", Toast.LENGTH_SHORT).show()
            currQ++
            refreshProc()
        })

        btn3?.setOnClickListener({
            Toast.makeText(this, "answer 3", Toast.LENGTH_SHORT).show()
            currQ++
            refreshProc()
        })

        btn4?.setOnClickListener({
            Toast.makeText(this, "answer 4", Toast.LENGTH_SHORT).show()
            currQ++
            refreshProc()
        })

        btnStop?.setOnClickListener({
            finish()
        })
    }
}
