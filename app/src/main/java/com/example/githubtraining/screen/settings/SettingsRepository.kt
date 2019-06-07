package com.example.githubtraining.screen.settings

import android.content.Context
import android.util.Log
import com.example.githubtraining.utill.repository.RepositoryUserDB
import com.example.githubtraining.utill.repository.RepositoryWs
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class SettingsRepository(var mViewModel:SettingsViewModel,mContext:Context) {

    var mRepositoryWS = RepositoryWs()
    var mRepositorDB = RepositoryUserDB(mContext)


}