package com.example.githubtraining.mvi

import androidx.lifecycle.LiveData

interface StateProducer<T : State> {
    val state: LiveData<T>
}