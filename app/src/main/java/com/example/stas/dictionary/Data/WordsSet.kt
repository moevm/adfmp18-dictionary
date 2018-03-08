package com.example.stas.dictionary.Data

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by stas on 07.03.18.
 */
class WordsSet : Parcelable {
    var name: String

    var words: Array<String>

    constructor(name: String, words: Array<String>) {
        this.name = name
        this.words = words
    }

    private constructor(source: Parcel) : this(
        name = source.readString(),
        words = source.createStringArray()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeStringArray(words)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<WordsSet> = object : Parcelable.Creator<WordsSet> {
            override fun createFromParcel(source: Parcel): WordsSet = WordsSet(source)
            override fun newArray(size: Int): Array<WordsSet?> = arrayOfNulls(size)
        }
    }
}