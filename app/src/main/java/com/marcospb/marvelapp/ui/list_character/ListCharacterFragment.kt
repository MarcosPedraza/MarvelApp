package com.marcospb.marvelapp.ui.list_character

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.marcospb.marvelapp.R
import com.marcospb.marvelapp.databinding.FragmentListCharacterBinding
import com.marcospb.marvelapp.ui.list_character.adapter.CharacterListAdapter
import com.marcospb.marvelapp.ui.list_character.viewmodel.CharacterListViewModel
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


@AndroidEntryPoint
class ListCharacterFragment : Fragment() {

    lateinit var binding: FragmentListCharacterBinding


    private val viewModel by viewModels<CharacterListViewModel>()

    lateinit var characterAdapter: CharacterListAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentListCharacterBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapters()
        viewModel.getCharacterList()
        binding.floatingActionButton.setOnClickListener {
            viewModel.getCharacterList()
        }

        viewModel.characterListLivedata.observe(viewLifecycleOwner, Observer { characterList ->
            characterAdapter.submitList(characterList)
        })
    }

    override fun onPause() {
        super.onPause()
        viewModel.characterList.clear()
    }

    private fun initAdapters() {

        characterAdapter = CharacterListAdapter {
            val dir = it.id?.let { it1 ->
                ListCharacterFragmentDirections.actionListCharacterFragmentToDetailCharacterFragment(
                    it1
                )
            }

            dir?.let { it1 -> findNavController().navigate(it1) }


        }
        binding.rvCharacters.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCharacters.adapter = characterAdapter


        binding.rvCharacters.setOnScrollChangeListener { view, i, i2, i3, i4 ->

        }


    }


    companion object {
        @JvmStatic
        fun newInstance() =
            ListCharacterFragment().apply {

            }
    }
}