package com.example.sliide.presentation.ui

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpaceItemDecoration(
    private val verticalSpace: Int = 0,
    private val horizontalSpace: Int = 0,
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.right = horizontalSpace
        outRect.left = horizontalSpace
        if (parent.getChildLayoutPosition(view) == 0) {
            outRect.top = verticalSpace
        }
        outRect.bottom = verticalSpace
    }
}
