package ru.mts.data.news.remote

import android.content.Context
import com.google.gson.reflect.TypeToken
import retrofit2.http.Headers
import retrofit2.http.POST
import ru.mts.data.utils.MockJsonReader

interface NewsApiService {

    @POST("api/v1/sample")
    @Headers("Content-Type:application/json; charset=utf-8;")
    suspend fun getSampleData(): BaseResponse<List<NewsItemResponse>>

    @POST("login")
    @Headers("Content-Type:application/json; charset=utf-8;")
    suspend fun login(): Boolean
}

class NewsApiServiceMock(
    private val context: Context
) : NewsApiService {
    override suspend fun getSampleData(): BaseResponse<List<NewsItemResponse>> {
        return MockJsonReader.read(
            context,
            "news.json",
            object : TypeToken<BaseResponse<List<NewsItemResponse>>>() {}
        )
    }

    override suspend fun login(): Boolean {
        return true
    }

}
