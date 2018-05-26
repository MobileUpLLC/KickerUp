package ru.mobileup.kickerup

import android.app.Application
import com.facebook.stetho.Stetho
import org.koin.android.ext.android.startAndroidContext
import timber.log.Timber
import uk.co.chrisjenx.calligraphy.CalligraphyConfig


class TheApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        initStetho()
        initLogging()
        initKoin()
        //initCalligraphy()
    }

    private fun initStetho() {
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }

    private fun initLogging() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun initKoin() {
        startAndroidContext(this, allModules())
    }

    /**
     * Sets default font to the whole application via Calligraphy library
     */
    private fun initCalligraphy() {
        CalligraphyConfig.initDefault(
                CalligraphyConfig.Builder()
                    .setDefaultFontPath(getString(R.string.font_main_regular))
                    .setFontAttrId(R.attr.fontPath)
                    .build()
        )
    }
}