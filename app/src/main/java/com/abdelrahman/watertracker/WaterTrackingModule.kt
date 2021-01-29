package com.abdelrahman.watertracker

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped

@InstallIn(ActivityRetainedComponent::class)
@Module
class WaterTrackingModule {

    @ActivityRetainedScoped
    @Provides
    fun providesPreferencesHelper(
        @ApplicationContext context: Context
    ) = PreferencesHelper(context)
}