package com.example.simple_voice_recorder_mimic.fragments

import android.content.Context
import android.graphics.drawable.Drawable
import android.media.AudioManager
import android.media.MediaMetadataRetriever
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.PowerManager
import android.provider.MediaStore
import android.provider.MediaStore.Audio.Media
import android.util.AttributeSet
import androidx.annotation.RequiresApi
import com.example.simple_voice_recorder_mimic.R
import com.example.simple_voice_recorder_mimic.adapters.RecordingsAdapter
import com.example.simple_voice_recorder_mimic.helpers.getAudioFileContentUri
import com.example.simple_voice_recorder_mimic.interfaces.RefreshRecordingsListener
import com.example.simple_voice_recorder_mimic.models.Events
import com.example.simple_voice_recorder_mimic.models.Recording
import com.simplemobiletools.commons.extensions.*
import com.simplemobiletools.commons.helpers.isRPlus
import kotlinx.android.synthetic.main.fragment_player.view.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.lang.Exception
import java.util.*
import kotlin.math.roundToLong


class PlayerFragment(context: Context, attributeSet: AttributeSet) : MyViewPagerFragment(context, attributeSet),
    RefreshRecordingsListener {

    private val FAST_FORWARD_SKIP_MS = 10000

    private var player: MediaPlayer? = null
    private var progressTimer = Timer()
    private var playedRecordingIDs = Stack<Int>()
    private var bus: EventBus? = null
    private var prevSavePath = ""
    private var playOnPreparation = true

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

    override fun refreshRecordings() {
        TODO("Not yet implemented")
    }

    private fun setupAdapter() {

    }

    private fun getRecordings(): ArrayList<Recording> {
        val recordings = ArrayList<Recording>()
        return when {
            isRPlus() -> {
                recordings
            }
            else -> {
                recordings
            }
        }
    }

    private fun getMediaStoreRecordings(): ArrayList<Recording> {
        val recordings = ArrayList<Recording>()

        val uri = Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
        val projection = arrayOf(
            Media._ID,
            Media.DISPLAY_NAME,
            Media.DATE_ADDED,
            Media.DURATION,
            Media.SIZE
        )

        val selection = "${Media.OWNER_PACKAGE_NAME} = ?"
        val selectionArgs = arrayOf(context.packageName)
        val sortOrder = "${Media.DATE_ADDED} DESC"

        context.queryCursor(uri, projection, selection, selectionArgs, sortOrder, true) { cursor ->
            val id = cursor.getIntValue(Media._ID)
            val title = cursor.getStringValue(Media.DISPLAY_NAME)
            val timestamp = cursor.getIntValue(Media.DATE_ADDED)
            var duration = cursor.getLongValue(Media.DURATION) / 1000
            var size = cursor.getIntValue(Media.SIZE)

            if (duration == 0L) {
                duration = getDurationFromUri(getAudioFileContentUri(id.toLong()))
            }

            if (size == 0) {
                size = getSizeFromUri(id.toLong())
            }

            val recording = Recording(id, title, "", timestamp, duration.toInt(), size)
            recordings.add(recording)
        }

        return recordings
    }

    private fun getLegacyRecordings(): ArrayList<Recording> {
        val recordings = ArrayList<Recording>()

        return recordings
    }

    private fun getSAFRecordings(): ArrayList<Recording> {
        val recordings = ArrayList<Recording>()

        return recordings
    }

    private fun getDurationFromUri(uri: Uri): Long {
        return try {
            val retriever = MediaMetadataRetriever()
            retriever.setDataSource(context, uri)
            val time = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)!!
            (time.toLong() / 1000.toDouble()).roundToLong()
        } catch (e: Exception) {
            0L
        }
    }

    private fun getSizeFromUri(id: Long): Int {
        val recordingUri = getAudioFileContentUri(id)
        return try {
            context.contentResolver.openInputStream(recordingUri)?.available() ?: 0
        } catch (e: Exception) {
            0
        }
    }


    private fun initMediaPlayer() {
        player = MediaPlayer().apply {
            setWakeMode(context, PowerManager.PARTIAL_WAKE_LOCK)
            setAudioStreamType(AudioManager.STREAM_MUSIC)

            setOnCompletionListener {
                progressTimer.cancel()
                player_progressbar.progress = player_progressbar.max
                player_progress_current.text = player_progress_max.text
                play_pause_btn.setImageDrawable(getToggleButtonIcon(false))
            }
        }
    }


    override fun playRecording(recording: Recording, playOnPrepared: Boolean) {
        TODO("Not yet implemented")
    }

    private fun setupProgressTimer() {

    }

    private fun getProgressUpdateTask() = object : TimerTask() {
        override fun run() {
            TODO("Not yet implemented")
        }
    }

    private fun updateCurrentProgress(seconds: Int) {

    }

    private fun resetProgress() {

    }

    private fun togglePlayPause() {

    }

    private fun pausePlayback() {

    }

    private fun resumePlayback() {

    }

    private fun getToggleButtonIcon(isPlaying: Boolean): Drawable {
        val drawable = if (isPlaying) com.simplemobiletools.commons.R.drawable.ic_pause_vector else
            com.simplemobiletools.commons.R.drawable.ic_place_vector
        return resources.getColoredDrawableWithColor(drawable, context.getProperPrimaryColor().getContrastColor())
    }

    private fun skip(forward: Boolean) {

    }

    private fun getIsPlaying() {

    }

    private fun getRecordingsAdapter() = recordings_list.adapter as? RecordingsAdapter

    private fun storePrevPath() {

    }

    private fun setupColors() {

    }

    fun finishActMode() = getRecordingsAdapter()?.finishActMode()

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun recordingCompleted(event: Events.RecordingCompleted) {

    }
}