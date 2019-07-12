package com.example.githubtraining.utill

import android.app.Activity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.githubtraining.screen.repositories.RepositoriesAdapter
import com.example.githubtraining.screen.repositories.RepositoriesViewModel
import java.util.regex.Pattern
import android.support.v7.widget.DividerItemDecoration
import com.example.githubtraining.screen.repositories.RepositoriesAdapter2


fun String.isValidEmail(): Boolean {
    val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
    val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
    val matcher = pattern.matcher(this)
    return matcher.matches()
}

fun RecyclerView.setRepoAdapter(activity: Activity,mViewModel: RepositoriesViewModel){
    layoutManager = GridLayoutManager(context, 1)
    val dividerItemDecoration = DividerItemDecoration(this.context,1)
    this.addItemDecoration(dividerItemDecoration)
    adapter = RepositoriesAdapter(activity,mViewModel)

}

fun RecyclerView.setRepoAdapter2(){
    layoutManager = GridLayoutManager(context, 1)
    val dividerItemDecoration = DividerItemDecoration(this.context,1)
    this.addItemDecoration(dividerItemDecoration)
    adapter = RepositoriesAdapter2()

}
