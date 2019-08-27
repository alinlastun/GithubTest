package com.example.githubtraining.mvi

interface IntentConsumer<T : Intent, U : Change> {
    suspend fun consume(intent: T): U
}