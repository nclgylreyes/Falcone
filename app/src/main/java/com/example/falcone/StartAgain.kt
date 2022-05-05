package com.example.falcone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_start_again.*

class StartAgain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_again)

        start_again.setOnClickListener(){
            val intent = Intent(this, StartAgain::class.java)
            startActivity(intent)
        }
    }
}