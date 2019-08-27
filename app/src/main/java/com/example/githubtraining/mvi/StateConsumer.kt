package com.example.githubtraining.mvi

interface StateConsumer<T : State> {
    fun consume(state: T)
}