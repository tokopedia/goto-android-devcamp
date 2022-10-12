package com.tkpd.devcamp2022.day3.image_gallery_image_upload.image_upload.data

import com.google.gson.annotations.SerializedName

data class UploadImageResultResponse(
    @SerializedName("status_code")
    val statusCode: String = "",
    @SerializedName("image")
    val image: Image = Image()
) {
    data class Image(
        @SerializedName("url")
        val url: String = ""
    )
}