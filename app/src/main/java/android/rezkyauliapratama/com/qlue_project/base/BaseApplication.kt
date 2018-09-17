package android.rezkyauliapratama.com.qlue_project.base

import android.app.Application
import android.rezkyauliapratama.com.qlue_project.di.application.ApplicationComponent
import android.rezkyauliapratama.com.qlue_project.di.application.ApplicationModule
import android.rezkyauliapratama.com.qlue_project.di.application.DaggerApplicationComponent
import android.rezkyauliapratama.com.qlue_project.di.application.NetworkModule
import android.support.multidex.MultiDexApplication

class BaseApplication : MultiDexApplication() {

    companion object {
        lateinit var component : ApplicationComponent
    }
    override fun onCreate() {
        super.onCreate()


        component = initDagger(this)
    }


    private fun initDagger(app: BaseApplication): ApplicationComponent =
            DaggerApplicationComponent.builder()
                    .applicationModule(ApplicationModule(app))
                    .networkModule(NetworkModule())
                    .build()

}
