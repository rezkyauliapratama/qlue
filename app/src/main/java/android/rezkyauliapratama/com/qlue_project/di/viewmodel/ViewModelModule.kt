package android.rezkyauliapratama.com.qlue_project.di.viewmodel

import android.arch.lifecycle.ViewModel
import android.rezkyauliapratama.com.qlue_project.di.viewmodel.ViewModelKey
import android.rezkyauliapratama.com.qlue_project.ui.complaintpage.ComplaintViewModel
import android.rezkyauliapratama.com.qlue_project.ui.mainpage.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule{

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel : MainViewModel) : ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(ComplaintViewModel::class)
    abstract fun bindComplaintViewModel(complaintViewModel: ComplaintViewModel) : ViewModel


}