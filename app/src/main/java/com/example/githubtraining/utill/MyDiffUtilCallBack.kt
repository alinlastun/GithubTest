/*
package com.example.githubtraining.utill

import android.support.v7.util.DiffUtil
import com.example.githubtraining.ui.repositories.RepositoriesAdapter

class MyDiffUtilCallBack(private var oldList: MutableList<RepositoriesAdapter.DataItem>, private var newList: MutableList<RepositoriesAdapter.DataItem>) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        return oldList[oldPosition].id == newList[newPosition].id
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(p0: Int, p1: Int): Boolean {
       return oldList  ==  newList
    }
}*/
