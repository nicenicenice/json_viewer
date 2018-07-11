package com.proj.assignment.test.my.jsonviewer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import com.proj.assignment.test.my.jsonviewer.utils.Utils;
import org.json.JSONException;
import org.json.JSONObject;

public class DetailJsonInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_json_info);

        Intent intent = getIntent();
        JSONObject jsonArray;
        try {
            String jsonItemInString = intent.getStringExtra("jsonItemInStr");
            jsonArray = Utils.getJsonObjByConvertationString(jsonItemInString);
        } catch (JSONException e) {
            String messageToShow = getResources().getString(R.string.failed_conversion_to_json);
            Toast.makeText(getBaseContext(), messageToShow, Toast.LENGTH_LONG).show();
            return;
        }

        try {
            TextView firstNameTextView = findViewById(R.id.first_name);
            String firstName = jsonArray.getString("first_name");
            firstNameTextView.setText("first name : " + firstName);

            TextView lastNameTextView = findViewById(R.id.last_name);
            String lastName = jsonArray.getString("last_name");
            lastNameTextView.setText("last name : " + lastName);

            TextView emailTextView = findViewById(R.id.email);
            String email = jsonArray.getString("email");
            emailTextView.setText("email : " + email);

            TextView genderTextView = findViewById(R.id.gender);
            String gender = jsonArray.getString("gender");
            genderTextView.setText("gender : " + gender);

            TextView ipAddressTextView = findViewById(R.id.ip_address);
            String ipAddress = jsonArray.getString("ip_address");
            ipAddressTextView.setText("ip address : " + ipAddress);

            setTitle(firstName + " " + lastName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
