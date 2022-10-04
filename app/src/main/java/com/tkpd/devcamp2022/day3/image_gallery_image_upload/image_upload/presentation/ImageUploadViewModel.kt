package com.tkpd.devcamp2022.day3.image_gallery_image_upload.image_upload.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tkpd.devcamp2022.day3.image_gallery_image_upload.image_upload.api.UploadImageRepository
import com.tkpd.devcamp2022.day3.image_gallery_image_upload.image_upload.data.ImageDataForUpload
import com.tkpd.devcamp2022.day3.image_gallery_image_upload.image_upload.data.UploadImageResultResponse
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

class ImageUploadViewModel(
    private val uploadImageRepository: UploadImageRepository
) : ViewModel() {

    val uploadImageResult: LiveData<List<UploadImageResultResponse?>>
        get() = _uploadImageResult
    private val _uploadImageResult = MutableLiveData<List<UploadImageResultResponse?>>()

    fun uploadImageMultipleRequest(listImageData: List<ImageDataForUpload>) {
        viewModelScope.launch {
            val response = listImageData.map { imageData ->
                async {
                    try {
                        uploadImageRepository.uploadImage(imageData)
                    } catch (e: Exception) {
                        null
                    }
                }
            }.awaitAll()
            _uploadImageResult.postValue(response)
        }
    }

    fun uploadImageSingleRequest(listImageData: List<ImageDataForUpload>) {
        viewModelScope.launch {
            val response = uploadImageRepository.uploadMultipleImage(listImageData)
            _uploadImageResult.postValue(listOf(response))
        }
    }
}