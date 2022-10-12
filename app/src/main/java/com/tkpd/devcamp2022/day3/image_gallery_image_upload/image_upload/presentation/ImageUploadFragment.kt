package com.tkpd.devcamp2022.day3.image_gallery_image_upload.image_upload.presentation

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tkpd.devcamp2022.databinding.FragmentImageUploadBinding
import com.tkpd.devcamp2022.day3.image_gallery_image_upload.image_upload.api.UploadImageRepository
import com.tkpd.devcamp2022.day3.image_gallery_image_upload.image_upload.data.ImageDataForUpload
import com.tkpd.devcamp2022.day3.image_gallery_image_upload.image_upload.data.UploadImageResultResponse

class ImageUploadFragment : Fragment() {

    private var binding: FragmentImageUploadBinding? = null
    private var imageGalleryResult: ActivityResultLauncher<Intent>? = null
    private var listTextViewUrl = listOf<TextView?>()
    private val viewModel by viewModels<ImageUploadViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return ImageUploadViewModel(UploadImageRepository()) as T
                }
            }
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        imageGalleryResult = registerImageGalleryResult()
        binding = FragmentImageUploadBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    private fun registerImageGalleryResult(): ActivityResultLauncher<Intent> {
        return registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            when (result.resultCode) {
                Activity.RESULT_OK -> {
                    clearTextView()
                    checkSelectedImage(result.data)
                }
            }
        }
    }

    private fun clearTextView() {
        listTextViewUrl.onEach {
            it?.text = ""
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
        listTextViewUrl = listOf(
            binding?.tvUploadedImage1,
            binding?.tvUploadedImage2,
            binding?.tvUploadedImage3
        )
        configButtonChooseImage()
        observeUploadImageResult()
    }

    private fun observeUploadImageResult() {
        viewModel.uploadImageResult.observe(viewLifecycleOwner) { uploadImageResult ->
            setTextImageUrl(uploadImageResult)
        }
    }

    private fun setTextImageUrl(uploadImageResult: List<UploadImageResultResponse?>?) {
        uploadImageResult?.filterNotNull()?.onEachIndexed { index, uploadImageResultResponse ->
            listTextViewUrl[index]!!.text = uploadImageResultResponse.image.url
        }
    }

    private fun configButtonChooseImage() {
        binding?.btnUploadImage?.setOnClickListener {
            openImageGallery()
        }
    }

    private fun checkSelectedImage(data: Intent?) {
        if (data?.clipData != null) {
            val totalSelectedImage = data.clipData?.itemCount ?: 0
            if (totalSelectedImage > 3) {
                showToasterMaximumThreeImages()
            } else {
                mutableListOf<Uri>().apply {
                    for (i in 0 until totalSelectedImage) {
                        data.clipData?.getItemAt(i)?.uri?.let {
                            add(it)
                        }
                    }
                }.let {
                    uploadImage(it.toList())
                }
            }
        } else {
            val uri = data?.data
            uri?.let {
                uploadImage(listOf(it))
            }
        }
    }

    private fun uploadImage(listUri: List<Uri>) {
        val listImageData = getListImageDataFromUri(listUri)
        viewModel.uploadImageMultipleRequest(listImageData)
//        For uploading image with single request
//        viewModel.uploadImageSingleRequest(listImageData)
    }

    private fun getListImageDataFromUri(listUri: List<Uri>): List<ImageDataForUpload> {
        return listUri.map { uri ->
            val getByteArrayData = getBytesArrayFromUri(uri)
            val fileName = getFileName(uri)
            ImageDataForUpload(
                getByteArrayData,
                fileName
            )
        }
    }

    private fun getBytesArrayFromUri(uri: Uri): ByteArray {
        //TODO: [Step 2] convert content uri to byte array using content resolver
//        return context?.contentResolver?.openInputStream(uri)?.readBytes() ?: ByteArray(0)
        return ByteArray(0)
    }

    private fun getFileName(uri: Uri): String {
        val cursor = context?.contentResolver?.query(
            uri,
            null,
            null,
            null,
            null
        )
        cursor?.moveToFirst()
        val cursorColumnIndex = cursor?.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        val fileName = cursor?.getString(cursorColumnIndex ?: 0).orEmpty()
        cursor?.close()
        return fileName
    }

    private fun showToasterMaximumThreeImages() {
        Toast.makeText(context, "Maximum 3 Image", Toast.LENGTH_LONG).show()
    }

}