package com.example.githubtraining.screen.repoDetails

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.databinding.ObservableField
import android.support.annotation.MainThread
import com.example.githubtraining.appComponent
import com.example.githubtraining.database.modelDB.InfoRepoModelDB
import javax.inject.Inject

class RepoDetailsViewModel @Inject constructor(private val repository:RepoDetailsRepository): ViewModel() {

    private val repoId = MutableLiveData<Int>()

    private val repo: LiveData<InfoRepoModelDB> =
        Transformations.switchMap(repoId) { repoId ->
            repository.getRepoById(repoId)
        }

    val mNameRepository: LiveData<String> =
        Transformations.map(repo) { repo ->
            repo.name
        }
    val mDescriptionRepository: LiveData<String> =
        Transformations.map(repo) { repo ->
            repo.description
        }
    val mStatusRepository: LiveData<String> =
        Transformations.map(repo) { repo ->
            if (repo.private == true) "private" else "public"
        }

    @MainThread
    fun init(repoId: Int) {
        this.repoId.value = repoId
    }


}