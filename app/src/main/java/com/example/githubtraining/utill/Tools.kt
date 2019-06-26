package com.example.githubtraining.utill

import android.support.design.widget.Snackbar
import android.view.View


class Tools {

    fun showSnackBar(view: View){
        val snackbar = Snackbar
            .make(view, "You are in OFfLINE Mode!!!", Snackbar.LENGTH_LONG)
            .setAction("Reconnect") {
                val snackbar1 = Snackbar.make(view, "Message is restored!", Snackbar.LENGTH_SHORT)
                snackbar1.show()
            }

        snackbar.show()

    }
}