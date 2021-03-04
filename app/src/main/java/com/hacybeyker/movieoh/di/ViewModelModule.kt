package com.hacybeyker.movieoh.di

import com.hacybeyker.movieoh.ui.detail.DetailViewModel
import com.hacybeyker.movieoh.ui.movies.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MovieViewModel() }
    viewModel { DetailViewModel() }
}