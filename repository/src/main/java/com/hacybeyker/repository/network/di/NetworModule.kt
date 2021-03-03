package com.hacybeyker.repository.network.di

import com.hacybeyker.repository.network.implement.MovieNetwork
import com.hacybeyker.usecases.repository.network.IMovieRepositoryNetwork
import org.koin.dsl.module

val networkModule = module {
    single<IMovieRepositoryNetwork> { MovieNetwork() }
}