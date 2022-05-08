package com.example.simple_voice_recorder_mimic.fragments

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import com.example.simple_voice_recorder_mimic.helpers.*
import com.example.simple_voice_recorder_mimic.services.RecorderService
import com.simplemobiletools.commons.extensions.*
import kotlinx.android.synthetic.main.fragment_recorder.view.*
import org.greenrobot.eventbus.EventBus
import java.util.*

class RecorderFragment(context: Context, attributeSet: AttributeSet) : MyViewPagerFragment(context, attributeSet) {

    private var status = RECORDING_STOPPED
    private var pauseBlinkTimer = Timer()
    private var bus: EventBus? = null

    override fun onResume() {
        setupColors()
        if (!RecorderService.isRunning) status = RECORDING_STOPPED
        refreshView()
    }

    override fun onDestroy() {
        bus?.unregister(this)
        pauseBlinkTimer.cancel()

    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        setupColors()
        recorder_visualizer.recreate()
        bus = EventBus.getDefault()
        bus!!.register(this)

        updateRecordingDuration(0)
        toggle_recording_button.setOnClickListener {
            toggleRecording()
        }

        toggle_pause_button.setOnClickListener {
            Intent(context, RecorderService::class.java).apply {
                action = TOGGLE_PAUSE
                context.startService(this)
            }
        }

        Intent(context, RecorderService::class.java).apply {
            action = GET_RECORDER_INFO
            try {

            } catch (e: Exception) {

            }
        }
    }

    private fun setupColors() {
        val properPrimaryColor = context.getProperPrimaryColor()
        toggle_recording_button.apply {
            setImageDrawable(getToggleButtonIcon())
            background.applyColorFilter(properPrimaryColor);
        }

        toggle_pause_button.apply {
            setImageDrawable(resources.getColoredDrawableWithColor(
                com.simplemobiletools.commons.R.drawable.ic_pause_vector, properPrimaryColor.getContrastColor()))
                background.applyColorFilter(properPrimaryColor)
        }

        recorder_visualizer.chunkColor = properPrimaryColor
        recording_duration.setTextColor(context.getProperTextColor())
    }

    private fun updateRecordingDuration(duration: Int) {
        recording_duration.text = duration.getFormattedDuration()
    }

    private fun getToggleButtonIcon(): Drawable {
        val drawable = if (status == RECORDING_RUNNING || status == RECORDING_PAUSED)
            com.simplemobiletools.commons.R.drawable.ic_stop_vector else
                com.simplemobiletools.commons.R.drawable.ic_microphone_vector

        return resources.getColoredDrawable(drawable, context.getProperPrimaryColor().getContrastColor())
    }

    private fun toggleRecording() {

    }

    private fun startRecording() {

    }

    private fun stopRecording() {

    }


    private fun getPauseBlinkTask() = object : TimerTask() {
        override fun run() {
            TODO("Not yet implemented")
        }
    }

    private fun refreshView() {

    }

}