package com.pfariasmunoz.roomwordssample.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WordDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(word: Word)
    @Query("DELETE FROM word_table")
    fun deleteAll()
    @Query("SELECT * FROM word_table ORDER BY word ASC")
    fun getAllWords(): LiveData<List<Word>>
    @Query("SELECT * FROM word_table LIMIT 1")
    fun getAnyWord(): Array<Word>
}