package com.example.soyrobado

import android.view.View

fun View.disableView() {
    this.isEnabled = false
    this.alpha = 0.5f
}

fun View.enableView() {
    this.isEnabled = true
    this.alpha = 1f
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}
