package com.example.githubtraining.utill

import android.app.Activity
import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.githubtraining.screen.repositories.RepositoriesAdapter
import com.example.githubtraining.screen.repositories.RepositoriesViewModel
import java.util.regex.Pattern
import android.support.v7.widget.DividerItemDecoration
import android.util.AttributeSet
import com.example.githubtraining.screen.repositories.RepositoriesAdapter2


fun String.isValidEmail(): Boolean {
    val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
    val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
    val matcher = pattern.matcher(this)
    return matcher.matches()
}

fun RecyclerView.setRepoAdapter(activity: Activity,mViewModel: RepositoriesViewModel){
    layoutManager = GridLayoutManager(context, 1)
    val dividerItemDecoration = DividerItemDecoration(this.context,1)
    this.addItemDecoration(dividerItemDecoration)
    adapter = RepositoriesAdapter(activity,mViewModel)

}

fun RecyclerView.setRepoAdapter2(){
    layoutManager = NpaGridLayoutManager(context, 1)
    val dividerItemDecoration = DividerItemDecoration(this.context,1)
    this.addItemDecoration(dividerItemDecoration)
    (layoutManager as GridLayoutManager).removeAllViews()
    adapter = RepositoriesAdapter2()

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
