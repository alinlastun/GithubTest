package com.example.githubtraining.utill

import android.content.Context
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.githubtraining.ui.repositories.RepositoriesAdapter
import java.util.regex.Pattern

fun String.isValidEmail(): Boolean {
    val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
    val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
    val matcher = pattern.matcher(this)
    return matcher.matches()
}

fun RecyclerView.setRepoAdapter2(clickListener: RepositoriesAdapter.RepoItemListener) {
    layoutManager = NpaGridLayoutManager(context, 1)
    val dividerItemDecoration = DividerItemDecoration(this.context, 1)
    this.addItemDecoration(dividerItemDecoration)
    (layoutManager as GridLayoutManager).removeAllViews()
    adapter = RepositoriesAdapter(clickListener)
}

private class NpaGridLayoutManager(context: Context?, spanCount: Int) : GridLayoutManager(context, spanCount) {
    /**
     * Disable predictive animations. There is a bug in RecyclerView which causes views that
     * are being reloaded to pull invalid ViewHolders from the internal recycler stack if the
     * adapter size has decreased since the ViewHolder was recycled.
     */
    override fun supportsPredictiveItemAnimations(): Boolean {
        return false
    }
}
