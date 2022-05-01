package com.example.simple_voice_recorder_mimic.fragments

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout

abstract class MyViewPagerFragment(context: Context, attributeSet: AttributeSet) :
    ConstraintLayout(context, attributeSet) {

    abstract fun onResume();

    abstract fun onDestroy()
}