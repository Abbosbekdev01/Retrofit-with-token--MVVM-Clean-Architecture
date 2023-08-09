package uz.abbosbek.codialtaskmusobaqa.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import uz.abbosbek.codialtaskmusobaqa.data.localStorech.LocalStorage

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        LocalStorage.init(this)
    }
}