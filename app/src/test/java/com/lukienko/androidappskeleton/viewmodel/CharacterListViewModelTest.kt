package com.lukienko.androidappskeleton.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.lukienko.androidappskeleton.RxImmediateSchedulerRule
import com.lukienko.androidappskeleton.domain.interactor.CharacterInteractor
import com.lukienko.androidappskeleton.presentation.characterList.CharacterListViewModel
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CharacterListViewModelTest{

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @RelaxedMockK
    lateinit var interactor: CharacterInteractor

    private lateinit var viewModel: CharacterListViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = CharacterListViewModel(interactor)
    }

    @Test
    fun `When load characters, show progress indicator`() {
        val loadingObserver = spyk<Observer<Boolean>>()
        viewModel.loadingProgressVisible.observeForever(loadingObserver)
        viewModel.loadCharacters()
        verify { loadingObserver.onChanged(true) }
    }

    @Test
    fun `When load characters with success, error message doesn't appear`() {
        val errorObserver = spyk<Observer<Boolean>>()
        viewModel.errorMessageVisible.observeForever(errorObserver)
        every { interactor.loadCharacters() } returns Single.just(listOf())
        viewModel.loadCharacters()
        verify { errorObserver.onChanged(false) }
    }

    @Test
    fun `When load characters with error, error message appears`() {
        val errorObserver = spyk<Observer<Boolean>>()
        viewModel.errorMessageVisible.observeForever(errorObserver)
        every { interactor.loadCharacters() } returns Single.error(Throwable("Some error occurred"))
        viewModel.loadCharacters()
        verify { errorObserver.onChanged(true) }
    }

    @After
    fun tearDown() {
        unmockkAll()
    }
}