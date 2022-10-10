package com.tkpd.devcamp2022.day4.map_push_notification.push_notification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.tkpd.devcamp2022.R
import com.tkpd.devcamp2022.databinding.ActivityPushNotificationBinding

class PushNotificationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPushNotificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPushNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                return@OnCompleteListener
            }
            // Get new FCM registration token
            val token = task.result
            binding.tvFcmToken.text = getString(R.string.text_token, token)
        })
    }
}