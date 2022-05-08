package com.example.simple_voice_recorder_mimic.services

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.MediaRecorder
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.simple_voice_recorder_mimic.activities.SplashActivity
import com.example.simple_voice_recorder_mimic.helpers.*
import com.simplemobiletools.commons.extensions.getLaunchIntent
import com.simplemobiletools.commons.extensions.toast
import java.util.*

class RecorderService : Service() {
    companion object {
        var isRunning = false
    }

    private val AMPLITUDE_UPDATE_MS = 75L

    private var currFilePath = ""

    private var duration = 0
    private var status = RECORDING_STOPPED
    private var durationTimer = Timer()
    private var amplitudeTimer = Timer()

    private var recorder: MediaRecorder? = null

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)

    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun startRecording() {

    }

    private fun stopRecording() {

    }

    private fun broadcastRecorderInfo() {

    }

    private fun startAmplitudeUpdates() {

    }

    private fun togglePause() {

    }

    private fun addFileInNewMediaStore() {

    }

    private fun addFileInLegacyMediaStore() {

    }

    private fun recordingSavedSuccessfully() {
        toast("Recording saved successfully")
    }

    private fun getDurationUpdateTask() = object : TimerTask() {
        override fun run() {
            TODO("Not yet implemented")
        }
    }

    private fun getAmplitudeUpdateTask() = object : TimerTask() {
        override fun run() {
            TODO("Not yet implemented")
        }
    }

    private fun showNotification(): Notification {

        val builder = NotificationCompat.Builder(this)

        return builder.build()
    }

    private fun getOpenAppIntent(): PendingIntent {
        val intent = getLaunchIntent() ?: Intent(this, SplashActivity::class.java)
        return PendingIntent.getActivity(this, RECORDER_RUNNING_NOTIF_ID, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
    }

    private fun broadcastDuration() {

    }

    private fun broadcastStatus() {

    }
}