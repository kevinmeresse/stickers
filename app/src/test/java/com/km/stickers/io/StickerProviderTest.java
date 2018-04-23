package com.km.stickers.io;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.*;

public class StickerProviderTest {

    public static JSONObject getStickersAsJson() {
        JSONObject json = null;
        try {
            json = new JSONObject("{  \n" +
                    "   \"1CTQNP6\":{  \n" +
                    "      \"content_id\":\"1CTQNP6\",\n" +
                    "      \"assets\":[  \n" +
                    "         {  \n" +
                    "            \"asset_id\":\"bd0d471a\",\n" +
                    "            \"url\":\"https://cdn.emogi.com/cxp/1CT/1CTQNP6-thumb.gif\",\n" +
                    "            \"file_extension\":\"gif\",\n" +
                    "            \"size\":\"thumb\"\n" +
                    "         },\n" +
                    "         {  \n" +
                    "            \"asset_id\":\"04d114e8\",\n" +
                    "            \"url\":\"https://cdn.emogi.com/cxp/1CT/1CTQNP6-full.gif\",\n" +
                    "            \"file_extension\":\"gif\",\n" +
                    "            \"size\":\"full\"\n" +
                    "         }\n" +
                    "      ],\n" +
                    "      \"tags\":[  \n" +
                    "         \"omw!\",\n" +
                    "         \"omw!\",\n" +
                    "         \"coming?\",\n" +
                    "         \"go!\",\n" +
                    "         \"ready?\",\n" +
                    "         \"omw\",\n" +
                    "         \"ready\",\n" +
                    "         \"hurry\",\n" +
                    "         \"want coffee\"\n" +
                    "      ]\n" +
                    "   },\n" +
                    "   \"47DQJ4\":{  \n" +
                    "      \"content_id\":\"47DQJ4\",\n" +
                    "      \"assets\":[  \n" +
                    "         {  \n" +
                    "            \"asset_id\":\"a8a65466\",\n" +
                    "            \"url\":\"https://cdn.emogi.com/cxp/47D/47DQJ4-thumb.gif\",\n" +
                    "            \"file_extension\":\"gif\",\n" +
                    "            \"size\":\"thumb\"\n" +
                    "         }\n" +
                    "      ],\n" +
                    "      \"tags\":[  \n" +
                    "         \"yoo\",\n" +
                    "         \"'sup\",\n" +
                    "         \"yooo\",\n" +
                    "         \"heyy\"\n" +
                    "      ]\n" +
                    "   },\n" +
                    "   \"74gmu\":{  \n" +
                    "      \"content_id\":\"74gmu\",\n" +
                    "      \"assets\":[  \n" +
                    "         {  \n" +
                    "            \"asset_id\":\"ed03d1ee\",\n" +
                    "            \"url\":\"https://cdn.emogi.com/cxp/74g/74gmu-thumb.gif\",\n" +
                    "            \"file_extension\":\"gif\",\n" +
                    "            \"size\":\"thumb\"\n" +
                    "         },\n" +
                    "         {  \n" +
                    "            \"asset_id\":\"fa459b15\",\n" +
                    "            \"url\":\"https://cdn.emogi.com/cxp/74g/74gmu-full.gif\",\n" +
                    "            \"file_extension\":\"gif\",\n" +
                    "            \"size\":\"full\"\n" +
                    "         }\n" +
                    "      ],\n" +
                    "      \"tags\":[  \n" +
                    "         \"ready\",\n" +
                    "         \"want pizza\"\n" +
                    "      ]\n" +
                    "   }\n" +
                    "}");
        } catch (JSONException ignored) {}
        return json;
    }
}