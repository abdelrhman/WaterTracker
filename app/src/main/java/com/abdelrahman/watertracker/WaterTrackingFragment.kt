package com.abdelrahman.watertracker

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WaterTrackingFragment : Fragment(R.layout.fragment_water_tracker) {

    private lateinit var waterCountText: WaterCountTextView
    private lateinit var trackWaterButton: Button
    private val viewModel: WaterTrackingViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        waterCountText = view.findViewById(R.id.waterCountText)
        trackWaterButton = view.findViewById(R.id.trackWaterButton)
        trackWaterButton.setOnClickListener {
            viewModel.incrementIntake()
        }
        viewModel.intake.observe(owner = viewLifecycleOwner, {
            waterCountText.text = it.toString()
        })
    }
}