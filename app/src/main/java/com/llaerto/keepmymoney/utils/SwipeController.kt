package com.llaerto.keepmymoney.utils

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.ACTION_STATE_SWIPE
import androidx.recyclerview.widget.ItemTouchHelper.RIGHT
import androidx.recyclerview.widget.RecyclerView

private const val SLIDE_DURATION = 200L

class SwipeController(var removeListener: (Int) -> Unit) : ItemTouchHelper.Callback() {
    var itemAnimator = MyAnimator()
    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        if (actionState == ACTION_STATE_SWIPE && dX > 0) {
            drawBackground(c, viewHolder.itemView, dX)
        }
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val pos = viewHolder.absoluteAdapterPosition
        ObjectAnimator.ofInt(viewHolder.itemView.height, 0).apply {
            duration = SLIDE_DURATION
            addUpdateListener { animation ->
                val value = animation.animatedValue as Int
                viewHolder.itemView.layoutParams.height = value
                viewHolder.itemView.requestLayout()
            }
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    removeListener(pos)
                    itemAnimator.isAnimationRunning = false
                }

                override fun onAnimationStart(animation: Animator?, isReverse: Boolean) {
                    itemAnimator.isAnimationRunning = true
                }
            })
            start()
        }
    }

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        return false
    }

    private fun drawBackground(c: Canvas, itemView: View, dX: Float) {
        if (dX > 0) {
            val rectF = RectF(itemView.left.toFloat(), itemView.top.toFloat(), itemView.right.toFloat(), itemView.bottom.toFloat())
            val p = Paint()
            p.color = Color.RED
            c.drawRect(rectF, p)
        }
    }

    fun attachToRecyclerView(rv: RecyclerView) {
        rv.itemAnimator = itemAnimator
        val itemTouchHelper = ItemTouchHelper(this)
        itemTouchHelper.attachToRecyclerView(rv)
    }

    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        return makeMovementFlags(0, RIGHT)
    }

    inner class MyAnimator : RecyclerView.ItemAnimator() {
        var isAnimationRunning = false

        override fun isRunning(): Boolean {
            return isAnimationRunning
        }

        override fun animatePersistence(viewHolder: RecyclerView.ViewHolder, preLayoutInfo: ItemHolderInfo, postLayoutInfo: ItemHolderInfo): Boolean {
            return false
        }

        override fun runPendingAnimations() {
        }

        override fun endAnimation(item: RecyclerView.ViewHolder) {
        }

        override fun animateDisappearance(
            viewHolder: RecyclerView.ViewHolder,
            preLayoutInfo: ItemHolderInfo,
            postLayoutInfo: ItemHolderInfo?
        ): Boolean {
            return false
        }

        override fun animateChange(
            oldHolder: RecyclerView.ViewHolder,
            newHolder: RecyclerView.ViewHolder,
            preLayoutInfo: ItemHolderInfo,
            postLayoutInfo: ItemHolderInfo
        ): Boolean {
            return false
        }

        override fun animateAppearance(viewHolder: RecyclerView.ViewHolder, preLayoutInfo: ItemHolderInfo?, postLayoutInfo: ItemHolderInfo): Boolean {
            ObjectAnimator.ofInt(0, viewHolder.itemView.height).apply {
                duration = SLIDE_DURATION
                addUpdateListener { animation ->
                    val value = animation.animatedValue as Int
                    viewHolder.itemView.layoutParams.height = value
                    viewHolder.itemView.requestLayout()
                }
                addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        isAnimationRunning = false
                    }

                    override fun onAnimationStart(animation: Animator?, isReverse: Boolean) {
                        isAnimationRunning = true
                    }
                })
                start()
            }
            return false
        }

        override fun endAnimations() {
        }
    }
}

