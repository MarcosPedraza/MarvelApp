package com.marcospb.marvelapp.ui.list_character.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcospb.marvelapp.data.model.CharacterItem
import com.marcospb.marvelapp.data.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Scope


@HiltViewModel
class CharacterListViewModel @Inject constructor(private val repository: CharacterRepository) :
    ViewModel() {


    private var currentOffset: Int? = null
    val characterList = mutableListOf<CharacterItem>()

    private val _characterListLivedata: MutableLiveData<List<CharacterItem>> = MutableLiveData()
    val characterListLivedata = _characterListLivedata


    private val _onErrorListener: MutableLiveData<String> = MutableLiveData()
    val onErrorListener = _onErrorListener


    fun getCharacterList() {
        viewModelScope.launch(Dispatchers.IO) {
            val offset = currentOffset ?: 0
            try {
                val list = repository.getCharacterList(offset)
                if (list.isSuccessful) {
                    list.body()?.data?.results?.let { value ->
                        characterList.addAll(value)
                        _characterListLivedata.postValue(characterList.toList())
                    }

                    val offsetValue = list.body()?.data?.offset?.plus(1)
                    offsetValue?.let {
                        currentOffset = it
                    }
                } else {
                    val error = list.errorBody().toString()
                    Log.e("Error", "$error")
                    _onErrorListener.postValue(error)
                }

            } catch (ex: Exception) {
                _onErrorListener.postValue("Error: ${ex.message}")
            }
        }
    }

    fun getCharacterDetail(characterId: Int) {



    }


}