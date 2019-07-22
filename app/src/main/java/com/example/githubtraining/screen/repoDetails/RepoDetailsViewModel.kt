package com.example.githubtraining.screen.repoDetails

import android.app.Application
import android.arch.lifecycle.*
import android.content.Context
import android.databinding.ObservableField
import android.support.annotation.MainThread
import android.text.Spanned
import com.example.githubtraining.appComponent
import com.example.githubtraining.database.modelDB.InfoRepoModelDB
import com.example.githubtraining.utill.Tools
import javax.inject.Inject

class RepoDetailsViewModel @Inject constructor(private val repository:RepoDetailsRepository,application: Application): ViewModel() {



    private val repoId = MutableLiveData<Int>()

    private val repo: LiveData<InfoRepoModelDB> =
        Transformations.switchMap(repoId) { repoId ->
            repository.getRepoById(repoId)
        }

    val resultInfoRepo: LiveData<Spanned> = Transformations.map(repo){
        Tools().formatInfoRepo(it,application.resources)
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