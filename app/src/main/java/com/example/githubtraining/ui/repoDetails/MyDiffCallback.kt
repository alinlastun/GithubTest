package com.example.githubtraining.ui.repoDetails

import androidx.recyclerview.widget.DiffUtil
import com.example.githubtraining.db.model.InfoRepoModelDB

class MyDiffCallback (private val newList: List<InfoRepoModelDB>, private val oldList: List<InfoRepoModelDB>) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }
}