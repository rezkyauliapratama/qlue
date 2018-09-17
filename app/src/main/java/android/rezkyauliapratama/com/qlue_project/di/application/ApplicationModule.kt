package android.rezkyauliapratama.com.qlue_project.di.application

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import android.rezkyauliapratama.com.qlue_project.di.application.ApplicationContext
import com.squareup.picasso.Picasso

@Module
class ApplicationModule(private val application: Application){

    @Provides
    @ApplicationContext
    fun provideContext(): Context {
        return application
    }

    @Provides
    fun provideApplication(): Application {
        return application
    }

}