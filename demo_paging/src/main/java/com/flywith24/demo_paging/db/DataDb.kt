package com.flywith24.demo_paging.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.flywith24.demo_paging.Api

/**
 * @author yyz (杨云召)
 * @date   2020/5/9
 * time   11:08
 * description
 */
@Database(
    entities = [Api.Data::class],
    version = 1,
    exportSchema = false
)
abstract class DataDb : RoomDatabase() {

    companion object {
        @Volatile
        private var instance: DataDb? = null

        fun getInstance(context: Context): DataDb {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): DataDb {
            return Room.databaseBuilder(context, DataDb::class.java, "data-db")
                .build()
        }
    }

    abstract fun dataDao(): DataDao
}