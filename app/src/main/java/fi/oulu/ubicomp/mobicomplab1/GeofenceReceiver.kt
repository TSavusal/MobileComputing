package fi.oulu.ubicomp.mobicomplab1

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingEvent

class GeofenceReceiver: BroadcastReceiver {

    override fun onReceive(context:: Context?, intent: Intent?) {

        val geofencingEvent = GeofencingEvent.fronIntent(intent)
        val geofencingTransition=geofencingEvent.geofencingTransition

        if(geofencingTransition==Geofence.GEOFENCE_TRANSITION_ENTER||geofencingTransition==Geofence.GEOFENCE_TRANSITION_DWELL)
            var uid=intent!!.getExtra("uid",0)
            var text=intent.getStringExtra("message")

        MainActivity.showNotification(context!!,text)
    }
}