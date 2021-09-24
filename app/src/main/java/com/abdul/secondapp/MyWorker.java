package com.abdul.secondapp;
import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class MyWorker extends Worker {

    public MyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        Log.i("CountWorker", "0");
        sendNotification("Notice", "A notice was sent");
        return ListenableWorker.Result.success();

    }

    private void sendNotification(String notice, String a_notice_was_sent) {
    }
}