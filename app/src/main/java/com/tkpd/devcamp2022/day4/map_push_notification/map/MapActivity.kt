package com.tkpd.devcamp2022.day4.map_push_notification.map

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import com.mapbox.android.core.location.LocationEngine
import com.mapbox.android.core.location.LocationEngineCallback
import com.mapbox.android.core.location.LocationEngineProvider
import com.mapbox.android.core.location.LocationEngineResult
import com.mapbox.android.core.permissions.PermissionsListener
import com.mapbox.android.core.permissions.PermissionsManager
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.plugin.annotation.AnnotationPlugin
import com.mapbox.maps.plugin.annotation.annotations
import com.mapbox.maps.plugin.annotation.generated.*
import com.mapbox.maps.viewannotation.viewAnnotationOptions
import com.tkpd.devcamp2022.R
import com.tkpd.devcamp2022.databinding.ActivityMapBinding
import java.lang.ref.WeakReference

class MapActivity : AppCompatActivity(), PermissionsListener {

    private lateinit var binding: ActivityMapBinding
    private var marker: Bitmap? = null
    private var annotationApi: AnnotationPlugin? = null
    private lateinit var locationEngine: LocationEngine
    private lateinit var permissionsManager: PermissionsManager

    companion object {
        val TAG: String = MapActivity::class.java.name
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initMapActivity()
        requestLocationPermission()
    }

    private fun initMapActivity() {
        marker = bitmapFromDrawableRes(this@MapActivity, R.drawable.red_marker)
        annotationApi = binding.mapView.annotations
        permissionsManager = PermissionsManager(this)
        locationEngine = LocationEngineProvider.getBestLocationEngine(this)
    }

    @SuppressLint("MissingPermission")
    private fun requestLocationPermission() {
        if (PermissionsManager.areLocationPermissionsGranted(this)) {
            // Permission sensitive logic called here,
            // such as activating the Maps SDK's LocationComponent to show the device's location
            val callback = LocationListeningCallback(this)
            locationEngine.getLastLocation(callback)
        } else {
            permissionsManager.requestLocationPermissions(this)
        }
    }

    private fun addPointAnnotation(location: Location, bitmap: Bitmap) {
        val pointAnnotationManager = annotationApi?.createPointAnnotationManager()
        // Set options for the resulting symbol layer.
        val pointAnnotationOptions: PointAnnotationOptions = PointAnnotationOptions()
            // Define a geographic coordinate.
            .withPoint(Point.fromLngLat(location.longitude, location.latitude))
            // Specify the bitmap you assigned to the point annotation
            // The bitmap will be added to map style automatically.
            .withIconImage(bitmap)
        // Add the resulting pointAnnotation to the map.
        pointAnnotationManager?.create(pointAnnotationOptions)
        // define camera position
        val cameraPosition = CameraOptions.Builder()
            .zoom(14.0)
            .center(pointAnnotationOptions.getPoint())
            .build()
        // set camera position
        binding.mapView.getMapboxMap().setCamera(cameraPosition)
    }

    private fun addCircleAnnotation() {
        val circleAnnotationManager = annotationApi?.createCircleAnnotationManager()
        // Set options for the resulting circle layer.
        val circleAnnotationOptions: CircleAnnotationOptions = CircleAnnotationOptions()
            // Define a geographic coordinate.
            .withPoint(Point.fromLngLat(18.06, 59.31))
            // Style the circle that will be added to the map.
            .withCircleRadius(8.0)
            .withCircleColor("#ee4e8b")
            .withCircleStrokeWidth(2.0)
            .withCircleStrokeColor("#ffffff")
        // Add the resulting circle to the map.
        circleAnnotationManager?.create(circleAnnotationOptions)
    }

    private fun addPolylineAnnotation() {
        val polylineAnnotationManager = annotationApi?.createPolylineAnnotationManager()
        // Define a list of geographic coordinates to be connected.
        val points = listOf(
            Point.fromLngLat(17.94, 59.25),
            Point.fromLngLat(18.18, 59.37)
        )
        // Set options for the resulting line layer.
        val polylineAnnotationOptions: PolylineAnnotationOptions = PolylineAnnotationOptions()
            .withPoints(points)
            // Style the line that will be added to the map.
            .withLineColor("#ee4e8b")
            .withLineWidth(5.0)
        // Add the resulting line to the map.
        polylineAnnotationManager?.create(polylineAnnotationOptions)
    }

    private fun addViewAnnotation(point: Point) {
        val viewAnnotationManager = binding.mapView.viewAnnotationManager
        // Define the view annotation
        viewAnnotationManager.addViewAnnotation(
            // Specify the layout resource id
            resId = R.layout.annotation_view,
            // Set any view annotation options
            options = viewAnnotationOptions {
                geometry(point)
            }
        )
        // define camera position
        val cameraPosition = CameraOptions.Builder()
            .zoom(14.0)
            .center(point)
            .build()
        // set camera position
        binding.mapView.getMapboxMap().setCamera(cameraPosition)
    }

    private fun bitmapFromDrawableRes(context: Context, @DrawableRes resourceId: Int) =
        convertDrawableToBitmap(AppCompatResources.getDrawable(context, resourceId))

    private fun convertDrawableToBitmap(sourceDrawable: Drawable?): Bitmap? {
        if (sourceDrawable == null) {
            return null
        }
        return if (sourceDrawable is BitmapDrawable) {
            sourceDrawable.bitmap
        } else {
            // copying drawable object to not manipulate on the same reference
            val constantState = sourceDrawable.constantState ?: return null
            val drawable = constantState.newDrawable().mutate()
            val bitmap: Bitmap = Bitmap.createBitmap(
                drawable.intrinsicWidth, drawable.intrinsicHeight,
                Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, canvas.width, canvas.height)
            drawable.draw(canvas)
            bitmap
        }
    }

    override fun onExplanationNeeded(permissionsToExplain: MutableList<String>?) {
        // explain why this app need this list of permissions to be allowed
        Log.i(TAG, permissionsToExplain.toString())
    }

    @SuppressLint("MissingPermission")
    override fun onPermissionResult(granted: Boolean) {
        if (granted) {
            // Permission sensitive logic called here,
            // such as activating the Maps SDK's LocationComponent to show the device's location
            val callback = LocationListeningCallback(this)
            locationEngine.getLastLocation(callback)
        } else {
            // User denied the permission
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private class LocationListeningCallback(activity: MapActivity) :
        LocationEngineCallback<LocationEngineResult> {

        private val activityWeakReference: WeakReference<MapActivity> = WeakReference(activity)
        override fun onSuccess(result: LocationEngineResult?) {
            val marker = activityWeakReference.get()?.marker
            marker?.let {
                result?.lastLocation?.apply {
                    activityWeakReference.get()?.addPointAnnotation(this, marker)
                }
            }
        }

        override fun onFailure(exception: Exception) {
            Log.e(TAG, exception.toString())
        }
    }
}