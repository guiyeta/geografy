package com.trabajoIntegrador.geografy

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class Notificacion : Service() {
    private val CHANNEL_ID: String = "Recordar Usuario"

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val notificacion = crearNotificacion()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            crearChanelId()
            startForeground(2, notificacion)
        } else {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details
            } else {
                NotificationManagerCompat.from(this).notify(2, notificacion)
            }
        }

        if(intent?.getBooleanExtra("seleccionado",true) == false){
            stopNotificacion()
            stopSelf()
        }

        return START_STICKY
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun crearChanelId() {
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(CHANNEL_ID, "Recordar usuario", importance)
        channel.description = "Se mostrara al dar check en Recordar Usuario"
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    private fun crearNotificacion(): Notification {
        val intentOk = Intent(this, Notificacion::class.java)
        intentOk.putExtra("seleccionado", false)
        val piOk = PendingIntent
            .getService(this, 1, intentOk, PendingIntent.FLAG_UPDATE_CURRENT)

        val okAction = NotificationCompat.Action.Builder(
            R.drawable.baseline_castle_24, "Ok", piOk
        ).build()

        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Se recordará el usuario")
            .setContentText("No se le volvera a pedir la información de inicio de sesión")
            .addAction(okAction)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setOngoing(true)
            .setSmallIcon(R.drawable.baseline_castle_24)
            .build()
    }

    private fun stopNotificacion() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            NotificationManagerCompat.from(this).cancel(2)
        }
    }

    override fun onDestroy() {
        stopNotificacion()
        super.onDestroy()
    }


}