package com.lukienko.androidappskeleton.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.lukienko.androidappskeleton.RxImmediateSchedulerRule
import com.lukienko.androidappskeleton.data.entity.Character
import com.lukienko.androidappskeleton.domain.interactor.CharacterInteractor
import com.lukienko.androidappskeleton.presentation.characterDetails.CharacterDetailsViewModel
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CharacterDetailsViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @RelaxedMockK
    lateinit var interactor: CharacterInteractor

    private lateinit var viewModel: CharacterDetailsViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = CharacterDetailsViewModel(interactor)
    }

    @Test
    fun `When residents are loaded with success or error, hide progress indicator and error message`() {
        val loadingObserver = spyk<Observer<Boolean>>()
        val errorObserver = spyk<Observer<Boolean>>()
        viewModel.loadingProgressVisible.observeForever(loadingObserver)
        viewModel.errorMessageVisible.observeForever(errorObserver)
        every { interactor.loadResidents(1) } returns Single.just(listOf())

        viewModel.loadData(1)
        verify { loadingObserver.onChanged(false) }
        verify { errorObserver.onChanged(false) }
    }

    @Test
    fun `When character is loaded, update live data`() {
        val characterObserver = spyk<Observer<Character>>()
        viewModel.character.observeForever(characterObserver)
        val mockCharacter = mockk<Character>()
        every { interactor.loadCharacter(1) } returns Single.just(mockCharacter)

        viewModel.loadData(1)
        val characterSlot = slot<Character>()
        verify { characterObserver.onChanged(capture(characterSlot)) }
        confirmVerified(characterObserver)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }
}