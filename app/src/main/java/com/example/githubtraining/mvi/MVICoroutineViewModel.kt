package com.example.githubtraining.mvi

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.liveData
import androidx.lifecycle.observe
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

abstract class MVICoroutineViewModel<S : State, I : Intent, C : Change> : ViewModel(),
    IntentConsumer<I, C>, StateProducer<S> {
    private val coroutineContext = viewModelScope.coroutineContext + Dispatchers.Default

    protected abstract var currentState: S

    private val changes = BroadcastChannel<C>(1)

    private var intentsJob: Job? = null

    override val state: LiveData<S> = liveData(coroutineContext) {
        changes.openSubscription().consumeAsFlow().scan(currentState) { accumulator, value ->
            mutate(accumulator, value).also {
                currentState = it
            }
        }.collect {
            emit(it)
        }
    }

    fun attachIntentProducer(producer: IntentProducer<I>) {
        intentsJob?.cancel()
        intentsJob = viewModelScope.launch(Dispatchers.Main) {
            producer.intents.collect {
                changes.send(consume(it))
            }
        }
    }

    protected fun change(change: C) {
        viewModelScope.launch {
            changes.send(change)
        }
    }

    override fun onCleared() {
        super.onCleared()

        intentsJob?.cancel()
    }

    protected abstract suspend fun mutate(state: S, change: C): S
}

fun <T : MVICoroutineViewModel<S, I, C>, S : State, I : Intent, C : Change, V> V.initViewModel(
    viewModelClass: KClass<T>,
    shared: Boolean = false
)
    where V : IntentProducer<I>, V : StateConsumer<S>, V : Fragment, V : HasViewModelFactory {
    val provider = if (shared) {
        ViewModelProviders.of(requireActivity(), viewModelFactory)
    } else {
        ViewModelProviders.of(this, viewModelFactory)
    }
    provider.get(viewModelClass.java).let { viewModel ->
        viewModel.attachIntentProducer(this)
        viewModel.state.observe(viewLifecycleOwner) { state ->
            consume(state)
        }
    }
}