package com.abdul.secondapp

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseInstanceIDService : FirebaseMessagingService() {
    override fun onNewToken(s: String) {
        super.onNewToken(s)
        Log.e("NEW_TOKEN", s)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
    }
}