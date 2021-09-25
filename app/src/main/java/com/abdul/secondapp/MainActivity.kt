package com.abdul.secondapp

import android.Manifest
import android.content.IntentSender
import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CursorAdapter
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import android.content.pm.PackageManager
import android.location.Location

import android.os.Build
import android.os.Looper
import android.provider.CallLog
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat.requestPermissions
import com.google.firebase.firestore.FirebaseFirestore
import androidx.annotation.NonNull
import androidx.core.app.ActivityCompat
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*

import com.google.android.gms.tasks.OnFailureListener

import com.google.firebase.firestore.DocumentReference

import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.QueryDocumentSnapshot

import com.google.firebase.firestore.QuerySnapshot

import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task


class MainActivity : AppCompatActivity() {
    var TAG = MainActivity::class.java.simpleName
    lateinit var db:  FirebaseFirestore
    private var fusedLocationClient: FusedLocationProviderClient? = null
    var locationRequest = null
    protected val REQUEST_CHECK_SETTINGS = 0x1
    var requestingLocationUpdates = true

    private lateinit var locationCallback: LocationCallback
    val REQUESTING_LOCATION_UPDATES_KEY = "requesting-location-updates-key";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult ?: return
                for (location in locationResult.locations) {
                    // Update UI with location data
                    // ...
                }
            }
        }
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        fusedLocationClient?.lastLocation
            ?.addOnSuccessListener { location : Location? ->
                // Got last known location. In some rare situations this can be null.
            }

        setContentView(R.layout.activity_main)
        FirebaseFirestore.setLoggingEnabled(true);

        db = FirebaseFirestore.getInstance()

        // setupListView()
        createLocationRequest()
        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)
        val client: SettingsClient = LocationServices.getSettingsClient(this)
        val task: Task<LocationSettingsResponse> = client.checkLocationSettings(builder.build())
        task.addOnSuccessListener { locationSettingsResponse ->
            // All location settings are satisfied. The client can initialize
            // location requests here.
            // ...
        }

        task.addOnFailureListener { exception ->
            if (exception is ResolvableApiException){
                // Location settings are not satisfied, but this can be fixed
                // by showing the user a dialog.
                try {
                    // Show the dialog by calling startResolutionForResult(),
                    // and check the result in onActivityResult().
                    exception.startResolutionForResult(this@MainActivity,
                        REQUEST_CHECK_SETTINGS)
                } catch (sendEx: IntentSender.SendIntentException) {
                    // Ignore the error.
                }
            }
        }

        updateValuesFromBundle(savedInstanceState)
    }

    private fun updateValuesFromBundle(savedInstanceState: Bundle?) {
        savedInstanceState ?: return

        // Update the value of requestingLocationUpdates from the Bundle.
        if (savedInstanceState.keySet().contains(REQUESTING_LOCATION_UPDATES_KEY)) {
            requestingLocationUpdates = savedInstanceState.getBoolean(
                REQUESTING_LOCATION_UPDATES_KEY)
        }

        // ...

        // Update UI to match restored state
        updateUI()
    }

    private fun updateUI() {
        TODO("Not yet implemented")
        startLocationUpdates()
    }

    override fun onResume() {
        super.onResume()
        if (requestingLocationUpdates) startLocationUpdates()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        var requestingLocationUpdates = true
        outState?.putBoolean(REQUESTING_LOCATION_UPDATES_KEY, requestingLocationUpdates)
        super.onSaveInstanceState(outState)
    }

    private fun startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        fusedLocationClient?.requestLocationUpdates(locationRequest,
            locationCallback,
            Looper.getMainLooper())
    }

    fun createLocationRequest() {
        locationRequest = LocationRequest.create()?.apply {
            interval = 10000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        } as Nothing?
    }

    private fun setupListView() {
        //var listview: ListView = findViewById(R.id.cpList)
        val uriSms: Uri = Uri.parse(CallLog.Calls.CONTENT_URI.toString())
        //"content://sms/inbox") //url
        val cursor: Cursor? = getContentResolver().query(uriSms, null, null, null, null)

        var adapter: CursorAdapter = SimpleCursorAdapter(
            this,
            android.R.layout.simple_list_item_1, //row layout
            cursor,    //data cursor
            arrayOf(CallLog.Calls.NUMBER),
            //"body", "address"), //column names
            intArrayOf(android.R.id.text1)
        ) //textview id
        //listview.adapter = adapter
    }

    fun firestoreHandler(view: View) {
        when(view.id){
            R.id.btnSend ->  {
                sendDataFirestore()
            }
            R.id.btnGet -> {
                getDataFireStore()
            }
        }
    }

    private fun getDataFireStore(){
        db.collection("users")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        Log.d(TAG, document.id + " => " + document.data)
                    }
                } else {
                    Log.w(TAG, "Error getting documents.", task.exception)
                }
            }
    }

    private fun sendDataFirestore() {
        // Create a new user with a first and last name
        val user: MutableMap<String, Any> = HashMap()
        user["first"] = "Ada"
        user["last"] = "Lovelace"
        user["born"] = 1815
        
        // Add a new document with a generated ID
        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d(
                    TAG,
                    "DocumentSnapshot added with ID: " + documentReference.id
                )
            }
            .addOnFailureListener { e -> Log.w(TAG, "Error adding document", e) }
    }


}