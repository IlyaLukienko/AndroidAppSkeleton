package com.lukienko.androidappskeleton.domain.interactor

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lukienko.androidappskeleton.RxImmediateSchedulerRule
import com.lukienko.androidappskeleton.data.entity.Character
import com.lukienko.androidappskeleton.domain.repository.ICharacterRepository
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import kotlin.test.assertFails
import kotlin.test.assertNotNull
import kotlin.test.assertNotSame

class CharacterInteractorTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @RelaxedMockK
    lateinit var characterRepository: ICharacterRepository
    private lateinit var interactor: CharacterInteractor

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        interactor = CharacterInteractor(characterRepository)
    }

    @Test
    fun `When incorrect character id passed to load character, the request fails`() {
        assertFails { interactor.loadCharacter(any()) }
    }

    @Test
    fun `When correct character id passed to load residents, the response is not null`() {
        val result = interactor.loadResidents(1)
        assertNotNull(result)
    }

    @Test
    fun `When characters are sorted, the order of elements should be different`() {
        val characters = mockk<List<Character>>(relaxed = true)
        val sortedCharacters =
            interactor.getSortedCharactersByDate(
                characters,
                false
            )

        assertNotSame(characters, sortedCharacters)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }
}