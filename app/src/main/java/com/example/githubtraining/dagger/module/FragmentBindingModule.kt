package com.example.githubtraining.dagger.module

import com.example.githubtraining.ui.infoUser.InfoUserFragment
import com.example.githubtraining.ui.login.LoginFragment
import com.example.githubtraining.ui.repoDetails.FragmentViewPager
import com.example.githubtraining.ui.repositories.RepositoriesFragment
import com.example.githubtraining.ui.settings.SettingsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {

    @ContributesAndroidInjector
    abstract fun infoUserFragment(): InfoUserFragment

    @ContributesAndroidInjector
    abstract fun loginFragment(): LoginFragment

    @ContributesAndroidInjector
    abstract fun viewPagerFragment(): FragmentViewPager

    @ContributesAndroidInjector
    abstract fun repositoriesFragment(): RepositoriesFragment

    @ContributesAndroidInjector
    abstract fun settingsFragment(): SettingsFragment
}