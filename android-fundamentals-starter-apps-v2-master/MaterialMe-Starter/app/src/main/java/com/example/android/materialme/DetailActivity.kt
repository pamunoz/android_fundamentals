package com.example.android.materialme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        tv_title_detail.text = intent.getStringExtra("title")
        Glide.with(this).load(intent.getIntExtra("image_resource", 0)).into(img_sport_image_detail)
    }
}
