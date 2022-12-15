package com.sartor.util.constants

import android.content.Context
import android.util.Patterns
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.widget.Placeholder
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.util.regex.Pattern

fun Toast.showToast(context: Context, msg : String){
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}

fun TextInputEditText.isEmpty() : Boolean? = this.text?.trim()?.isEmpty()

fun ShapeableImageView.placeImage(image: Any?, placeholder: Int = 0){
    Glide.with(this).load(image).placeholder(placeholder).into(this)
}



fun ImageView.placeImage(image: Any?, placeholder: Int = 0){
    Glide.with(this).load(image).placeholder(placeholder).into(this)
}

fun String.isEmail():Boolean{
    return Pattern.matches(Patterns.EMAIL_ADDRESS.pattern(),this)
}