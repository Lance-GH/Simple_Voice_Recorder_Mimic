package com.example.simple_voice_recorder_mimic.models

data class Recording(val id: Int, val title: String, val path: String, val timestamp: Int, val duration: Int, val size: Int)