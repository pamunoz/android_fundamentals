package com.pfariasmunoz.roomwordssample.db

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class WordRoomDatabase: RoomDatabase() {
    abstract fun wordDao(): WordDao

    companion object {
        private var INSTANCE: WordRoomDatabase? = null

        fun getDatabase(context: Context): WordRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(WordRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        // Create database here
                        INSTANCE = Room.databaseBuilder(context.applicationContext, WordRoomDatabase::class.java, "word_database")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build()
                    }
                }
            }
            return INSTANCE
        }

        private val sRoomDatabaseCallback: RoomDatabase.Callback = object : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                PopulateDbAsync(INSTANCE!!).execute()
            }
        }

        /**
         * Populate the database in the background.
         */
        private class PopulateDbAsync(db: WordRoomDatabase) : AsyncTask<Unit, Unit, Unit>() {

            private val mDao: WordDao = db.wordDao()
            private val words = arrayListOf("dolphin", "crocodile", "cobra")

            override fun doInBackground(vararg params: Unit?) {
                // Start the app with a clean database every time.
                // Not needed if you only populate the database
                // when it is first created
                mDao.deleteAll()
                for (word in words) {
                    mDao.intert(Word(word))
                }
            }

        }
    }

}