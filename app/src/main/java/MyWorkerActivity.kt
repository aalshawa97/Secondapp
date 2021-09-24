package com.abdul.secondapp
/*
import android.content.ContentValues.TAG
import androidx.work.WorkManager

import androidx.work.PeriodicWorkRequest

import android.os.Bundle
import android.util.Log

import androidx.appcompat.app.AppCompatActivity
import androidx.work.Constraints
import androidx.work.ListenableWorker
import com.google.common.util.concurrent.ListenableFuture
import java.util.concurrent.TimeUnit


class MyWorkerActivity : ListenableWorker() {
   var Tag = "My Worker Activity"
   fun onCreate(savedInstanceState: Bundle?) {
       val pwr = PeriodicWorkRequest.Builder(MyWorker::class.java, 16, TimeUnit.MINUTES)
           .setConstraints(Constraints.NONE)
           .build()
       WorkManager.getInstance().enqueue(pwr)
   }

   /**
    * Override this method to start your actual background processing. This method is called on
    * the main thread.
    *
    *
    * A ListenableWorker has a well defined
    * [execution window](https://d.android.com/reference/android/app/job/JobScheduler)
    * to to finish its execution and return a [Result].  After this time has expired, the
    * worker will be signalled to stop and its [ListenableFuture] will be cancelled.
    *
    *
    * The future will also be cancelled if this worker is stopped for any reason
    * (see [.onStopped]).
    *
    * @return A [ListenableFuture] with the [Result] of the computation.  If you
    * cancel this Future, WorkManager will treat this unit of work as failed.
    */

   override fun startWork(): ListenableFuture<Result> {
       // Do your work here

   }

}

*/