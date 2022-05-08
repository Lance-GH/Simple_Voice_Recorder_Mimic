package com.example.simple_voice_recorder_mimic.activities

import android.os.Bundle
import android.view.Menu
import com.example.simple_voice_recorder_mimic.R
import com.example.simple_voice_recorder_mimic.extensions.config
import com.example.simple_voice_recorder_mimic.helpers.*
import com.simplemobiletools.commons.dialogs.ChangeDateTimeFormatDialog
import com.simplemobiletools.commons.dialogs.FilePickerDialog
import com.simplemobiletools.commons.dialogs.RadioGroupDialog
import com.simplemobiletools.commons.extensions.*
import com.simplemobiletools.commons.helpers.isQPlus
import com.simplemobiletools.commons.models.RadioItem
import kotlinx.android.synthetic.main.activity_settings.*
import java.util.*

class SettingsActivity : SimpleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()

        setupPurchaseThankYou()
        setupCustomizeColors()
        setupUseEnglish()
        setupChangeDateTimeFormat()
        setupHideNotification()
        setupSaveRecordingsFolder()
        setupExtension()
        setupBitrate()
        setupRecordAfterLaunch()
        updateTextColors(settings_scrollview)

        arrayOf(settings_color_customization_label, settings_general_settings_label).forEach {
            it.setTextColor(getProperPrimaryColor())
        }

        arrayOf(settings_color_customization_holder, settings_general_settings_holder).forEach {
            it.background.applyColorFilter(getProperPrimaryColor().getContrastColor())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        updateMenuItemColors(menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun setupPurchaseThankYou() {
        settings_purchase_thank_you.beGoneIf(isOrWasThankYouInstalled())

        // make sure corner at ripple fit the stroke rounded corners
        if (settings_purchase_thank_you_holder.isGone()) {
            settings_use_english_holder.background = resources.getDrawable(com.simplemobiletools.commons.R.drawable.ripple_top_corners, theme)
        }

        settings_purchase_thank_you_holder.setOnClickListener {
            launchPurchaseThankYouIntent()
        }
    }

    private fun setupCustomizeColors() {
        settings_customize_colors_label.text = getCustomizeColorsString()
        settings_customize_colors_holder.setOnClickListener {
            handleCustomizeColorsClick()
        }
    }

    private fun setupUseEnglish() {
        settings_use_english_holder.beVisibleIf(config.wasUseEnglishToggled || Locale.getDefault().language != "en")
        settings_use_english.isChecked = config.useEnglish

        if (settings_use_english_holder.isGone() && settings_purchase_thank_you_holder.isGone()) {
            settings_change_date_time_format_holder.background = resources.getDrawable(com.simplemobiletools.commons.R.drawable.ripple_top_corners, theme)
        }

        settings_use_english_holder.setOnClickListener {
            settings_use_english.toggle()
            config.useEnglish = settings_use_english.isChecked
            System.exit(0)
        }
    }

    private fun setupChangeDateTimeFormat() {
        settings_change_date_time_format_holder.setOnClickListener {
            ChangeDateTimeFormatDialog(this) { }
        }
    }


    private fun setupHideNotification() {
        settings_hide_notification.isChecked = config.hideNotification
        settings_hide_notification_holder.setOnClickListener {
            settings_hide_notification.toggle()
            config.hideNotification = settings_hide_notification.isChecked
        }
    }

    private fun setupSaveRecordingsFolder() {
        settings_save_recordings.text = humanizePath(config.saveRecordingsFolder)
        settings_save_recordings_holder.setOnClickListener {
            FilePickerDialog(this, config.saveRecordingsFolder, false, showFAB = true) {
                val path = it
                handleSAFDialog(path) { grantedSAF ->
                    if (!grantedSAF) {
                        return@handleSAFDialog
                    }

                    handleSAFDialogSdk30(path) { grantedSAF30 ->
                        if (!grantedSAF30) {
                            return@handleSAFDialogSdk30
                        }

                        config.saveRecordingsFolder = path
                        settings_save_recordings.text = humanizePath(config.saveRecordingsFolder)
                    }
                }
            }
        }
    }

    private fun setupExtension() {
        settings_extension.text = config.getExtensionText()
        settings_extension_holder.setOnClickListener {
            val items = arrayListOf(
                RadioItem(EXTENSION_M4A, "m4a"),
                RadioItem(EXTENSION_MP3, "mp3")
            )

            if (isQPlus()) {
                items.add(RadioItem(EXTENSION_OGG, "ogg"))
            }

            RadioGroupDialog(this@SettingsActivity, items, config.extension) {
                config.extension = it as Int
                settings_extension.text = config.getExtensionText()
            }
        }
    }

    private fun setupBitrate() {
        settings_bitrate.text = getBitrateText(config.bitrate)
        settings_bitrate_holder.setOnClickListener {
            val items = BITRATES.map { RadioItem(it, getBitrateText(it)) } as ArrayList

            RadioGroupDialog(this@SettingsActivity, items, config.bitrate) {
                config.bitrate = it as Int
                settings_bitrate.text = getBitrateText(config.bitrate)
            }
        }
    }

    private fun getBitrateText(value: Int): String = getString(R.string.bitrate_value).format(value / 1000)

    private fun setupRecordAfterLaunch() {
        settings_record_after_launch.isChecked = config.recordAfterLaunch
        settings_record_after_launch_holder.setOnClickListener {
            settings_record_after_launch.toggle()
            config.recordAfterLaunch = settings_record_after_launch.isChecked
        }
    }
}