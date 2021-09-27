package com.abdul.secondapp

import android.content.ContentValues.TAG
import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

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
        //Log.d(TAG, "useApplicationInfoContext: " + appContext.applicationInfo.appComponentFactory)
        assertEquals("androidx.core.app.CoreComponentFactory", appContext.applicationInfo.appComponentFactory)
    }
}