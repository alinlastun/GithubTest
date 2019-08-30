package com.example.githubtraining.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher
import java.util.concurrent.Executors

/**
 * Add your Coroutine Dispatcher here if the default ones are not acceptable
 */
object AppDispatchers {
    val viewModel: CoroutineDispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()
}

val Dispatchers.ViewModel: CoroutineDispatcher
    get() = AppDispatchers.viewModel