package com.proj.assignment.test.my.jsonviewer.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import static com.proj.assignment.test.my.jsonviewer.DataContract.*;

public class Utils {
    private final static String SERVICE_URL = "http://www.mocky.io/v2/5b45ced42f00008900420dd9";

    public static JSONArray getJsonArrayByConvertationString(String rawJsonData) throws JSONException {
        return new JSONArray(rawJsonData);
    }

    public static JSONObject getJsonObjByConvertationString(String rawJsonData) throws JSONException {
        return new JSONObject(rawJsonData);
    }

    public static String getStringWithAllJsonData(JSONObject jsonItem) {

        try {
            String result = ID + " : " + jsonItem.getString(ID) + "\n" +
                    FIRST_NAME + " : " + jsonItem.getString(FIRST_NAME) + "\n" +
                    LAST_NAME + " : " + jsonItem.getString(LAST_NAME) + "\n" +
                    EMAIL + " : " + jsonItem.getString(EMAIL) + "\n" +
                    GENDER + " : " + jsonItem.getString(GENDER) + "\n" +
                    IP_ADDRESS + " : " + jsonItem.getString(IP_ADDRESS);
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