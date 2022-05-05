package com.example.falcone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_try_again.*

class TryAgain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_try_again)

        try_again.setOnClickListener(){
            val intent = Intent(this, TryAgain::class.java)
            startActivity(intent)
        }
    }
}