package com.example.githubtraining.ui.repoDetails
import android.app.Application
import android.text.Spanned
import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.githubtraining.database.modelDB.InfoRepoModelDB
import com.example.githubtraining.utill.Tools
import javax.inject.Inject

class RepoDetailsViewModel @Inject constructor(
    private val repository: RepoDetailsRepository,
    application: Application
) : ViewModel() {
    private val repoId = MutableLiveData<Int>()

     val repo: LiveData<InfoRepoModelDB> =
        Transformations.switchMap(repoId) { repoId ->
            repository.getRepoById(repoId)
        }

    val resultInfoRepo: LiveData<Spanned> = Transformations.map(repo) {
        Tools().formatInfoRepo(it, application.resources)
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