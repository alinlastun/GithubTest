package extensions

import timber.log.Timber

fun CD(message: String, vararg args: Any) {
    Timber.d("$message (${Thread.currentThread().name})", args)
}