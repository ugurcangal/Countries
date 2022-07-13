package com.ugurcangal.kotlincountries.util

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ugurcangal.kotlincountries.R

//Extension Function


fun ImageView.downloadFromUrl(url: String?, progressDrawable: CircularProgressDrawable) {
    val options = RequestOptions()
        .placeholder(progressDrawable) // yüklenirken ne görüneceğini belirlemek için.
        .error(R.mipmap.ic_launcher_round) // hata olursa ne görünecek...


    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)

}

fun placeholderProgressBar(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }
}