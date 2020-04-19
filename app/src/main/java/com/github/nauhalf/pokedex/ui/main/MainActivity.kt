package com.github.nauhalf.pokedex.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.github.nauhalf.pokedex.R
import com.github.nauhalf.pokedex.model.Pokemon
import com.github.nauhalf.pokedex.ui.detail.DetailPokemonActivity
import com.github.nauhalf.pokedex.utils.getJsonFromRaw
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUp()
    }

    private fun setUp() {
        setSupportActionBar(toolbar)
        val pGridAdapter = PokedexGridAdapter(onClickListener = {pokemon->
            val intent = DetailPokemonActivity.newIntent(this)
            intent.putExtra(DetailPokemonActivity.POKEMON_KEY, pokemon)
            startActivity(intent)
        })
        recyclerPokedex.apply {
            adapter = pGridAdapter
            layoutManager = GridLayoutManager(this.context, 3)
        }

        //get json then set it to string
        val pokemonString = getJsonFromRaw(R.raw.pokedex)

        //parse string to Object via Gson
        val pokemonList = Gson().fromJson(pokemonString, Array<Pokemon>::class.java).toList()
        pGridAdapter.addList(pokemonList)
    }
}
