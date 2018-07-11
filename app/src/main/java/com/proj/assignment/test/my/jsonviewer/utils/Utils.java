package com.proj.assignment.test.my.jsonviewer.utils;

import android.support.annotation.Nullable;

import com.proj.assignment.test.my.jsonviewer.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Utils {
    private static String SERVICE_URL = "http://www.mocky.io/v2/5b45ced42f00008900420dd9";

    public static JSONArray getJsonArrayByConvertationString(String rawJsonData) throws JSONException {
        return new JSONArray(rawJsonData);
    }

    public static JSONObject getJsonObjByConvertationString(String rawJsonData) throws JSONException {
        return new JSONObject(rawJsonData);
    }

    public static String getStringWithAllJsonData(JSONObject jsonItem) {

        try {
            String result = "id : " + jsonItem.getString("id") + "\n" +
                    "first_name : " + jsonItem.getString("first_name") + "\n" +
                    "last_name : " + jsonItem.getString("last_name") + "\n" +
                    "email : " + jsonItem.getString("email") + "\n" +
                    "gender : " + jsonItem.getString("gender") + "\n" +
                    "ip_address : " + jsonItem.getString("ip_address");
            return result;
        } catch (Exception e) {
            return "an unexpected error occurred";
        }

    }

    public static String getRawDataFromService() throws Exception {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
            .url(SERVICE_URL)
            .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}