package com.example.studyapp.presentation.core.notification

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.annotation.DrawableRes
import androidx.core.app.NotificationCompat
import com.example.studyapp.R
import kotlin.random.Random

class NotificationService(
    private val context: Context
) {

    private val notificationManager = context.getSystemService(NotificationManager::class.java)

    fun showBasicNotification() {
        val notification = NotificationCompat.Builder(context, "study_notification_id")
            .setContentTitle("Study")
            .setContentText("Good bro")
            .setSmallIcon(R.drawable.logo)
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .setAutoCancel(true)
            .build()
        notificationManager.notify(
            Random.nextInt(),
            notification
        )
    }
    fun showExpandableNotification() {
        val image=context.bitmapFromResource(R.drawable.success_light)
        val notification = NotificationCompat.Builder(context, "study_notification_id")
            .setContentTitle("Study")
            .setContentText("Good bro")
            .setSmallIcon(R.drawable.logo)
            .setLargeIcon(image)
            .setStyle(
                NotificationCompat
                    .BigPictureStyle()
                    .bigPicture(image)
                    .bigLargeIcon(null as Bitmap?)
            )
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .setAutoCancel(true)
            .build()
        notificationManager.notify(
            Random.nextInt(),
            notification
        )
    }

    fun showGroupNotification() {
        val groupId="study_group"
        val summaryId=0
        val notification1 = NotificationCompat.Builder(context, "study_notification_id")
            .setContentTitle("Study")
            .setContentText("Good bro")
            .setSmallIcon(R.drawable.logo)
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .setAutoCancel(true)
            .setGroup(groupId)
            .build()
        val notification2 = NotificationCompat.Builder(context, "study_notification_id")
            .setContentTitle("Study")
            .setContentText("Good bro")
            .setSmallIcon(R.drawable.logo)
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .setAutoCancel(true)
            .setGroup(groupId)
            .build()
        val summaryNotification = NotificationCompat.Builder(context, "study_notification_id")
            .setContentTitle("Study")
            .setContentText("Good bro")
            .setSmallIcon(R.drawable.logo)
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .setStyle(
                NotificationCompat
                    .InboxStyle()
                    .setSummaryText("Summary")
                    .setBigContentTitle("Sumary reminder")
            )
            .setAutoCancel(true)
            .setGroup(groupId)
            .build()
        notificationManager.notify(
            Random.nextInt(),
            notification1
        )
        notificationManager.notify(
            Random.nextInt(),
            notification2
        )
        notificationManager.notify(
            Random.nextInt(),
            summaryNotification
        )

    }
    private fun Context.bitmapFromResource(
        @DrawableRes resiUd: Int
    ) = BitmapFactory
        .decodeResource(
            resources,
            resiUd
        )
}