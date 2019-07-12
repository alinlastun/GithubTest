package com.example.githubtraining.screen.repositories

import android.annotation.SuppressLint
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.githubtraining.BR
import com.example.githubtraining.R
import com.example.githubtraining.database.modelDB.InfoRepoModelDB
import com.example.githubtraining.databinding.RowRepoListBinding
import com.example.githubtraining.utill.enums.ItemDisplayedType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class RepositoriesAdapter2 : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mData: MutableList<DataItem> = mutableListOf()
    private val adapterScope = CoroutineScope(Dispatchers.Default)

    fun addHeaderAndSubmitList(list: MutableList<InfoRepoModelDB>?) {

        adapterScope.launch {
            val data: MutableList<DataItem> = mutableListOf()

            val reposGroupedByYear = list?.groupBy { it.created_at!!.substring(0, 4) }

            reposGroupedByYear?.entries?.forEach {
                data.add(DataItem.YearsHeader(it.key))
                for (infoRepo in it.value) {
                    data.add(DataItem.MyRepoItem(infoRepo))
                }
            }
            withContext(Dispatchers.Main) {
                mData = data

                notifyDataSetChanged()
                //submitList(items)

            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ItemDisplayedType.TYPE_HEADER.value -> YearHeaderHolder.form(parent)
            ItemDisplayedType.TYPE_ITEM.value -> RepositoriesHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is RepositoriesHolder -> {
                holder.bind((mData[position] as DataItem.MyRepoItem).infoRepoModelDB)
            }
            is YearHeaderHolder -> {
                holder.textView.text = (mData[position] as DataItem.YearsHeader).yearName
            }
        }
    }

    class RepositoriesHolder(private val binding: RowRepoListBinding) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): RecyclerView.ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RowRepoListBinding.inflate(layoutInflater, parent, false)
                return RepositoriesHolder(binding)
            }
        }

        fun bind(infoRepoModelDB: InfoRepoModelDB) {
            binding.setVariable(BR.model, infoRepoModelDB)
            binding.executePendingBindings()
        }
    }

    class YearHeaderHolder(val view: View) : RecyclerView.ViewHolder(view) {

        var textView = view.findViewById<TextView>(R.id.mNameYears)

        companion object {
            fun form(parent: ViewGroup): RecyclerView.ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.row_header, parent, false)
                return YearHeaderHolder(view)
            }
        }
    }


    private fun isPositionHeader(position: Int): Boolean {
        return mData[position] is DataItem.YearsHeader
    }

    override fun getItemViewType(position: Int): Int {
        if (isPositionHeader(position))
            return ItemDisplayedType.TYPE_HEADER.value
        return ItemDisplayedType.TYPE_ITEM.value
    }


    class RepoDiffCallback : DiffUtil.ItemCallback<DataItem>() {
        override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem == newItem
        }
    }


    sealed class DataItem {
        abstract val id: Long

        data class MyRepoItem(val infoRepoModelDB: InfoRepoModelDB) : DataItem() {
            override val id: Long = infoRepoModelDB.id!!.toLong()
        }

        data class YearsHeader(var yearName: String) : DataItem() {
            override val id: Long = Long.MIN_VALUE

        }
    }


}