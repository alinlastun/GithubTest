package com.example.githubtraining.dagger.component
import android.app.Application
import com.example.githubtraining.MainApplication
import com.example.githubtraining.dagger.module.ActivityBindingModule
import com.example.githubtraining.dagger.module.AppDataBaseModule
import com.example.githubtraining.dagger.module.AppExecutorModule
import com.example.githubtraining.dagger.module.ApplicationContextModule
import com.example.githubtraining.dagger.module.ServiceUtilModule
import com.example.githubtraining.dagger.module.SharedPreferencesModule
import com.example.githubtraining.dagger.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelModule::class, ApplicationContextModule::class,
    SharedPreferencesModule::class,AppDataBaseModule::class, ServiceUtilModule::class,
    AppExecutorModule::class, AndroidSupportInjectionModule::class, ActivityBindingModule::class ])

interface AppComponent: AndroidInjector<MainApplication> {

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: Application):Builder
        fun build(): AppComponent
    }
}