package com.sedat.kotlinapp.fragment.workmanager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.sedat.kotlinapp.R

class MyWorkManager(val context: Context, workerParams: WorkerParameters) : Worker(context,
    workerParams) {
    override fun doWork(): Result {
        val getData = inputData
        val value = getData.getBoolean("isSend", false)
       if(value)
            sendNotification()
        return Result.success()
    }

    private fun sendNotification(){

        val mBuilder = NotificationCompat.Builder(context, "1230")
                .setSmallIcon(R.drawable.notifications_24)
                .setContentTitle("Workmanager")
                .setContentText("Workmanager çalıştı.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(false)

        val notificationManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel("1230", "Channel", NotificationManager.IMPORTANCE_DEFAULT).apply {
                description = "Deneme bildirimi."
            }
            notificationManager.createNotificationChannel(channel)
            notificationManager.notify(3, mBuilder.build())
        }else{
            notificationManager.notify(3, mBuilder.build())
        }
    }

}