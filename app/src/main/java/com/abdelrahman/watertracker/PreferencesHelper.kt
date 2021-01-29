package com.abdelrahman.watertracker

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

open class PreferencesHelper constructor(context: Context) {
    private val preferences = context.getSharedPreferences("water_tracker_prefs", MODE_PRIVATE)
    private val KEY_WATER_INTAKE = "PreferencesHelper.KEY_WATER_INTAKE"
    private var waterIntakePreferenceListener: WaterIntakePreferenceListener? = null
    private val sharedPreferencesListener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
        if (key == KEY_WATER_INTAKE) {
            waterIntakePreferenceListener?.onWaterIntakeChanged(waterIntake())
        }
    }

    open fun subscribeToWaterIntakeChanges(listener: WaterIntakePreferenceListener) {
        this.waterIntakePreferenceListener = listener
        preferences.registerOnSharedPreferenceChangeListener(sharedPreferencesListener)
    }

    open fun unsubscribeFromWaterIntakeChanges() {
        this.waterIntakePreferenceListener = null
        preferences.unregisterOnSharedPreferenceChangeListener(sharedPreferencesListener)
    }

    open fun incrementWaterIntake() {
        preferences.edit().putInt(KEY_WATER_INTAKE, waterIntake() + 1).apply()
    }

    open fun waterIntake(): Int {
        return preferences.getInt(KEY_WATER_INTAKE, 0)
    }
}