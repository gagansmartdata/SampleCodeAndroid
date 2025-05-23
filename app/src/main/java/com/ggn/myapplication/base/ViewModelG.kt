package com.ggn.myapplication.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * use this viewModel if you need to maintain any state for the activity.
 * loading, error and data (can be used for API call mostly, else use normal ViewModel)
 */
abstract class ViewModelG<S> : ViewModel() {
    protected abstract val mutableState: MutableStateFlow<S>

    fun state(): StateFlow<S> = mutableState
    private fun stateVal(): S = state().value

  //use this function to update state for any activity from viewmodel
    protected suspend fun updateUIState(update: suspend (S) -> S) {
        mutableState.value = update(stateVal())
    }

    protected fun updateStateNonBlocking(update: (S) -> S) {
        mutableState.value = update(stateVal())
    }
}
