package com.flywith24.demo_paging.db

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.flywith24.demo_paging.Api

/**
 * @author yyz (杨云召)
 * @date   2020/5/9
 * time   11:23
 * description
 */
@Dao
interface DataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(data: List<Api.Data>)

    @Query("SELECT * FROM data ORDER BY indexInResponse ASC")
    fun getData(): DataSource.Factory<Int, Api.Data>

}