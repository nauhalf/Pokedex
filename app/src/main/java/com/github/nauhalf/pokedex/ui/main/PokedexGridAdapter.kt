package com.github.nauhalf.pokedex.ui.main

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.github.nauhalf.pokedex.R
import com.github.nauhalf.pokedex.model.Pokemon
import kotlinx.android.synthetic.main.item_pokedex_grid.view.*

class PokedexGridAdapter(private val pokemonList: MutableList<Pokemon> = mutableListOf(), private val onClickListener : (Pokemon) -> Unit) : RecyclerView.Adapter<PokedexGridAdapter.PokedexGridViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokedexGridViewHolder {
        //Create PokedexGridViewHolder object with item_pokedex_grid.xml layout
        return PokedexGridViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pokedex_grid, parent,false)
        )
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    override fun onBindViewHolder(holder: PokedexGridViewHolder, position: Int) {
        val pokemon = pokemonList[position]
        holder.bind(pokemon)
        addList(null)
    }

    fun addList(list: List<Pokemon>?){
        list?.let {pokemon ->
            pokemonList.addAll(pokemon)
        }
    }

    inner class PokedexGridViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun loadImage(url: String){
            Glide.with(view)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerInside()
                .into(view.imgPokemon)

        }

        fun bind(p: Pokemon){
            loadImage(p.imageUrl as String)
            view.setOnClickListener {
                onClickListener(p)
            }
        }
    }

}