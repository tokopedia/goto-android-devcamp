package com.tkpd.devcamp2022.day2.workmanager.worker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.tkpd.devcamp2022.R

class DevCampWorker(val context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        return try {
            buildNotification(
                inputData.getString(DATA_TITLE) ?: "",
                inputData.getString(DATA_SUBTITLE) ?: ""
            )
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }

    private fun buildNotification(title: String, subtitle: String) {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val builder =
            NotificationCompat.Builder(applicationContext, DEV_CAMP_WORKMANAGER_CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher).setContentTitle(title).setContentText(subtitle)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                DEV_CAMP_WORKMANAGER_CHANNEL_ID,
                "DevCamp WorkManager Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )

            builder.setChannelId(DEV_CAMP_WORKMANAGER_CHANNEL_ID)
            notificationManager.createNotificationChannel(channel)
        }

        val notification = builder.build()

        notificationManager.notify(DEV_CAMP_WORKMANAGER_NOTIFICATION_ID, notification)
    }

    companion object {
        const val DEV_CAMP_WORKMANAGER_NOTIFICATION_ID = 1111
        const val DEV_CAMP_WORKMANAGER_CHANNEL_ID = "DEV_CAMP_WORKMANAGER_CHANNEL_ID"

        const val DATA_TITLE = "DATA_TITLE"
        const val DATA_SUBTITLE = "DATA_SUBTITLE"
    }
}