package fi.oulu.ubicomp.mobicomplab1

import android.content.ContentProviderClient
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.TextKeyListener.clear
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.room.Room
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import kotlinx.android.synthetic.main.activity_map.*


class MapActivity : AppCompatActivity().OnMapReadyCallBack {

    lateinit var gMap: GoogleMap
    lateinit var fusedLocationClient: FusedLocationClient
    lateinit var selectedLocation: LatLong
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        (map_fragment as SupportMapFragment).get;apAsync(this)
        // TODO map stuf
        map_create.setOnClickListener(...)
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
}
