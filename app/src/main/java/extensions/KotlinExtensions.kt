package com.softvision.hope.base.extensions

val <T> T.exhaustive: T
    get() = this

fun <E> Iterable<E>.replace(old: E, new: E) = map { if (it == old) new else it }