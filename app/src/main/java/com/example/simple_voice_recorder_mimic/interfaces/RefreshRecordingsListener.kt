package com.example.simple_voice_recorder_mimic.interfaces

import com.example.simple_voice_recorder_mimic.models.Recording

interface RefreshRecordingsListener {

    fun refreshRecordings()

    fun playRecording(recording: Recording, playOnPrepared: Boolean)
}