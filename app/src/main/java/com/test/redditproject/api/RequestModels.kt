package com.test.redditproject.api

data class ParentRequestModel(
    val kind: String
    , val data: DataExternalModel
)

data class DataExternalModel(
    val children: List<ChildrenModel>
    , val after: String?
)

data class ChildrenModel(val data: DataModel?)

data class DataModel(
    val id: String?
    , val title: String?
    , val selftext: String?
    , val score: Int
    , val thumbnail: String?
    , val author: String?
    , val num_comments: Int
    , val url: String?
    , val created_utc: Long
)