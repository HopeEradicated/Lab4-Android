package com.example.album.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.album.network.*
import com.example.album.network.albumApi
import com.example.album.network.albumApi.moshiService
import com.squareup.moshi.JsonAdapter
import kotlinx.coroutines.launch
import org.json.JSONObject

enum class ApiStatus { LOADING, ERROR, DONE }

class OverviewViewModel : ViewModel() {

    companion object {
        var id: String = "207800062";
        var token: String = "45f2d7b945f2d7b945f2d7b9ff46e337b1445f245f2d7b9266b56c7cfec3434c8a906bd";
    }

    private val _status = MutableLiveData<ApiStatus>()

    val status: LiveData<ApiStatus> = _status

    private val _photos = MutableLiveData<List<Albom>>()

    val photos: LiveData<List<Albom>> = _photos

    init {
        getMarsPhotos()
    }

    private fun getMarsPhotos() {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                val response = albumApi.retrofitService.albumsGet(token, id, 1,1, "5.131")
                val titleList = StringBuilder()
                val items = JSONObject(response).getJSONObject("response").getJSONArray("items")
                for (i in 0 until items.length()) {
                    titleList.append("{\"title\":\"" + items.getJSONObject(i).getString("title") + "\",\"id\":\"" +
                            items.getJSONObject(i).getString("id") + "\",\"size\":\"" + items.getJSONObject(i).getString("size")
                            + "\",\"thumb_src\":\"" + items.getJSONObject(i).getString("thumb_src")
                            + "\"};")
                }
                val temp: ArrayList<Albom> = arrayListOf()
                val tempArr: List<String> = titleList.toString().split(';');
                for (i in 0 until items.length()) {
                    val jsonAdapter: JsonAdapter<Albom> = moshiService.adapter(
                        Albom::class.java
                    )
                    temp.add(i, jsonAdapter.fromJson(tempArr[i])!!)
                }

                _photos.value = temp
                _status.value = ApiStatus.DONE
                } catch (e: Exception) {
                    _status.value = ApiStatus.ERROR
                    _photos.value = listOf()
            }
        }
    }
}
