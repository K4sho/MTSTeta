package ru.mts.data.news.db

import androidx.room.*

@Dao
interface NewsDao {
    @Query("SELECT * FROM news")
    fun getAll(): List<NewsEntity?>?

    @Query("SELECT * FROM news WHERE id = :id")
    fun getById(id: Long): NewsEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(news: List<NewsEntity>)

    @Update
    fun update(news: NewsEntity)

    @Delete
    fun delete(news: NewsEntity)

    @Query("SELECT COUNT(*) FROM news")
    fun newsCounts(): Int
}