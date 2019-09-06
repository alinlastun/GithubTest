package com.example.githubtraining.repository

import com.example.githubtraining.db.dao.DaoInfoRepo
import com.example.githubtraining.db.dao.DaoInfoUser
import com.example.githubtraining.db.dao.DaoStuff
import com.example.githubtraining.domain.model.RepositoryInformation
import com.example.githubtraining.model.Member
import com.example.githubtraining.repository.apimapper.RepoInfoMapper
import com.example.githubtraining.repository.fetcher.RepoFetcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class InfoRepoRepository @Inject constructor(
    private val repoFetcher: RepoFetcher,
    private val daoInfoRepo: DaoInfoRepo,
    private val daoInfoUser: DaoInfoUser,
    private val daoStuff: DaoStuff ) {

    private val repoListCollaborator: MutableList<com.example.githubtraining.db.model.RepositoryInformation> = mutableListOf()
    private val repoListOwner: MutableList<com.example.githubtraining.db.model.RepositoryInformation> = mutableListOf()
    /**
     * Get a Flow with the DB data but also trigger a fetch from the network.
     * All concurrent operations will be executed in the [scope] CoroutineScope.
     */
    fun getUserInfo(scope: CoroutineScope, listener: (isSuccess: Boolean, errorMsg: String) -> Unit): Flow<List<RepositoryInformation>> {
        scope.launch {
            repoFetcher.fetchRepoInfo(listener)

        }
        return getRepoList().map{ repoList ->
            repoList.map {   RepoInfoMapper.map(it) }

        }
    }

    private fun getRepoList(): Flow<List<com.example.githubtraining.db.model.RepositoryInformation>> {
        return getFinalList(getRepoListSorted(daoStuff.getSortNumber()))
    }

    private fun getFinalList(repoList: Flow<List<com.example.githubtraining.db.model.RepositoryInformation>>): Flow<List<com.example.githubtraining.db.model.RepositoryInformation>> {
        getCollaboratorList(repoList)
        var members = Member()
        daoStuff.getMembers().forEach { members = it }
        return if (members.collaborator && !members.owner) {
            repoListCollaborator
        } else if (members.owner && !members.collaborator) {
            repoListOwner
        } else if (members.owner && members.collaborator) {
            repoList
        } else {
            repoList
        }
    }

    private fun getCollaboratorList(repoList: Flow<List<com.example.githubtraining.db.model.RepositoryInformation>>) {
        repoListCollaborator.clear()
        repoListOwner.clear()
        repoList.map { list ->
            list.map {
                it.fullName?.let { fullName ->
                    if (!fullName.contains(daoInfoUser.getUserLogged())) {
                        repoListCollaborator.add(it)
                    } else if (fullName.contains(daoInfoUser.getUserLogged())) {
                        repoListOwner.add(it)
                    } else{

                    }
            }

        }
    }

     fun getRepoListSorted(resultSort: Int): Flow<List<com.example.githubtraining.db.model.RepositoryInformation>> {
        return when (resultSort) {
            0 -> daoInfoRepo.getRepoSortedByCreated()
            1 -> daoInfoRepo.getRepoSortedByUpdated()
            2 -> daoInfoRepo.getRepoSortedByPushed()
            3 -> daoInfoRepo.getRepoSortedByFullName()
            else -> throw Exception("Unknown InfoRepoModelDB")
        }
    }
}