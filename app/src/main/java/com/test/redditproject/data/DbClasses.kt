package com.test.redditproject.data

import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.RoomDatabase

@Database(entities = arrayOf(DataInfo::class), version = 6)
abstract class AppDataBase : RoomDatabase() {
    abstract fun postsDao(): PostsDao

}

@Entity
class DataInfo {
    @PrimaryKey
    var id: String = ""
    var title: String? = null
    var selftext: String? = null
    var score: Int = 0
    var thumbnail: String? = null
    var author: String? = null
    var num_comments: Int = 0
    var url: String? = null
    var created_utc: Long = 0L
}