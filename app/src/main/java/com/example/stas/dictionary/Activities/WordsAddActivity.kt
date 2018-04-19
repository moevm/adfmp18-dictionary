package com.example.stas.dictionary.Activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ProgressBar
import android.widget.Toast
import com.example.stas.dictionary.Data.WordPair
import com.example.stas.dictionary.R
import com.example.stas.dictionary.database
import kotlinx.android.synthetic.main.activity_word_add.*
import org.jetbrains.anko.db.insert
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class WordsAddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word_add)
        progressBar.visibility = ProgressBar.INVISIBLE
        var text = intent
                .getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT)
//        var text1 = intent
//                .getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT)
        // process the text
        // here we have highlighted text
//        if (text == null)
//            text = ""
//        inWord.setText(text.toString())
//        inTransl.setText(text1.toString())
//        textView2.text = text
        var clickCtr = 0
        val formatter = DateTimeFormatter.ISO_DATE
        button.setOnClickListener {
            if (clickCtr % 2 == 0) {
                progressBar.incrementProgressBy(5)
                progressBar.visibility = ProgressBar.VISIBLE
                progressBar.progress = 100
                Toast.makeText(this, "WordPair added", Toast.LENGTH_SHORT).show()
                val current = LocalDateTime.now()
                val formatted = current.format(formatter)
                database.use {
                    insert(WordPair.TABLE_NAME, WordPair.COLUMN_WORD to inWord.text.toString(),
                            WordPair.COLUMN_TRANSLATE to inTransl.text.toString(),
                            WordPair.COLUMN_DATE to formatted.toString())
                }
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