package rezkyaulia.com.gojek_weather.di.activity

import android.app.Activity
import android.content.Context
import dagger.Module
import dagger.Provides
import rezkyaulia.com.gojek_weather.di.activity.ActivityContext

@Module
class ActivityModule(val activity: Activity){

    @Provides
    @ActivityContext
    fun providesContext(): Context = activity

    @Provides
    fun provideActivity(): Activity = activity
}