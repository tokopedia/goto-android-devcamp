package com.tkpd.devcamp2022.day3.image_gallery_image_upload.image_upload.api

import com.tkpd.devcamp2022.day3.image_gallery_image_upload.image_upload.data.UploadImageResultResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.*

interface UploadImageApi {
    //TODO: [Step 1] define api call for both single and multiple upload image
//    @Multipart
//    @POST("api/1/upload")
//    suspend fun uploadImage(
//        @Part("key") apiKey: RequestBody,
//        @Part imageFormData: MultipartBody.Part,
//    ): UploadImageResultResponse

//    @Multipart
//    @POST("api/1/upload")
//    suspend fun uploadMultipleImage(
//        @Part("key") apiKey: RequestBody,
//        @Part multipleImageFormData: List<MultipartBody.Part>,
//    ): UploadImageResultResponse
}