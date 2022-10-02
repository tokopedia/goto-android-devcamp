package com.tkpd.devcamp2022.day2.workmanager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.*
import com.tkpd.devcamp2022.databinding.ActivityWorkManagerBinding
import com.tkpd.devcamp2022.day2.workmanager.worker.DevCampWorker
import java.util.concurrent.TimeUnit

class WorkManagerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWorkManagerBinding

    private var workManager: WorkManager? = null

    private var onceRequest: OneTimeWorkRequest? = null
    private var periodicRequest: PeriodicWorkRequest? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorkManagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // get WorkManager instance
        workManager = WorkManager.getInstance(this)

        binding.btnWorkmanagerOnce.setOnClickListener {
            // build Data to be sent to Worker
            val data: Data = Data.Builder().apply {
                putString(DevCampWorker.DATA_TITLE, "DevCamp 2022")
                putString(
                    DevCampWorker.DATA_SUBTITLE,
                    "Ini adalah Work Manager yang berjalan hanya sekali!"
                )
            }.build()

            // Create Work Request
            onceRequest = OneTimeWorkRequestBuilder<DevCampWorker>()
                .addTag(ONCE_TAG)
                .setInputData(data)
                .build()

            // Enqueue the Work Request
            onceRequest?.let {
                workManager?.enqueueUniqueWork(
                    ONCE_NAME,
                    ExistingWorkPolicy.KEEP,
                    it
                )
            }
        }

        binding.btnWorkmanagerCancelOnce.setOnClickListener {
            onceRequest?.let {
                // Cancel by Work Request Id
                // workManager?.cancelWorkById(it.id)
            }

            // Cancel by tag
            // workManager?.cancelAllWorkByTag(ONCE_TAG)

            // Cancel by name
            workManager?.cancelUniqueWork(ONCE_NAME)
        }

        binding.btnWorkmanagerPeriodic.setOnClickListener {
            // build Data to be sent to Worker
            val data: Data = Data.Builder().apply {
                putString(DevCampWorker.DATA_TITLE, "DevCamp 2022")
                putString(
                    DevCampWorker.DATA_SUBTITLE,
                    "Ini adalah Work Manager yang berjalan hanya setiap 15 menit!"
                )
            }.build()

            // Create Periodic Work Request
            periodicRequest =
                PeriodicWorkRequestBuilder<DevCampWorker>(15, TimeUnit.MINUTES)
                    .addTag(PERIODIC_TAG)
                    .setInputData(data)
                    .build()

            // Enqueue the Work Request
            periodicRequest?.let {
                workManager?.enqueue(it)
            }
        }
    }

    companion object {
        private const val ONCE_TAG = "onceTag"
        private const val PERIODIC_TAG = "periodicTag"

        private const val ONCE_NAME = "onceName"

    }
}