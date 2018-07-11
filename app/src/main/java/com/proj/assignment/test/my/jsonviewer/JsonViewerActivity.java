package com.proj.assignment.test.my.jsonviewer;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.proj.assignment.test.my.jsonviewer.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class JsonViewerActivity extends AppCompatActivity implements View.OnClickListener {

    Map<String, JSONObject> dataMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_viewer);

        Intent intent = getIntent();
        JSONArray jsonArray;
        try {
            String jsonRawData = intent.getStringExtra("jsonRawData");
            jsonArray = Utils.getJsonArrayByConvertationString(jsonRawData);
        } catch (JSONException e) {
            String messageToShow = getResources().getString(R.string.failed_conversion_to_json);
            Toast.makeText(getBaseContext(), messageToShow, Toast.LENGTH_LONG).show();
            return;
        }

        dataMap = new HashMap<>();
        LinearLayout scrollViewLayout = (LinearLayout) findViewById(R.id.scroll_view_lauout);

        for (int i = 0 ; i < jsonArray.length(); i++) {
            try {
                final JSONObject jsonItem = jsonArray.getJSONObject(i);

                String uniqNameOfItem = jsonItem.getInt("id") + "_"
                        + jsonItem.getString("first_name")
                        + "_" + jsonItem.getString("last_name");

                dataMap.put(uniqNameOfItem, jsonItem);

                TextView textView = new TextView(this);
                textView.setText(uniqNameOfItem);
                textView.setTextSize(20);
                textView.setPadding(15, 15, 15, 15);
                textView.setOnClickListener(this);
                textView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override public boolean onLongClick(View v) {
                        new AlertDialog.Builder(JsonViewerActivity.this)
                            .setMessage(Utils.getStringWithAllJsonData(jsonItem))
                            .show();
                        return true;
                    }
                });
                scrollViewLayout.addView(textView);
            } catch (JSONException e) { /* do nothing */ }
        }
    }

    private void showDetailInfoInActivity(JSONObject jsonItem) {
        if (jsonItem == null)
            return;

        String jsonItemInString = jsonItem.toString();
        Intent intent = new Intent(this, DetailJsonInfo.class);
        intent.putExtra("jsonItemInStr", jsonItemInString);
        startActivity(intent);
    }

    public void onClick(View v) {
        TextView textView = (TextView) v;
        String nameOfItem = textView.getText().toString();
        JSONObject jsonItem = dataMap.get(nameOfItem);
        if (jsonItem == null) {
            Toast.makeText(getBaseContext(), "some error happened", Toast.LENGTH_LONG).show();
            return;
        }
        showDetailInfoInActivity(jsonItem);
    }
}