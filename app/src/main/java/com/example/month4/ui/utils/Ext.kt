package com.example.month4.ui.utils

import android.media.Image
import android.widget.ImageView
import com.squareup.picasso.Picasso
import java.net.URI

fun ImageView.loadImage(url: String){
    Picasso.get().load(url).into(this)
}