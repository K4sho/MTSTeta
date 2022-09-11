package ru.mts.data.news.remote

import com.google.gson.annotations.SerializedName
import ru.mts.data.news.db.NewsEntity
import ru.mts.data.news.repository.News

open class BaseResponse<TClass>(
    @SerializedName("status") val status: String? = "",
    @SerializedName("data") val data: TClass? = null
)

data class NewsItemResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String? = "",
    @SerializedName("poster") val poster: String? = "",
    @SerializedName("content") val content: String? = ""
)

internal fun NewsItemResponse.toDomain(): News {
    return News(
        this.id,
        this.title ?: "",
        this.poster ?: "",
        this.content ?: ""
    )
}

internal fun NewsItemResponse.toEntity(): NewsEntity {
    return NewsEntity(
        this.id,
        this.title ?: "",
        this.poster ?: "",
        this.content ?: ""
    )
}