package com.sartor.ui.activity

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.sartor.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScanCreditCardActivity : AppCompatActivity() {

    lateinit var cancel: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_credit_card)

        cancel = findViewById(R.id.ivBack)

        cancel.setOnClickListener {
            finish()
        }

    }
}