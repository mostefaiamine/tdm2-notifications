package dz.esi.notificationdemo

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_notif.*

class NotifActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notif)
        val i = intent
        try {
            val data = i.getStringExtra("data")
            txtReceiver.text = "Reçu : " + data
        } catch (ex: Exception) {
            txtReceiver.text = "Erreur"
        }

    }
}
