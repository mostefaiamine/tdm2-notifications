package dz.esi.notificationdemo

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Icon
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import android.app.NotificationChannel
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private val channelId = "channel-01"
    private val channelName = "SIL Channel"
    private val importance = NotificationManager.IMPORTANCE_HIGH

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnCreateNotif.setOnClickListener { v -> CreateNotifClick(v) }
    }

    fun CreateNotifClick(v: View) {
        // notification principale


        val testIntent = Intent(this, TestActivity::class.java)
        val pTestIntent = PendingIntent.getActivity(this, System.currentTimeMillis().toInt(), testIntent, 0)
        val notifIntent1 = Intent(this, NotifActivity::class.java)
        notifIntent1.putExtra("data", "Zoom")
        val pNotifIntent1 = PendingIntent.getActivity(this, System.currentTimeMillis().toInt(), notifIntent1, 0)
        val notifIntent2 = Intent(this, NotifActivity::class.java)
        notifIntent2.putExtra("data", "Précédent")
        val pNotifIntent2 = PendingIntent.getActivity(this, System.currentTimeMillis().toInt(), notifIntent2, 0)

        // Icones
        val icon1 = Icon.createWithResource(this, android.R.drawable.btn_minus)
        val icon2 = Icon.createWithResource(this, android.R.drawable.title_bar_tall)

        val action1 = Notification.Action.Builder(icon1, "Zoom", pNotifIntent1).build()
        val action2 = Notification.Action.Builder(icon1, "Précédent", pNotifIntent2).build()




        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val mChannel = NotificationChannel(
                    channelId, channelName, importance)
            notificationManager.createNotificationChannel(mChannel)
        }

        val noti = Notification.Builder(this, channelId)
                .setContentTitle("Nouvelle Notification")
                .setContentText("Je viens de recevioir une notification !")
                .setSmallIcon(android.R.drawable.btn_dialog)
                .setContentIntent(pTestIntent)
                .addAction(action1)
                .addAction(action2)
                .setAutoCancel(true)

                .build()

        notificationManager.notify(0, noti)
    }
}
