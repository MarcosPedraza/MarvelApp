package com.marcospb.marvelapp.data.repository

import com.marcospb.marvelapp.BuildConfig
import com.marcospb.marvelapp.data.model.Data
import com.marcospb.marvelapp.data.model.ResponseCharacterList
import com.marcospb.marvelapp.data.remote.MarvelApiService
import com.marcospb.marvelapp.utils.StringUtils
import com.marcospb.marvelapp.utils.StringUtils.md5
import retrofit2.Response
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*
import javax.inject.Inject

class CharacterRepository @Inject constructor(private val apiService: MarvelApiService) {


    suspend fun getCharacterList(offset: Int): Response<ResponseCharacterList> {
        val ts = System.currentTimeMillis()
        val hash = "$ts${BuildConfig.PRIVATE_API_KEY}${BuildConfig.API_KEY}".md5()
        return apiService.getCharacterListPaging(
            BuildConfig.API_KEY,
            timestamp = ts.toString(),
            hash,
            offset = offset
        )
    }
}