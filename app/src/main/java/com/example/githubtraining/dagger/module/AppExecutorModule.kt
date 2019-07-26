package com.example.githubtraining.dagger.module
import android.os.Handler
import android.os.Looper
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppExecutorModule {

    @Provides
    @Singleton
    @Named("DiskExecutor")
    fun provideDiskIoExecutor(): Executor {
        return Executors.newSingleThreadExecutor()
    }

    @Provides
    @Singleton
    @Named("NetworkExecutor")
    fun provideNetworkExecutor(): Executor {
        return Executors.newFixedThreadPool(3)
    }

    class MainThreadExecutor : Executor {
        private val mainThreadHandler = Handler(Looper.getMainLooper())
        override fun execute(command: Runnable?) {
            mainThreadHandler.post(command)
        }
    }

    @Provides
    @Singleton
    @Named("MainThreadExecutor")
    fun provideMainThreadExecutor(): Executor {
        return MainThreadExecutor()
    }
}