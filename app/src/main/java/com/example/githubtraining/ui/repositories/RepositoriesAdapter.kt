package com.example.githubtraining.ui.repositories
import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.githubtraining.BR
import com.example.githubtraining.R
import com.example.githubtraining.database.modelDB.InfoRepoModelDB
import com.example.githubtraining.databinding.RowRepoListBinding
import com.example.githubtraining.utilities.enums.ItemDisplayedType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
class RepositoriesAdapter(
    private val clickListener: RepoItemListener
) : ListAdapter<RepositoriesAdapter.DataItem, RecyclerView.ViewHolder>(RepoDiffUilCallBack()) {

    private var mData: MutableList<DataItem> = mutableListOf()
    private val adapterScope = CoroutineScope(Dispatchers.Default)

    fun addHeaderAndSubmitList(list: List<InfoRepoModelDB>?) {
        Log.d("afdewdf","setRepoAdapter2 list: ${list?.size}")
        adapterScope.launch {
            val data: MutableList<DataItem> = mutableListOf()

            val reposGroupedByYear = list?.groupBy { it.created_at?.substring(0, 4) }

            reposGroupedByYear?.entries?.forEach {

                it.key?.let { it1 -> DataItem.YearsHeader(it1) }?.let { it2 -> data.add(it2) }
                for (infoRepo in it.value) {
                    data.add(DataItem.MyRepoItem(infoRepo))
                }
            }
            withContext(Dispatchers.Main) {
                submitList(data)
              mData.clear()
              mData.addAll(data)
            }
        }
    }

    class RepoDiffUilCallBack : DiffUtil.ItemCallback<DataItem>() {
        override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (ItemDisplayedType.values()[viewType]) {
            ItemDisplayedType.TYPE_HEADER -> YearHeaderHolder.form(parent)
            ItemDisplayedType.TYPE_ITEM -> RepositoriesHolder.from(parent)

        }
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is RepositoriesHolder -> {
                holder.bind((mData[position] as DataItem.MyRepoItem).infoRepoModelDB, clickListener)
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

        fun bind(infoRepoModelDB: InfoRepoModelDB, clickListener: RepoItemListener) {
            binding.setVariable(BR.model, infoRepoModelDB)
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    class YearHeaderHolder(val view: View) : RecyclerView.ViewHolder(view) {

        var textView: TextView = view.findViewById<TextView>(R.id.mNameYears)!!

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
        return if (isPositionHeader(position)) {
            ItemDisplayedType.TYPE_HEADER.value
        } else {
            ItemDisplayedType.TYPE_ITEM.value
        }
    }
    sealed class DataItem {
        abstract val id: Long

        data class MyRepoItem(val infoRepoModelDB: InfoRepoModelDB) : DataItem() {
            override val id: Long = infoRepoModelDB.id.toLong()
        }

        data class YearsHeader(var yearName: String) : DataItem() {
            override val id: Long = Long.MIN_VALUE
        }
    }

    class RepoItemListener(val clickListener: (idInfoRepo: Int) -> Unit) {
        fun onClick(idRepo: Int) = clickListener(idRepo)
    }
}