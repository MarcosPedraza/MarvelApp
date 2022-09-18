package com.marcospb.marvelapp.ui.detail_character

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marcospb.marvelapp.R


class DetailCharacterFragment : Fragment() {


    //private val _binder


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_detail_character, container, false)
    }

    companion object {

        fun newInstance() =
            DetailCharacterFragment().apply {

            }
    }
}