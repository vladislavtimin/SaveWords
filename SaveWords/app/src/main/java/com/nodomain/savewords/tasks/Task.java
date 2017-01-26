package com.nodomain.savewords.tasks;


import android.os.Handler;

public class Task {

    private Handler handler;

    protected Task(Handler handler) {
        this.handler = handler;
    }

    protected void doInNewThread(Runnable runnable) {
        new Thread(runnable).start();
    }

    protected void post(Runnable runnable) {
        handler.post(runnable);
    }
}
