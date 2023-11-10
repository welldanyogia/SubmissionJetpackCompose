package com.example.submissionjetpackcompose.data.repository

import com.example.submissionjetpackcompose.data.model.HeroesData
import com.example.submissionjetpackcompose.data.model.HeroesList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class HeroRepository {
    private val heroes = mutableListOf<HeroesList>()
    init {
        if (heroes.isEmpty()){
            HeroesData.heroes.forEach {
                heroes.add(HeroesList(it,0))
            }
        }
    }
    fun getAllHeroes(): Flow<List<HeroesList>> {
        return flowOf(heroes)
    }
    fun getHeroesById(heroId : Long):HeroesList{
        return heroes.first{
            it.heroesList.id == heroId
        }
    }
    fun searchHeroes(query: String): List<HeroesList>{
        return heroes.filter {
            it.heroesList.name.contains(query, ignoreCase = true)
        }
    }
    companion object {
        @Volatile
        private var instance: HeroRepository? = null

        fun getInstance(): HeroRepository =
            instance ?: synchronized(this) {
                HeroRepository().apply {
                    instance = this
                }
            }
    }
}