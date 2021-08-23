package com.example.airqualityproject

import android.app.Application
import com.example.airqualityproject.di.AppModule.myModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

//@HiltAndroidApp
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@BaseApplication)
            modules(myModule)
        }
    }

}