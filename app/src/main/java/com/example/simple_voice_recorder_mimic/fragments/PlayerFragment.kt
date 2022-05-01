package com.example.simple_voice_recorder_mimic.fragments

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.PowerManager
import android.util.AttributeSet
import com.example.simple_voice_recorder_mimic.interfaces.RefreshRecordingsListener
import com.example.simple_voice_recorder_mimic.models.Recording
import kotlinx.android.synthetic.main.fragment_player.view.*
import java.util.*

class PlayerFragment(context: Context, attributeSet: AttributeSet) : MyViewPagerFragment(context, attributeSet),
    RefreshRecordingsListener {

    private var player: MediaPlayer? = null;
    private var progressTimer = Timer()

    override fun onResume() {
        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        player?.stop()
        player?.release()
        player = null


        progressTimer.cancel()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
    }

    private fun setupViews() {

    }

    private fun initMediaPlayer() {
        player = MediaPlayer().apply {
            setWakeMode(context, PowerManager.PARTIAL_WAKE_LOCK)
            setAudioStreamType(AudioManager.STREAM_MUSIC)

            setOnCompletionListener {
                progressTimer.cancel()
                player_progressbar.progress = player_progressbar.max
                player_progress_current.text = player_progress_max.text
            }
        }
    }

    override fun refreshRecordings() {
        TODO("Not yet implemented")
    }

    override fun playRecording(recording: Recording, playOnPrepared: Boolean) {
        TODO("Not yet implemented")
    }
}