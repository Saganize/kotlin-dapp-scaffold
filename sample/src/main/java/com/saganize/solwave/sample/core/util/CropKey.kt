package com.saganize.solwave.sample.core.util

fun String.cropKey() = if (this.isBlank()) this else this.take(4) + "...." + this.takeLast(4)