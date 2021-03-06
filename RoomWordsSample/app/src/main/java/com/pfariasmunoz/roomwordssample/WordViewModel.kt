package com.pfariasmunoz.roomwordssample

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.pfariasmunoz.roomwordssample.db.Word

class WordViewModel(application: Application) : AndroidViewModel(application) {
    private val mWordRepository: WordRepository = WordRepository(application)
    private val mAllWords: LiveData<List<Word>>
    init {
        mAllWords = mWordRepository.allWords
    }

    val allWords = mAllWords

    fun insert(word: Word) = mWordRepository.insert(word)

    fun deleteAll() = mWordRepository.deleteAll()

    fun deleteWord(word: Word) = mWordRepository.deleteWord(word)

}