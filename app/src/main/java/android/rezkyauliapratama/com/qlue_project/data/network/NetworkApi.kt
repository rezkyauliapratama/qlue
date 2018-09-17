package android.rezkyauliapratama.com.qlue_project.data.network

import android.rezkyauliapratama.com.qlue_project.data.model.Complaint
import io.reactivex.Single
import retrofit2.http.GET

/**
 * Created by Rezky Aulia Pratama on 11/9/18.
 */

interface NetworkApi{

        @GET(EndPoints.topReport)
        fun getAllComplaints(): Single<List<Complaint>>

}