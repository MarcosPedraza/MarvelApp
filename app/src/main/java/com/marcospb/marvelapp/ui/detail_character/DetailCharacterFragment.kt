package com.marcospb.marvelapp.ui.detail_character

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.marcospb.marvelapp.R
import com.marcospb.marvelapp.data.model.DataDetail
import com.marcospb.marvelapp.databinding.FragmentDetailCharacterBinding
import com.marcospb.marvelapp.ui.detail_character.viewmodel.DetailCharacterViewModel
import dagger.hilt.android.AndroidEntryPoint


/**
 * Detail Character fragment
 *
 * @constructor Create Character fragment
 */
@AndroidEntryPoint
class DetailCharacterFragment : Fragment() {


    private lateinit var binding: FragmentDetailCharacterBinding


    val args: DetailCharacterFragmentArgs by navArgs()

    private val viewModel by viewModels<DetailCharacterViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailCharacterBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        val idDetail = args.characterId
        viewModel.getCharacterDetail(idDetail)

    }

    private fun setupObservers() {
        viewModel.detailRequestStatus.observe(viewLifecycleOwner, Observer {
            setCharacterInfo(it)
        })

        viewModel.onErrorRequest.observe(viewLifecycleOwner, Observer {


        })

    }

    private fun setCharacterInfo(data: DataDetail) {
        //binding.titleItem

        Glide.with(requireContext())
            .load("${data.results[0].thumbnail?.path}.${data.results[0].thumbnail?.extension}")
            .placeholder(R.drawable.ic_hide_image)
            .into(binding.characterImage)


        binding.characterName.text = data.results[0].name
        binding.description.text = data.results[0].description



    }


    companion object {

        fun newInstance() =
            DetailCharacterFragment().apply {

            }
    }
}