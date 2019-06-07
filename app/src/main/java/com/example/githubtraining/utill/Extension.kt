package com.example.githubtraining.utill

import android.app.Activity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.githubtraining.screen.repositories.RepositoriesAdapter
import java.util.regex.Pattern

fun String.isValidEmail(): Boolean {
    val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
    val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
    val matcher = pattern.matcher(this)
    return matcher.matches()
}

fun RecyclerView.setRepoAdapter(activity: Activity){
    adapter = RepositoriesAdapter(activity)
    layoutManager = GridLayoutManager(context, 1)
}
