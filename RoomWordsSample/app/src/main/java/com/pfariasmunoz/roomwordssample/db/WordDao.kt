package com.pfariasmunoz.roomwordssample.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WordDao {
    @Insert
    fun intert(word: Word)
    @Query("DELETE FROM word_table")
    fun deleteAll()
    @Query("SELECT * FROM word_table ORDER BY word ASC")
    fun getAllWords(): List<Word>
}