package com.sartor.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import androidx.recyclerview.widget.RecyclerView

class SpacingForRecyclerChild(var halfSpace: Int) : ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View,
                                parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.top = halfSpace
        outRect.right = halfSpace
        outRect.left = halfSpace
        outRect.bottom = halfSpace
    }
}