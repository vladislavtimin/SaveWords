package com.nodomain.savewords;


import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


public class FileManager {

    private static final String EXTERNAL_STORAGE_PATH =
            Environment.getExternalStorageDirectory().getAbsolutePath() + "/";

    private static final String APP_FOLDER = ".SaveWords/";
    private static final String SOUNDS_FOLDER = "sounds/";
    private static final String PICTURES_FOLDER = "pictures/";


    public FileManager() {
        File appFolder = new File(EXTERNAL_STORAGE_PATH + APP_FOLDER);
        File picturesFolder = new File(EXTERNAL_STORAGE_PATH + APP_FOLDER + PICTURES_FOLDER);
        File soundsFolder = new File(EXTERNAL_STORAGE_PATH + APP_FOLDER + SOUNDS_FOLDER);

        if (!appFolder.exists()) {
            appFolder.mkdirs();
        }

        if (!picturesFolder.exists()) {
            picturesFolder.mkdirs();
        }

        if (!soundsFolder.exists()) {
            soundsFolder.mkdirs();
        }
    }

    public void saveDataToFile(byte[] data, File file) throws IOException {
        OutputStream out = null;

        try {
            out = new FileOutputStream(file);
            out.write(data);
        } catch (IOException e) {
            Log.e("MyTag", e.getMessage());
            throw e;
        } finally {
            try {
                if (out != null) out.close();
            } catch (IOException e) {
            Log.e("MyTag", e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public File makeSoundFile(String fileName) {
        String soundFilePath = EXTERNAL_STORAGE_PATH + APP_FOLDER + SOUNDS_FOLDER + fileName + ".mp3";
        return new File(soundFilePath);
    }

    public File makePictureFile(String fileName, String fileExtension) {
        String pictureFilePath = EXTERNAL_STORAGE_PATH + APP_FOLDER + PICTURES_FOLDER + fileName + fileExtension;
        return new File(pictureFilePath);
    }
}
