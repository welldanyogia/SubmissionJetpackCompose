package com.example.submissionjetpackcompose.data.di

import com.example.submissionjetpackcompose.data.repository.HeroRepository

object Injection {
    fun provideRepository(): HeroRepository {
        return HeroRepository.getInstance()
    }
}