package com.abdul.secondapp

import android.content.ContentValues.TAG
import android.os.Looper
import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread
import com.google.android.gms.maps.model.LatLng

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.abdul.secondapp", appContext.packageName)
    }

    @Test
    fun useApplicationInfoContextAndMakeSureItIsNotTheSameAsThePackageName() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertNotEquals("com.abdul.secondapp", appContext.applicationInfo)
    }

    @Test
    fun useApplicationInfoAppComponentFactory() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("androidx.core.app.CoreComponentFactory", appContext.applicationInfo.appComponentFactory)
    }

    @Test
    fun useLatitudeAndLongitudeSydney() {
        // Context of the app under test.
       Looper.prepare()
        val sydney = LatLng(-34.0, 151.0)
        runOnUiThread {
            val sydneyTest = MapsActivity().getSydney()
            assertEquals(sydney, sydneyTest)
        }

    }
}

/*
@RunWith(RobolectricTestRunner::class)
class MyActivityTest {
    @Test
    fun clickingButton_shouldChangeMessage() {
        val activity: MyActivity = Robolectric.setupActivity(MyActivity::class.java)
        activity.button.performClick()
        assertThat(activity.message.getText()).isEqualTo("Robolectric Rocks!")
    }
}
*/