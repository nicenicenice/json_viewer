package com.proj.assignment.test.my.jsonviewer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import com.proj.assignment.test.my.jsonviewer.utils.Utils;
import org.json.JSONException;
import org.json.JSONObject;

import static com.proj.assignment.test.my.jsonviewer.DataContract.*;

public class DetailJsonInfo extends AppCompatActivity {
    final String JSON_RAW_DATA_ITEM = "jsonItemInStr";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_json_info);

        Intent intent = getIntent();
        String jsonItemInString = intent.getStringExtra(JSON_RAW_DATA_ITEM);
        JSONObject jsonDetailItem = getJsonObjByString(jsonItemInString);

        showDetailInfoOnScreen(jsonDetailItem);
    }

    private JSONObject getJsonObjByString(String rawJsonInString) {
        try {
            return Utils.getJsonObjByConvertationString(rawJsonInString);
        } catch (JSONException e) {
            String messageToShow = getResources().getString(R.string.failed_conversion_to_json);
            Toast.makeText(getBaseContext(), messageToShow, Toast.LENGTH_LONG).show();
            return null;
        }
    }

    private void showDetailInfoOnScreen(JSONObject jsonDetailItem) {
        if (jsonDetailItem == null)
            return;

        try {
            TextView firstNameTextView = findViewById(R.id.first_name);
            String firstName = jsonDetailItem.getString(FIRST_NAME);
            firstNameTextView.setText(FIRST_NAME + " : " + firstName);

            TextView lastNameTextView = findViewById(R.id.last_name);
            String lastName = jsonDetailItem.getString(LAST_NAME);
            lastNameTextView.setText(LAST_NAME + " : " + lastName);

            TextView emailTextView = findViewById(R.id.email);
            String email = jsonDetailItem.getString(EMAIL);
            emailTextView.setText(EMAIL + " : " + email);

            TextView genderTextView = findViewById(R.id.gender);
            String gender = jsonDetailItem.getString(GENDER);
            genderTextView.setText(GENDER + " : " + gender);

            TextView ipAddressTextView = findViewById(R.id.ip_address);
            String ipAddress = jsonDetailItem.getString(IP_ADDRESS);
            ipAddressTextView.setText(IP_ADDRESS + " : " + ipAddress);

            setTitle(firstName + " " + lastName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
