package android.rezkyauliapratama.com.qlue_project.di.application

import android.rezkyauliapratama.com.qlue_project.customview.UrlImageView
import android.rezkyauliapratama.com.qlue_project.di.viewmodel.ViewModelFactory
import android.rezkyauliapratama.com.qlue_project.di.viewmodel.ViewModelModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class ,NetworkModule::class])
interface ApplicationComponent {


    fun getViewModelFactory() : ViewModelFactory
}