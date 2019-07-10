package com.example.githubtraining.screen.repositories

import android.app.Activity
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.githubtraining.BR
import com.example.githubtraining.R
import com.example.githubtraining.database.modelDB.InfoRepoModelDB
import com.example.githubtraining.model.DisplayRepo
import com.example.githubtraining.model.RepoItem
import com.example.githubtraining.model.YearItem
import com.example.githubtraining.utill.enums.ItemDisplayedType


class RepositoriesAdapter(var activity: Activity, var mViewModel: RepositoriesViewModel) :
    RecyclerView.Adapter<RepositoriesAdapter.RepositoriesHolder>() {

    private var mData: MutableList<DisplayRepo> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoriesHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val binding: ViewDataBinding
        binding = if (viewType == ItemDisplayedType.TYPE_ITEM.value) {
            DataBindingUtil.inflate(layoutInflater, R.layout.row_repo_list, parent, false)
        } else {
            DataBindingUtil.inflate(layoutInflater, R.layout.row_header, parent, false)
        }
        return RepositoriesHolder(binding, activity, mViewModel)
    }

    override fun onBindViewHolder(holder: RepositoriesHolder, position: Int) {
        holder.bind(mData[position])
    }


    override fun getItemCount(): Int {
        return mData.size
    }

    class RepositoriesHolder(
        private val binding: ViewDataBinding,
        private var activity: Activity,
        private var mViewModel: RepositoriesViewModel
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(repoModel: DisplayRepo) {
            binding.setVariable(BR.model, repoModel)
            binding.setVariable(BR.viewModel, mViewModel)
            binding.setVariable(BR.repoActivity, activity)
            binding.executePendingBindings()
        }
    }

    fun addData(infoRepoList: MutableList<InfoRepoModelDB>) {
        mData = ArrayList()
        if (infoRepoList.size > 0) {
            val myListOfYears: MutableList<String> = arrayListOf()

            //sort list descending
            val mySortedList: MutableList<InfoRepoModelDB> =
                infoRepoList.sortedWith(compareByDescending<InfoRepoModelDB> { it.created_at }.thenByDescending { it.created_at }) as MutableList<InfoRepoModelDB>

            // extracted age from created_at and save into a String list
            for (list in mySortedList) {
                val parts = list.created_at!!.substring(0, 4)
                myListOfYears.add(parts)
            }
            //add in DisplayRepoList all YearItem elements that are bigger then element +1
            var lastYearSaved = ""
            for (i in 0..myListOfYears.size) {
                if (i < myListOfYears.size - 1) {
                    if (myListOfYears[i] > myListOfYears[i + 1]) {
                        val yearItem = YearItem(myListOfYears[i], myListOfYears[i])
                        mData.add(yearItem)
                        lastYearSaved = myListOfYears[i]
                    }
                }
            }
            // add last element from myListOfYears in DisplayRepoList if it is smaller then last element saved in DisplayRepoList
            if (myListOfYears[myListOfYears.size - 1] < lastYearSaved) {
                val yearItem = YearItem(myListOfYears[myListOfYears.size - 1], "")
                mData.add(yearItem)
            }

            // add in DisplayRepoList all InfoRepo from DB
            for (repoList in infoRepoList) {
                val repoItem = repoList.created_at?.let { RepoItem(repoList, it) }
                if (repoItem != null) {
                    mData.add(repoItem)
                }
            }
            mData.sortBy { it.myCreatedAt.toLowerCase() }

        }

        notifyDataSetChanged()
    }


    private fun isPositionHeader(position: Int): Boolean {
        return mData[position] is YearItem
    }

    override fun getItemViewType(position: Int): Int {
        if (isPositionHeader(position))
            return ItemDisplayedType.TYPE_HEADER.value
        return ItemDisplayedType.TYPE_ITEM.value
    }


}