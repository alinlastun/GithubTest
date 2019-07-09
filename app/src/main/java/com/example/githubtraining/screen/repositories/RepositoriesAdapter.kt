package com.example.githubtraining.screen.repositories

import android.app.Activity
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.util.Log.d
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.githubtraining.BR
import com.example.githubtraining.R
import com.example.githubtraining.database.modelDB.InfoRepoModelDB

class RepositoriesAdapter(var activity:Activity,var mViewModel:RepositoriesViewModel) : RecyclerView.Adapter<RepositoriesAdapter.RepositoriesHolder>() {

    private  val ROW_HEADER = 2
    private  val ROW_LIST = 1
    private var mData: MutableList<InfoRepoModelDB> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoriesHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        var binding: ViewDataBinding
        d("qaafdsfasd","${viewType}")
        binding = if(viewType ==ROW_LIST){
            d("ASDfsd","list")
            DataBindingUtil.inflate(layoutInflater, R.layout.row_repo_list, parent, false)
        }else{
            d("ASDfsd","header")
            DataBindingUtil.inflate(layoutInflater, R.layout.row_header, parent, false)
        }
       // val binding: ViewDataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.row_repo_list, parent, false)
        return RepositoriesHolder(binding,activity,mViewModel)
    }

    override fun onBindViewHolder(holder: RepositoriesHolder, position: Int) {
        holder.bind(mData[position])
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    class RepositoriesHolder(private val binding: ViewDataBinding, private var activity:Activity,private var mViewModel:RepositoriesViewModel) : RecyclerView.ViewHolder(binding.root) {
        fun bind(repoModel: InfoRepoModelDB) {
            binding.setVariable(BR.model, repoModel)
            binding.setVariable(BR.viewModel, mViewModel)
            binding.setVariable(BR.repoActivity, activity)
            binding.executePendingBindings()
        }
    }

    fun addData(listRepo: MutableList<InfoRepoModelDB>) {
        mData = ArrayList()
        mData.addAll(listRepo)
        for(list in listRepo){
            d("a4f32c","${list.name} ${list.type}")
        }

        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        d("Asdfasd","${mData[position].type}")
        return if(mData[position].type==1){
            ROW_LIST
        }else{
            ROW_HEADER
        }
    }
}