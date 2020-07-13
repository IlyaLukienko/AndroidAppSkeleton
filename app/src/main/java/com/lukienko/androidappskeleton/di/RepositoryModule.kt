package com.lukienko.androidappskeleton.di

import com.lukienko.androidappskeleton.data.repository.CharacterRepository
import com.lukienko.androidappskeleton.domain.repository.ICharacterRepository
import org.koin.dsl.module

val repositoryModule = module(override = true) {
    single <ICharacterRepository> { CharacterRepository(get()) }
}