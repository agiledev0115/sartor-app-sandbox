package com.sartor.ui.fragments.home_screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sartor.R

class AddressBookActivity : AppCompatActivity() {

    //todo pending until api is updated

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address_book)
    }


    override fun onBackPressed() {
        super.onBackPressed()
        this.overridePendingTransition(android.R.anim.fade_out, android.R.anim.fade_in)
    }
}