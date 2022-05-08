package com.example.simple_voice_recorder_mimic.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.simple_voice_recorder_mimic.R
import com.simplemobiletools.commons.extensions.appLaunched

class MainActivity : SimpleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appLaunched(BuildConfig.APPLICATION_ID)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    private fun tryInitVoiceRecorder() {

    }

    private fun setupViewPager() {

    }

    private fun getPagerAdapter() {

    }

    private fun setupTabColors() {

    }

    private fun getInactiveTabIndex() {

    }

    private fun launchSettings() {

    }

    private fun launchAbout() {

    }
}