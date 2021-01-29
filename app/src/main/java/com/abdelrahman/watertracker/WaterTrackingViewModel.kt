package com.abdelrahman.watertracker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WaterTrackingViewModel @Inject constructor(
    private val preferencesHelper: PreferencesHelper,
    private val savedStateHandle: SavedStateHandle
) : ViewModel(), WaterIntakePreferenceListener {

    private val _liveData = MutableLiveData<Int>()
    val intake: LiveData<Int> = _liveData

    init {
        preferencesHelper.subscribeToWaterIntakeChanges(this)
        _liveData.postValue(preferencesHelper.waterIntake())
    }

    override fun onCleared() {
        preferencesHelper.unsubscribeFromWaterIntakeChanges()
        super.onCleared()
    }

    fun incrementIntake() {
        preferencesHelper.incrementWaterIntake()
    }

    fun getIntake() = preferencesHelper.waterIntake()

    override fun onWaterIntakeChanged(waterIntake: Int) {
        _liveData.postValue(waterIntake)
    }
}