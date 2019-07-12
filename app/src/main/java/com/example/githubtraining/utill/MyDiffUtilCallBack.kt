package com.example.githubtraining.utill

import android.support.v7.util.DiffUtil
import com.example.githubtraining.screen.repositories.RepositoriesAdapter2

class MyDiffUtilCallBack(private var oldList: MutableList<RepositoriesAdapter2.DataItem>,private var newList: MutableList<RepositoriesAdapter2.DataItem>) : DiffUtil.Callback() {
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
}