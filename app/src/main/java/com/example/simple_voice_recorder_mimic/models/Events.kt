package com.example.simple_voice_recorder_mimic.models

class Events {
    class RecordingDuration internal constructor(val duration: Int)
    class RecordingStatus internal constructor(val status: Int)
    class RecordingAmplitde internal constructor(val amplitude: Int)
    class RecordingCompleted internal constructor()
}