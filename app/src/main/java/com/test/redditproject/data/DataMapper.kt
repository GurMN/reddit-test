package com.test.redditproject.data

import com.test.redditproject.api.DataModel

fun DataModel.mapModelToInfo(): DataInfo {
    val dataInfo = DataInfo()
    dataInfo.id = id.toString()
    dataInfo.title = title
    dataInfo.selftext = selftext
    dataInfo.score = score
    dataInfo.thumbnail = thumbnail
    dataInfo.author = author
    dataInfo.num_comments = num_comments
    dataInfo.url = url
    dataInfo.created_utc = created_utc
    return dataInfo
}

fun DataInfo.mapInfoToModel(): DataModel {
    val dataModel = DataModel(
        id
        , title
        , selftext
        , score
        , thumbnail
        , author
        , num_comments
        , url
        , created_utc
    )
    return dataModel
}