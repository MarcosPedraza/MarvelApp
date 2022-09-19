package com.marcospb.marvelapp.ui.detail_character.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marcospb.marvelapp.data.model.CharacterDetailResponse
import com.marcospb.marvelapp.data.repository.CharacterRepository
import com.marcospb.marvelapp.ui.detail_character.DetailCharacterFragment
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class DetailCharacterViewModel @Inject constructor(private val repository: CharacterRepository) :
    ViewModel() {


    private val _detailRequestStatus: MutableLiveData<CharacterDetailResponse> = MutableLiveData()
    val detailRequestStatus = _detailRequestStatus


    private val _onErrorRequest: MutableLiveData<String> = MutableLiveData()
    val onErrorRequest = _onErrorRequest


    fun getCharacterDetail(characterId: Int) {

    }
}