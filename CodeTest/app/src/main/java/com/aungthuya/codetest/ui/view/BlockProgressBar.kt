package com.aungthuya.codetest.ui.view

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ProgressBar


class BlockProgressBar private constructor(private val context: Context) {

    private var style: Int = 0
    private val params = FrameLayout.LayoutParams(100, 100)
    private var parent: ViewGroup? = null
    private var defaultVisibility = View.GONE
    private var color: Int = 0x66000000

    fun style(id: Int): BlockProgressBar {
        style = id
        return this
    }

    fun centerIn(parent: ViewGroup): BlockProgressBar {
        params.gravity = Gravity.CENTER
        this.parent = parent
        return this
    }

    fun build(): View {
        val frame = FrameLayout(context)
        val progress = ProgressBar(context, null, style)

        frame.setBackgroundColor(color)
        frame.visibility = defaultVisibility
        frame.addView(progress, params)

        parent?.addView(frame)
        return frame
    }

    companion object {
        fun with(context: Context) = BlockProgressBar(context)
    }
}