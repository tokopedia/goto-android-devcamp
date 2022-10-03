package com.tkpd.devcamp2022.day3.image_gallery_image_upload.image_gallery.presentation

import android.app.Activity
import android.content.ClipData
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.tkpd.devcamp2022.databinding.FragmentImageGalleryBinding

class ImageGalleryFragment : Fragment() {

    private var binding: FragmentImageGalleryBinding? = null
    private var imageGalleryResult: ActivityResultLauncher<Intent>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        imageGalleryResult = registerImageGalleryResult()
        binding = FragmentImageGalleryBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    private fun registerImageGalleryResult(): ActivityResultLauncher<Intent> {
        return registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            when (result.resultCode) {
                Activity.RESULT_OK -> {
                    clearImageView()
                    checkSelectedImage(result.data)
                }
            }
        }
    }

    private fun openImageGallery() {
        val intentAction = Intent.ACTION_GET_CONTENT
        val mimeType = "image/*"
        val imagePickerIntent = Intent(intentAction).apply {
            type = mimeType
        }
        imagePickerIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        imageGalleryResult?.launch(imagePickerIntent)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        configButtonChooseImage()
    }

    private fun configButtonChooseImage() {
        binding?.btnImageGallery?.setOnClickListener {
            openImageGallery()
        }
    }

    private fun clearImageView() {
        binding?.apply {
            ivImage1.setImageResource(0)
            ivImage2.setImageResource(0)
            ivImage3.setImageResource(0)
        }
    }

    private fun checkSelectedImage(data: Intent?) {
        if (data?.clipData != null) {
            val totalSelectedImage = data.clipData?.itemCount ?: 0
            if (totalSelectedImage > 3) {
                showToasterMaximumThreeImages()
            } else {
                setMultipleImage(data.clipData, totalSelectedImage)
            }
        } else {
            val uri = data?.data
            setSingleImage(uri)
        }
    }

    private fun setMultipleImage(clipData: ClipData?, totalSelectedImage: Int) {
        val listImageView = listOf(binding?.ivImage1, binding?.ivImage2, binding?.ivImage3)
        for (i in 0 until totalSelectedImage) {
            listImageView[i]?.setImageURI(clipData?.getItemAt(i)?.uri)
        }
    }

    private fun setSingleImage(uri: Uri?) {
        binding?.ivImage1?.setImageURI(uri)
    }

    private fun showToasterMaximumThreeImages() {
        Toast.makeText(context, "Maximum 3 Image", Toast.LENGTH_LONG).show()
    }


}