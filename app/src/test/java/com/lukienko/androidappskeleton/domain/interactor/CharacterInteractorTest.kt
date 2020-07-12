package com.lukienko.androidappskeleton.domain.interactor

import com.lukienko.androidappskeleton.domain.repository.ICharacterRepository
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Before

class CharacterInteractorTest {
    @RelaxedMockK
    lateinit var characterRepository: ICharacterRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }
}