package com.hacybeyker.usecases.di

import com.hacybeyker.usecases.usecase.MovieUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { MovieUseCase() }
}