package android.rezkyauliapratama.com.qlue_project.customview

import android.app.Application
import android.content.Context
import android.rezkyauliapratama.com.qlue_project.R
import android.util.AttributeSet
import android.widget.ImageView
import com.squareup.picasso.Picasso
import javax.inject.Inject


class UrlImageView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : ImageView(context, attrs, defStyle) {

    var defaultImageId = R.drawable.ic_picture
    var url: String? = null
        set(url) {
            field = url

            Picasso.get().
                    load(url)?.
                    placeholder(defaultImageId)?.
                    resize(1024,0)?.
                    onlyScaleDown()?.
                    centerInside()?.
                    into(this)

        }


}