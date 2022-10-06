package com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.util

import android.Manifest.permission
import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.NetworkCapabilities.*
import android.net.NetworkInfo
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.Q
import android.os.SystemClock
import android.view.View
import androidx.annotation.RequiresPermission
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.tkpd.devcamp2022.R
import java.lang.reflect.InvocationTargetException

object WeatherUtil {
    fun setWeatherIcon(context: Context, imageView: AppCompatImageView, weatherCode: Int) {
        if (weatherCode / 100 == 2) {
            Glide.with(context).load(R.drawable.ic_storm_weather).into(imageView)
        } else if (weatherCode / 100 == 3) {
            Glide.with(context).load(R.drawable.ic_rainy_weather).into(imageView)
        } else if (weatherCode / 100 == 5) {
            Glide.with(context).load(R.drawable.ic_rainy_weather).into(imageView)
        } else if (weatherCode / 100 == 6) {
            Glide.with(context).load(R.drawable.ic_snow_weather).into(imageView)
        } else if (weatherCode / 100 == 7) {
            Glide.with(context).load(R.drawable.ic_unknown).into(imageView)
        } else if (weatherCode == 800) {
            Glide.with(context).load(R.drawable.ic_clear_day).into(imageView)
        } else if (weatherCode == 801) {
            Glide.with(context).load(R.drawable.ic_few_clouds).into(imageView)
        } else if (weatherCode == 803) {
            Glide.with(context).load(R.drawable.ic_broken_clouds).into(imageView)
        } else if (weatherCode / 100 == 8) {
            Glide.with(context).load(R.drawable.ic_cloudy_weather).into(imageView)
        }
    }

    fun Double.toPrettyString() =
        if(this - this.toLong() == 0.0)
            String.format("%d", this.toLong())
        else
            String.format("%s", this)

    fun View.clickWithDebounce(debounceTime: Long = 600L, action: () -> Unit) {
        this.setOnClickListener(object: View.OnClickListener {
            private var lastClickTime: Long = 0

            override fun onClick(p0: View?) {
                if (SystemClock.elapsedRealtime() - lastClickTime < debounceTime) return
                else action()

                lastClickTime = SystemClock.elapsedRealtime()
            }
        })
    }

    val Context.isNetworkConnected: Boolean
        get() {
            val manager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
            return if (SDK_INT >= Q)
                manager.getNetworkCapabilities(manager.activeNetwork)?.let {
                    it.hasTransport(TRANSPORT_WIFI) || it.hasTransport(TRANSPORT_CELLULAR) ||
                            it.hasTransport(TRANSPORT_BLUETOOTH) ||
                            it.hasTransport(TRANSPORT_ETHERNET) ||
                            it.hasTransport(TRANSPORT_VPN)
                } ?: false
            else
                @Suppress("DEPRECATION")
                manager.activeNetworkInfo?.isConnected == true
        }
}