package com.task.artivatic

import android.app.Application
import com.task.artivatic.di.ApplicationComponent
import com.task.artivatic.di.DaggerApplicationComponent

class MyApp : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.factory().create(this)
    }
}