package com.test.redditproject

import android.app.Application
import androidx.room.Room
import com.test.redditproject.data.AppDataBase
import com.test.redditproject.data.PostsRepository

class PostsApp : Application() {
    override fun onCreate() {
        super.onCreate()
        PostsRepository.createInstance(provideDataBase())
    }

    private fun provideDataBase(): AppDataBase {
        return Room.databaseBuilder(this, AppDataBase::class.java, "children")
            .build()
    }
}