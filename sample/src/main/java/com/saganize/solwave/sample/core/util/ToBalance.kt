package com.saganize.solwave.sample.core.util

fun Double.toBalance(): String {
    return "${this/1000000000} SOL"
}