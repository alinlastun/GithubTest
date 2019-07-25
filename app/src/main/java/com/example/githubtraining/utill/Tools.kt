package com.example.githubtraining.utill

import android.content.res.Resources
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.util.Log
import androidx.core.text.HtmlCompat
import com.example.githubtraining.R
import com.example.githubtraining.database.modelDB.InfoRepoModelDB
import java.text.SimpleDateFormat
import java.util.*


class Tools {

    fun formatInfoRepo(infoRepo: InfoRepoModelDB, resources: Resources): Spanned {
        Log.d("Asdfasdf",infoRepo.name)
        val sb = StringBuilder()
        sb.apply {

            append("<br>")
            append(resources.getString(R.string.name_repo))
            append("\t${infoRepo.name}<br>")
            append("<br>")
            append(resources.getString(R.string.full_name_repo))
            append("\t${infoRepo.full_name}<br>")
            append("<br>")
            append(resources.getString(R.string.created_repo))
            append("\t${infoRepo.created_at?.let { formatMyDate(it) }}<br>")
            append("<br>")
            append(resources.getString(R.string.updated_repo))
            append("\t${infoRepo.updated_at?.let { formatMyDate(it) }}<br>")
            append("<br>")
            append(resources.getString(R.string.pushed_repo))
            append("\t${infoRepo.pushed_at?.let { formatMyDate(it) }}<br>")
            append("<br>")
            append(resources.getString(R.string.is_private_repo))
            append("\t${infoRepo.private}<br>")


        }
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
        } else {
            HtmlCompat.fromHtml(sb.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
        }
    }

     fun formatMyDate(myDate: String): String {
        val outputFormat = SimpleDateFormat("dd-MMMM-yyyy", Locale.US)
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
        val date = inputFormat.parse(myDate)
        return outputFormat.format(date)


    }
}