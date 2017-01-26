package com.nodomain.savewords.tasks;


import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.nodomain.savewords.WordsStorage;

import java.io.IOException;

public class AddWordTask extends Task {

    public AddWordTask(Handler handler) {
        super(handler);
    }

    public void addWord(final AddWordTaskListener listener, final Context context, final String word, final byte[] image) {
        doInNewThread(new Runnable() {
            @Override
            public void run() {
                try {
                    WordsStorage.getInstance(context).addWord(word, image);
                    post(new Runnable() {
                        @Override
                        public void run() {
                            listener.wordAdded();
                        }
                    });
                } catch (IOException e) {
                    Log.e("MyTag", e.getMessage() + " " + e.toString());
                    post(new Runnable() {
                        @Override
                        public void run() {
                            listener.wordAdditionFail();
                        }
                    });
                }
            }
        });
    }
}
