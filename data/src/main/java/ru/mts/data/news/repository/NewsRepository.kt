package ru.mts.data.news.repository

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.mts.data.news.db.NewsEntity
import ru.mts.data.news.db.NewsLocalDataSource
import ru.mts.data.news.remote.*
import ru.mts.data.utils.Result
import ru.mts.data.utils.doOnError
import ru.mts.data.utils.doOnSuccess
import timber.log.Timber

class NewsRepository(
    private val newsLocalDataSource: NewsLocalDataSource,
    private val newsRemoteDataSource: NewsRemoteDataSource
) {
    suspend fun getNews(): Flow<Result<List<News>, Throwable>> {
        return flow {
            newsLocalDataSource.getNews()
                .doOnSuccess {
                    emit(Result.Success(it.toNewsListItems()))
                }
                .doOnError {
                    emit(Result.Error(Throwable("Не удалось получить список новостей")))
                }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun syncNews() {
        val res = newsRemoteDataSource.getNews()
        Log.d("NewsTest", "response mock: $res")
        res
            .doOnSuccess {
                it.data?.toNewsEntity()?.let { news ->
                    newsLocalDataSource.fillNews(news)
                }
            }.doOnError {
                Timber.tag("NewsRepository").d("Sync news failed")
            }
    }

    suspend fun isEmptyNews(): Boolean = newsLocalDataSource.isEmptyNews()

    private fun List<NewsEntity?>.toNewsListItems() = this.map { item ->
        News(
            id = item?.id ?: -1,
            title = item?.title ?: "",
            poster = item?.poster ?: "",
            content = item?.content ?: ""
        )
    }

    fun List<NewsItemResponse>.toNewsEntity() = this.map { item ->
        NewsEntity(
            id = item.id,
            title = item.title ?: "",
            poster = item.poster ?: "",
            content = item.content ?: ""
        )
    }
}
