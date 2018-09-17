package android.rezkyauliapratama.com.qlue_project.ui.complaintpage

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.rezkyauliapratama.com.qlue_project.R
import android.rezkyauliapratama.com.qlue_project.BR
import android.rezkyauliapratama.com.qlue_project.data.model.Complaint
import android.rezkyauliapratama.com.qlue_project.databinding.ListItemComplaintBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error

class ComplaintsRvAdapter  (private val lifecycleOwner: LifecycleOwner, private val complaintViewModel: ComplaintViewModel) : RecyclerView.Adapter<ComplaintsRvAdapter.ViewHolder>(), AnkoLogger{

    val mItems  = mutableListOf<Complaint>()

    init {
        complaintViewModel.itemsLD.observe(lifecycleOwner, Observer {
            mItems.clear()
            if (it != null)
                mItems.addAll(it)

            notifyDataSetChanged()
            error { "items : "+Gson().toJson(it) }
        })
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_complaint, parent, false)
        return ViewHolder(view)    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val complaint : Complaint = mItems[position]
        holder.bindItem(complaint)
    }


    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)  {

        private var binding:ListItemComplaintBinding = ListItemComplaintBinding.bind(itemView)

        fun bindItem(complaint: Complaint) {
            binding.imageView.url = complaint.image_url
            binding.setVariable(BR.item,complaint)
            binding.executePendingBindings()
        }
    }
}