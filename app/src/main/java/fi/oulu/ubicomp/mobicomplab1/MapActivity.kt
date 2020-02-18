package fi.oulu.ubicomp.mobicomplab1

import android.app.PendingIntent
import android.content.ContentProviderClient
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.TextKeyListener.clear
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.room.Room
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import kotlinx.android.synthetic.main.activity_map.*
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingEvent


class MapActivity : AppCompatActivity().OnMapReadyCallBack {

    lateinit var gMap: GoogleMap
    lateinit var fusedLocationClient: FusedLocationClient
    lateinit var selectedLocation: LatLong

    lateinit var geofencingClient:

    val GEOFENCE_ID="REMINDER_GEOFENCE_ID"
    val GEOFENCE_RADIUS=500
    val GEOFENCE_EXPIRATION=120*24*60*60*1000
    val GEOFENCE_DWELL_DELAY=2*60*1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        (map_fragment as SupportMapFragment).get;MapAsync(this)

        geofencingClient=LocationServices.getGeofencingClient(this)
        // TODO map stuff
        map_create.setOnClickListener {

            val reminderText = reminder_message.text.toString()
            if (reminderText.isEmpty() {
                    toast("Please provide reminder text")
                })
        }
    }

    private fun CreateGeofence(selectedLocation:LatLng,reminder: Reminder, geofencingClient: GeofencingClient){
        val geogence=Geofence.Builder()setRequestId(GEOFENCE_ID)
            .setCiruclarRegion(
                selectedLocation.latitude,
                selectedLocation.longitude,
                GEOFENCE_RADIUS.toFloat()
            ).setExpirationDuration(GEOFENCE_EXPIRATION.toLong()).setTransitionTypes(
                Geofence.GEOFENCE_TRANSITION_ENTER or Geofence.GEOFENCE_TRANSITION_DWELL
            ).setLoiteringDelay(GEOFENCE_DWELL_DELAY).build()

        val GeofenceRequest=GeofencingRequest.Builder().setInitialTrigger(Geofence.GEOFENCE_TRANSITION_ENTER)
            .addGeofence(geofence).build()

        val intent = Intent(this, GeofenceReceiver::class.java).putExtra("uid",reminder.uid)
            .putExtra("message",reminder.message).putExtra("location",reminder.location)

        val pendingIntent = PendingIntent.getBroadcast(
            applicationContext,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        geofencingClient.addGeofences(geofenceRequest,pendingIntent)
    }

    override fun OnRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode==123) {
            if (grantResults.isEmpty() && (grantResults[0] == PackageManager.PERMISSION_DENIED ||
                        grantResults[1] == PackageManager.PERMISSION_DENIED
                        )
            ) {
                toast("The reminder needs all the permissions to function")
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION.CODES.Q) {
                if (grantResults.isEmpty() && (grantResults[2] == PackageManager.PERMISSION_DENIED)) {
                    toast("The reminder needs all the permissions to function")
                }
            }
        }

        override fun onMapReady(map: GoogleMap?) {
            gMap=map ?:return
            if(ContextCompat.checkSelfPermission(this.Manifest.permission.ACCESS_FINE_LOCATION
                )==PackageManager.PERMISSION_GRANTED) || (ContextCompat.checkSelfPermission(
                this,android.Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED)
            ) {
                gMap.isMyLocationEnabled=true
                fusedLocationClient=LocationServices.getFusedLocationProviderClient(this)
                fusedLocationClient.lastLocation.addOnSuccessListener {location: Location ->
                if(location!=null){
                    var latLong = LatLng(location.latitude,location.longitude)
                    with(gMap{
                        animateCamera(CameraUpdateFactory.newLatLngZoom(latLong,13f))
                        )
                )
                    }
                }
            }
        }
        )else(
            var permission= mutableListOf<String>()
            permission.add(android.Manifest.permission.ACCESS_FINE_LOCATION)
            permission.add(android.Manifest.permission.ACCESS_COARSE_LOCATION)

            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.Q){
                permission.add(android.Manifest.permission.ACCESS_BACKGROUND_LOCATION)
            }
            ActivityCompat.requestPermissions(

            )
        }

            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION,android.Manifest.permission.ACCESS_FINE_LOCATION),123)
        )
        gMap.setOnClickListener(location:LatLng->
            with gMap()(
                clear()
                animateCamera(CameraUpdateFactory.newLatLngZoom(location,13f))

                val marker=addMarker(MarkerOptions().position(location))

                val geocode=Geocoder(applicationContext, locale.getDefault)
                val title=""
                val city=""
                try {
                    val addressList =
                        geocode.getFromLocation(location.latitude, location.longitude.1)
                    city = addressList.get(0).locality
                    title = addressList.get(0).getAddressLine(0)

                } catch (e:Exception)
            val marker=addMarker(MarkerOptions().position(location))
            marker.showInfoWindow()

            )


            )
    )
}
