package fi.oulu.ubicomp.mobicomplab1

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import org.jetbrains.anko.toast

class ReminderReceiver : BroadcastReceiver() {



    override fun onReceive(context: Context, intent: Intent?) {

        val text = intent.getStringExtra( "message")

        context.toast(text!!)


    }

}