package com.tkpd.devcamp2022.day3.image_gallery_image_upload.image_upload.api

import com.tkpd.devcamp2022.day3.image_gallery_image_upload.image_upload.data.ImageDataForUpload
import com.tkpd.devcamp2022.day3.image_gallery_image_upload.image_upload.data.UploadImageResultResponse
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UploadImageRepository {
    private val uploadImageApi: UploadImageApi
    private val apiKey = "6d207e02198a847aa98d0a2a901485a5"

    init {
        val client = OkHttpClient.Builder().build()
        uploadImageApi = Retrofit.Builder()
            .baseUrl("https://freeimage.host")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(UploadImageApi::class.java)
    }

    suspend fun uploadImage(
        imageData: ImageDataForUpload
    ): UploadImageResultResponse {
        //TODO: [Step 4] call api for upload single image
//        val imageFormData = createImageFormData(imageData)
//        val apiKeyRequestBody = apiKey.toRequestBody()
//        return uploadImageApi.uploadImage(apiKeyRequestBody, imageFormData)
        return UploadImageResultResponse()
    }

    suspend fun uploadMultipleImage(listImageData: List<ImageDataForUpload>): UploadImageResultResponse {
        //TODO: [Step 5] call api for upload multiple image
//        val listImageFormData = listImageData.map {imageData ->
//            createImageFormData(imageData)
//        }
//        val apiKeyRequestBody = apiKey.toRequestBody()
//        return uploadImageApi.uploadMultipleImage(apiKeyRequestBody, listImageFormData)
        return UploadImageResultResponse()
    }

    private fun createImageFormData(imageData: ImageDataForUpload): MultipartBody.Part {
        //TODO: [Step 3] convert image data to RequestBody and create MultipartBody.Part using createFormData function
//        val imageRequestBody = imageData.imageByteData.toRequestBody()
//        return MultipartBody.Part.createFormData("source", imageData.fileName, imageRequestBody)
        return MultipartBody.Part.createFormData("", "")
    }
}