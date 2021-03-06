package com.pfariasmunoz.roomwordssample

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.pfariasmunoz.roomwordssample.db.Word
import com.pfariasmunoz.roomwordssample.db.WordDao
import com.pfariasmunoz.roomwordssample.db.WordRoomDatabase

class WordRepository(application: Application) {
    private var mWordDao: WordDao
    private var mAllWords: LiveData<List<Word>>

    init {
        val db = WordRoomDatabase.getDatabase(application)
        mWordDao = db?.wordDao()!!
        mAllWords = mWordDao.getAllWords()
    }

    val allWords: LiveData<List<Word>> = mAllWords

    fun insert(word: Word): AsyncTask<Word, Unit, Unit> = InsertAsyncTask(mWordDao).execute(word)

    fun deleteAll(): AsyncTask<Unit, Unit, Unit> = DeleteAllWordsAsyncTask(mWordDao).execute()

    fun deleteWord(word: Word): AsyncTask<Word, Unit, Unit> = DeleteWordAsyncTask(mWordDao).execute(word)

    companion object {
        private class InsertAsyncTask(private val dao: WordDao): AsyncTask<Word, Unit, Unit>() {
            override fun doInBackground(vararg params: Word?) = params[0]?.let { dao.insert(it) }
        }

        private class DeleteAllWordsAsyncTask(private val dao: WordDao) : AsyncTask<Unit, Unit, Unit>() {
            override fun doInBackground(vararg params: Unit?) = dao.deleteAll()
        }

        private class DeleteWordAsyncTask(private val dao: WordDao) : AsyncTask<Word, Unit, Unit>() {
            override fun doInBackground(vararg params: Word?) = params[0]?.let { dao.deleteWord(it) }
        }
    }

}