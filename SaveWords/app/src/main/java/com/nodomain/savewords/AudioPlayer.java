package com.nodomain.savewords;


import android.content.Context;
import android.media.MediaPlayer;

import java.io.IOException;


public class AudioPlayer {

    public void playFile(String filePath) {
        try {
            MediaPlayer mp = new MediaPlayer();
            mp.setDataSource(filePath);
            mp.prepare();
            mp.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void playRes(Context context, int resId) {
        MediaPlayer mp = MediaPlayer.create(context, resId);
        mp.start();
    }
}
