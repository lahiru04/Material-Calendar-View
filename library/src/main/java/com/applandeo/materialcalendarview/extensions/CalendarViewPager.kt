package com.applandeo.materialcalendarview.extensions

import android.content.Context
import androidx.viewpager.widget.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * Created by Mateusz Kornakiewicz on 21.11.2017.
 */

class CalendarViewPager : ViewPager {

    private var mSwipeEnabled = true

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    //This method is needed to get wrap_content height for ViewPager
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var heightMeasureSpec = heightMeasureSpec
        var height = 0

        for (i in 0 until childCount) {
            val child = getChildAt(i)
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED))

            val h = child.measuredHeight

            if (h > height) {
                height = h
            }
        }

        if (height != 0) {
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY)
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    fun setSwipeEnabled(swipeEnabled: Boolean) {
        this.mSwipeEnabled = swipeEnabled
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return mSwipeEnabled && super.onTouchEvent(event)
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        return mSwipeEnabled && super.onInterceptTouchEvent(event)
    }

}
