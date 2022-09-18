package com.marcospb.marvelapp.data.remote

import com.marcospb.marvelapp.data.model.ResponseCharacterList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import java.sql.Timestamp

interface MarvelApiService {


    @GET("characters")
    suspend fun getCharacterListPaging(
        @Query("apikey") apiKey: String,
        @Query("ts") timestamp: String,
        @Query("hash") hash: String,
        @Query("limit") limit: Int = ITEM_LIMIT,
        @Query("offset") offset: Int
    ): Response<ResponseCharacterList>

}