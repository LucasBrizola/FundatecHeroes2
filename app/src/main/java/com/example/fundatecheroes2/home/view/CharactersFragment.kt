package com.example.fundatecheroes2.home.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.fundatecheroes2.App
import com.example.fundatecheroes2.databinding.FragmentCharactersBinding
import com.example.fundatecheroes2.home.presentation.ListItemAdapter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.example.fundatecheroes2.character.presentation.Character

class CharactersFragment : Fragment() {
    private lateinit var binding: FragmentCharactersBinding

    private val moshi by lazy {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val preferences = App.context.getSharedPreferences("bd", Context.MODE_PRIVATE)

        val characterListString = preferences.getString("characterList", "")
        if (characterListString.isNullOrBlank()) {
            toastRecyclerVazio()
        }
        else{
            val listType = Types.newParameterizedType(MutableList::class.java, Character::class.java)
            val adapter: JsonAdapter<MutableList<Character>> = moshi.adapter(listType)

            val result = adapter.fromJson(characterListString)


            val recyclerAdapter = ListItemAdapter()
            binding.list.adapter = recyclerAdapter

            recyclerAdapter.setItems(result!!)
        }

    }
    private fun toastRecyclerVazio() {
        Toast.makeText(requireContext(), "Recycler está vazio.", Toast.LENGTH_LONG).show()
    }

    companion object {
        fun newInstance() = CharactersFragment()
    }
}