package com.example.githubtraining.ui.repoDetails

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.githubtraining.R
import com.example.githubtraining.db.model.InfoRepoModelDB

class CustomFragmentAdapter(val context: Context) :
    RecyclerView.Adapter<CustomFragmentAdapter.MyViewHolder>() {

    private var mData: MutableList<InfoRepoModelDB> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.fragment_repo_details, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.nTitleText.text=mData[position].name

    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var nTitleText: TextView = itemView.findViewById(R.id.nTitleText)
    }

    fun updateList(newList: List<InfoRepoModelDB>) {
        val diffResult = DiffUtil.calculateDiff(MyDiffCallback(newList, mData))
        diffResult.dispatchUpdatesTo(this)
        mData.clear()
        mData.addAll(newList)
    }
}