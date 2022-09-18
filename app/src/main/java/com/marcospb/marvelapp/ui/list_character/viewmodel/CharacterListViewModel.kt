package com.marcospb.marvelapp.ui.list_character.viewmodel

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
    private val characterList = mutableListOf<CharacterItem>()

    private val _characterListLivedata: MutableLiveData<List<CharacterItem>> = MutableLiveData()
    val characterListLivedata = _characterListLivedata


    fun getCharacterList() {
        viewModelScope.launch(Dispatchers.IO) {
            val offset = currentOffset ?: 0
            try {
                val list = repository.getCharacterList(offset)
                if (list.isSuccessful) {
                    list.body()?.data?.results?.let {
                        characterList.addAll(it)
                        _characterListLivedata.postValue(characterList)
                    }

                    var offsetValue = list.body()?.data?.offset?.plus(1)
                    offsetValue?.let {
                        currentOffset = it
                    }
                }

            } catch (ex: Exception) {

            }
        }
    }


}