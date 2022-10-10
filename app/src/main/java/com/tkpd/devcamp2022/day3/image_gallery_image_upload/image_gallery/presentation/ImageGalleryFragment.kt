package com.tkpd.devcamp2022.day3.image_gallery_image_upload.image_gallery.presentation

import android.app.Activity
import android.content.ClipData
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.tkpd.devcamp2022.databinding.FragmentImageGalleryBinding

class ImageGalleryFragment : Fragment() {

    private var binding: FragmentImageGalleryBinding? = null
    private var imageGalleryResult: ActivityResultLauncher<Intent>? = null
    private var listImageView = listOf<ImageView?>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        //TODO: [Step 1] register activity result below using registerImageGalleryResult()
        imageGalleryResult = registerImageGalleryResult()
        binding = FragmentImageGalleryBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    private fun registerImageGalleryResult(): ActivityResultLauncher<Intent> {
        return registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            //TODO: [Step 3] check result code data and call checkSelectedImage function to validate clipData value
            when (result.resultCode) {
                Activity.RESULT_OK -> {
                    clearImageView()
                    checkSelectedImage(result.data)
                }
            }
        }
    }

    private fun openImageGallery() {
        //TODO: [Step 2] define intent for image gallery below and launch it
        val intentAction = Intent.ACTION_GET_CONTENT
        val mimeType = "image/*"
        val imagePickerIntent = Intent(intentAction).apply {
            type = mimeType
        }
        imagePickerIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        imageGalleryResult?.launch(imagePickerIntent)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        listImageView = listOf(binding?.ivImage1, binding?.ivImage2, binding?.ivImage3)
        configButtonChooseImage()
    }

    private fun configButtonChooseImage() {
        binding?.btnImageGallery?.setOnClickListener {
            openImageGallery()
        }
    }

    private fun clearImageView() {
        listImageView.onEach { it?.setImageResource(0) }
    }

    private fun checkSelectedImage(data: Intent?) {
        //TODO: [Step 4] check whether clipData is null or not and also load the selected image to ImageView
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