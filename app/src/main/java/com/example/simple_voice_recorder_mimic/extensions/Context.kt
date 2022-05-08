package com.example.simple_voice_recorder_mimic.extensions

import android.content.Context
import com.example.simple_voice_recorder_mimic.helpers.Config

val Context.config: Config get() = Config.newInstance(applicationContext)