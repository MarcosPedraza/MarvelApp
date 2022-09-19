package com.marcospb.marvelapp.ui.detail_character.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcospb.marvelapp.data.model.CharacterDetailResponse
import com.marcospb.marvelapp.data.model.DataDetail
import com.marcospb.marvelapp.data.repository.CharacterRepository
import com.marcospb.marvelapp.ui.detail_character.DetailCharacterFragment
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject


/**
 * Detail character view model
 *
 * @property repository
 * @constructor Create Detail character View Model
 */
@HiltViewModel
class DetailCharacterViewModel @Inject constructor(private val repository: CharacterRepository) :
    ViewModel() {


    private val _detailRequestStatus: MutableLiveData<DataDetail> = MutableLiveData()
    val detailRequestStatus = _detailRequestStatus


    private val _onErrorRequest: MutableLiveData<String> = MutableLiveData()
    val onErrorRequest = _onErrorRequest


    /**
     * Get character detail
     *
     * @param characterId
     */
    fun getCharacterDetail(characterId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val request = repository.getCharacterDetail(characterId)
                if (request.isSuccessful) {
                    _detailRequestStatus.postValue(request.body()?.data)
                } else {
                    _onErrorRequest.postValue(request.errorBody().toString())
                }
            } catch (ex: Exception) {
                _onErrorRequest.postValue(ex.message)
            }

        }
    }
}