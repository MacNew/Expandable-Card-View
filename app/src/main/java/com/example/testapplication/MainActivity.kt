package com.example.testapplication

import android.animation.ValueAnimator
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewTreeObserver
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var minHeight: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        card_view.getViewTreeObserver().addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                card_view.getViewTreeObserver().removeOnPreDrawListener(this)
               // minHeight = card_view.height
                minHeight = 0
                val layoutParams = card_view.getLayoutParams()
                layoutParams.height = minHeight
                card_view.setLayoutParams(layoutParams)
                return true
            }
        })

        image_butoon.setOnClickListener {
            minHeight = 630
            toggleCardVienHeight(0)
        }
    }

    private fun toggleCardVienHeight(height: Int) {
        if (card_view.getHeight() == minHeight) {
            // expand
            expandView(height)
        } else
            collapseView()

    }

    fun collapseView() {

        val anim = ValueAnimator.ofInt(
            card_view.getMeasuredHeightAndState(),
            minHeight
        )
        anim.addUpdateListener { valueAnimator ->
            val `val` = valueAnimator.animatedValue as Int
            val layoutParams = card_view.getLayoutParams()
            layoutParams.height = `val`
            card_view.setLayoutParams(layoutParams)
        }
        anim.start()
        image_butoon.setImageResource(R.drawable.ic_arrow_upward_black_24dp)
    }

    fun expandView(height: Int) {
        val anim = ValueAnimator.ofInt(
            card_view.getMeasuredHeightAndState(),
            height
        )
        anim.addUpdateListener { valueAnimator ->
            val `val` = valueAnimator.animatedValue as Int
            val layoutParams = card_view.getLayoutParams()
            layoutParams.height = `val`
            card_view.setLayoutParams(layoutParams)
        }
        anim.start()
        image_butoon.setImageResource(R.drawable.ic_arrow_downward_black_24dp)
    }
}
