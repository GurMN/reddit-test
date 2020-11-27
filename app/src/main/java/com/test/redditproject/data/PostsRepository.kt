package com.test.redditproject.data

class PostsRepository(val db: AppDataBase) {
    companion object {
        private var instance: PostsRepository? = null
        fun createInstance(db: AppDataBase) {
            if (instance == null) {
                instance = PostsRepository(db)
            }
        }

        fun getInstance(): PostsRepository? {
            return instance
        }
    }


    fun getTopList() = db.postsDao().getAll()

    fun savePost(postInfo: DataInfo) {
        db.postsDao().insert(postInfo)
    }

}