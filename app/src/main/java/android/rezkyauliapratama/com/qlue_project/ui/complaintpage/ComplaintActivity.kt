package android.rezkyauliapratama.com.qlue_project.ui.complaintpage

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.rezkyauliapratama.com.qlue_project.BR
import android.rezkyauliapratama.com.qlue_project.R
import android.rezkyauliapratama.com.qlue_project.base.BaseActivity
import android.rezkyauliapratama.com.qlue_project.databinding.ActivityComplaintBinding
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_complaint.*

class ComplaintActivity : BaseActivity<ActivityComplaintBinding, ComplaintViewModel>(){

    lateinit var adapter: ComplaintsRvAdapter

    override fun getLayoutId(): Int {
        return R.layout.activity_complaint
    }

    override fun initViewModel(): ComplaintViewModel {
        return ViewModelProviders.of(this, viewModelFactory).get(ComplaintViewModel::class.java)

    }

    override fun initBindingVariable(): Int {
        return BR.viewModel
    }

    override fun inject() {
        initActivityComponent()?.inject(this)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initRecyclerView()

    }

    private fun initRecyclerView() {
        adapter = ComplaintsRvAdapter(this,viewModel)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

}