package android.rezkyauliapratama.com.qlue_project.ui.complaintpage

import android.arch.lifecycle.MutableLiveData
import android.rezkyauliapratama.com.qlue_project.base.BaseViewModel
import android.rezkyauliapratama.com.qlue_project.data.model.Complaint
import android.rezkyauliapratama.com.qlue_project.data.network.NetworkApi
import com.google.gson.Gson
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.schedulers.IoScheduler
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.error
import javax.inject.Inject

class ComplaintViewModel @Inject constructor(networkApi: NetworkApi) : BaseViewModel(){

    val itemsLD : MutableLiveData<List<Complaint>> = MutableLiveData()
    val networkStatusLD : MutableLiveData<List<NetworkStatus>> = MutableLiveData()
    val uiStatusLD : MutableLiveData<List<UiStatus>> = MutableLiveData()

    init {

        networkApi
                .getAllComplaints()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    itemsLD.value = response
                }, { throwable ->
                    error { "error : "+ Gson().toJson(throwable) }
                })

    }


    enum class NetworkStatus{
        SUCCESS,
        FAILURE
    }

    enum class UiStatus{
        SHOW_LOADER,
        HIDE_LOADER
    }



}