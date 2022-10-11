package com.tkpd.devcamp2022.day3.image_gallery_image_upload.image_gallery.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tkpd.devcamp2022.R


class ImageGalleryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_gallery)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container_image_gallery, ImageGalleryFragment())
                .commit()
        }
    }
}