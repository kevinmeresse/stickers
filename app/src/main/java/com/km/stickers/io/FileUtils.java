package com.km.stickers.io;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

public class FileUtils {

    public static String readFileFromAssets(String filename, Context context) {
        String content = null;
        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            //noinspection ResultOfMethodCallIgnored
            is.read(buffer);
            is.close();
            content = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return content;
    }
}
