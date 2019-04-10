package com.pfariasmunoz.roomwordssample

import android.app.Application
import androidx.lifecycle.LiveData
import com.pfariasmunoz.roomwordssample.db.Word
import com.pfariasmunoz.roomwordssample.db.WordDao
import com.pfariasmunoz.roomwordssample.db.WordRoomDatabase

class WordRepository(application: Application) {
    private var mWordDao: WordDao? = null
    private var mAllWords: LiveData<List<Word>>? = null

    init {
        val db = WordRoomDatabase.getDatabase(application)
        mWordDao = db?.wordDao()
        mAllWords = mWordDao?.getAllWords()
    }

}