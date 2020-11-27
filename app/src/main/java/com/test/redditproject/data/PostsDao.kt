package com.test.redditproject.data

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface PostsDao {
    @Query("SELECT * FROM DataInfo")
    fun getAll(): LiveData<List<DataInfo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(post: DataInfo?)

    @Update
    fun update(post: DataInfo?)

    @Delete
    fun delete(post: DataInfo?)
}