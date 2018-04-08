package com.example.stas.dictionary.Activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ProgressBar
import android.widget.Toast
import com.example.stas.dictionary.R
import kotlinx.android.synthetic.main.activity_word_add.*


class WordsAddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word_add)
        progressBar.visibility = ProgressBar.INVISIBLE
        var text = intent
                .getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT)
        // process the text
        // here we have highlighted text
        if (text == null)
            text = ""
        inWord.setText(text.toString())
        textView2.text = text
        var clickCtr = 0
        button.setOnClickListener {
            if (clickCtr % 2 == 0) {
                progressBar.incrementProgressBy(5)
                progressBar.visibility = ProgressBar.VISIBLE
                progressBar.progress = 100
                Toast.makeText(this, "Word added", Toast.LENGTH_SHORT).show()

//                val intent = Intent(this@WordsAddActivity,MainActivity.SectionsPagerAdapter(2)::class.java)
//                intent.putExtra("words0", text)
//                startActivity(intent)
                this.finish()
            } else {
                progressBar.visibility = ProgressBar.INVISIBLE
                clickCtr += 1
            }


        }

    }
}