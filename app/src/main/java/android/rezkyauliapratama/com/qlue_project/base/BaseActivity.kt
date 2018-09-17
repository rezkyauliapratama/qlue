package android.rezkyauliapratama.com.qlue_project.base

import android.Manifest
import android.arch.lifecycle.ViewModel
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.rezkyauliapratama.com.qlue_project.R
import android.rezkyauliapratama.com.qlue_project.di.activity.ActivityComponent
import android.rezkyauliapratama.com.qlue_project.di.activity.DaggerActivityComponent
import android.rezkyauliapratama.com.qlue_project.di.viewmodel.ViewModelFactory
import android.support.annotation.LayoutRes
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.AnkoLogger
import rezkyaulia.com.gojek_weather.di.activity.ActivityModule
import java.util.ArrayList
import javax.inject.Inject

abstract class BaseActivity<T : ViewDataBinding, V: ViewModel> :  AppCompatActivity(), AnkoLogger {

    val PERMISSION_REQUEST = 10000

    //inisialisasi viewModelFactory , apiRepository
    @Inject
    lateinit var viewModelFactory: ViewModelFactory


    //inisialisasi general type untuk viewDataBinding dan viewModel
    lateinit var viewDataBinding: T
    lateinit var viewModel : V


    //abstract method untuk di turunkan di dalam subclass
    /**
     * @return layout resource id
     */
    @LayoutRes
    abstract fun getLayoutId(): Int

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    abstract fun initViewModel(): V

    /**
     * @return binding variable resources
     */
    abstract fun initBindingVariable() : Int

    abstract fun inject()

    private var activityComponent: ActivityComponent?= null

    //function untuk init activity component
    fun initActivityComponent(): ActivityComponent? {
        if (activityComponent == null)
            activityComponent = DaggerActivityComponent.builder()
                    .applicationComponent(BaseApplication.component)
                    .activityModule(ActivityModule(this))
                    .build()

        return activityComponent
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        inject()

        super.onCreate(savedInstanceState)

        performDataBinding()


    }

    //function untuk mengeksekusi data binding viewModel kedalam layout
    private fun performDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView(this,getLayoutId())
        this.viewModel =  initViewModel()
        viewDataBinding.setVariable(initBindingVariable(), viewModel)
        viewDataBinding.executePendingBindings()

    }

    fun checkAppPermission() {

        val permissions = ArrayList<String>()
        val showMessage = (location(permissions)
                )


        if (permissions.size > 0) {
            val strings = arrayOfNulls<String>(permissions.size)

            if (showMessage) {
                val builder = AlertDialog.Builder(this)
                        .setMessage(R.string.permissionrequestmessage)
                        .setPositiveButton(R.string.gotit, DialogInterface.OnClickListener { dialog, which ->
                            ActivityCompat.requestPermissions(this@BaseActivity,
                                    permissions.toTypedArray(),
                                    PERMISSION_REQUEST)
                        })
                builder.create().show()
            } else
                ActivityCompat.requestPermissions(this,
                        permissions.toTypedArray(),
                        PERMISSION_REQUEST)
        }

    }
    private fun location(permissions: MutableList<String>): Boolean {
        if (ActivityCompat.checkSelfPermission(application, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(application, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION)
            permissions.add(Manifest.permission.ACCESS_FINE_LOCATION)

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.ACCESS_COARSE_LOCATION) && ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)) {

                return true

            }

        }
        return false
    }


}