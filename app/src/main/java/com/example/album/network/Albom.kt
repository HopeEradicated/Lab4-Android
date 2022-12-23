package com.example.album.network

import com.squareup.moshi.Json

data class Albom(
        @Json(name = "title") val alname: String,
        @Json(name = "id") val id: String,
        @Json(name = "size") val alnumberoffl: String,
        @Json(name = "thumb_src") val imgSrcUrl: String
)