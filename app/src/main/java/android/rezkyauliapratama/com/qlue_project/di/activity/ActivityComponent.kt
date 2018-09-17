package android.rezkyauliapratama.com.qlue_project.di.activity

import android.rezkyauliapratama.com.qlue_project.ui.mainpage.MainActivity
import android.rezkyauliapratama.com.qlue_project.di.application.ApplicationComponent
import android.rezkyauliapratama.com.qlue_project.ui.complaintpage.ComplaintActivity
import dagger.Component
import rezkyaulia.com.gojek_weather.di.activity.ActivityModule
import rezkyaulia.com.gojek_weather.di.activity.PerActivity

@PerActivity
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent{
    fun inject(mainActivity: MainActivity)
    fun inject(mainActivity: ComplaintActivity)

}