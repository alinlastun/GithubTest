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

class RepositoriesAdapter(var activity:Activity) : RecyclerView.Adapter<RepositoriesAdapter.RepositoriesHolder>() {

    private var mData: MutableList<InfoRepoModelDB> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoriesHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.row_repo_list, parent, false)
        return RepositoriesHolder(binding,activity)
    }


    override fun onBindViewHolder(holder: RepositoriesHolder, position: Int) {
        holder.bind(mData[position])
    }


    override fun getItemCount(): Int {
        return mData.size
    }

    class RepositoriesHolder(private val binding: ViewDataBinding, private var activity:Activity) : RecyclerView.ViewHolder(binding.root) {
        fun bind(repoModel: InfoRepoModelDB) {
            binding.setVariable(BR.model, repoModel)
            binding.setVariable(BR.repoActivity, activity)
            binding.executePendingBindings()
        }

    }

    fun addData(listPaymentInfo: MutableList<InfoRepoModelDB>) {
        mData = ArrayList()
        mData.addAll(listPaymentInfo)
        notifyDataSetChanged()
    }

}