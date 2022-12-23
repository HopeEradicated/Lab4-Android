package com.example.album.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.converter.scalars.ScalarsConverterFactory

private const val BASE_URL = "https://api.vk.com/method/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface ApiService {

    @GET("photos.getAlbums")
    suspend fun albumsGet(
        @Query("access_token") token: String,
        @Query("owner_id") id: String,
        @Query("need_system") system: Int,
        @Query("need_covers") covers: Int,
        @Query("v") v: String,
    ): String

    @GET("photos.get")
    suspend fun photosGet(
        @Query("access_token") token: String,
        @Query("owner_id") id: String,
        @Query("album_id") system: String,
        @Query("rev") rev: Int,
        @Query("v") v: String,
    ): String

}

object albumApi {
    val retrofitService: ApiService by lazy { retrofit.create(ApiService::class.java) }
    val moshiService: Moshi = moshi
}
