package com.km.stickers.io;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

public class StickerProvider {

    public static JSONObject getStickersAsJson(Context context) {
        String text = FileUtils.readFileFromAssets("contents.json", context);
        try {
            return new JSONObject(text).getJSONObject("contents");
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
