package com.marcospb.marvelapp.data.remote

import com.marcospb.marvelapp.data.model.CharacterDetailResponse
import com.marcospb.marvelapp.data.model.ResponseCharacterList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.sql.Timestamp

interface MarvelApiService {


    /**
     * Get character list paging
     *
     * @param apiKey
     * @param timestamp
     * @param hash
     * @param limit
     * @param offset
     * @param orderBy
     * @return
     */
    @GET("characters")
    suspend fun getCharacterListPaging(
        @Query("apikey") apiKey: String,
        @Query("ts") timestamp: String,
        @Query("hash") hash: String,
        @Query("limit") limit: Int = ITEM_LIMIT,
        @Query("offset") offset: Int,
        @Query("orderBy") orderBy: String = "name"
    ): Response<ResponseCharacterList>


    /**
     * Get character detail by id
     *
     * @param idCharacter
     * @return
     */
    @GET("characters/{idCharacter}")
    suspend fun getCharacterDetailById(
        @Path("idCharacter") idCharacter: Int,
        @Query("apikey") apiKey: String,
        @Query("ts") timestamp: String,
        @Query("hash") hash: String
    ): Response<CharacterDetailResponse>


}