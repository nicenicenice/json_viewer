package com.proj.assignment.test.my.jsonviewer;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.proj.assignment.test.my.jsonviewer.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonViewerActivity extends AppCompatActivity {

    Map<String, String> dataMap;
    final String JSON_RAW_DATA = "jsonRawData";
    final String JSON_RAW_DATA_ITEM = "jsonItemInStr";
    LinearLayout scrollViewLayout;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<String> itemOfNamesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_viewer);

        Intent intent = getIntent();
        String jsonRawData = intent.getStringExtra(JSON_RAW_DATA);
        JSONArray jsonArray = getJsonArrayByString(jsonRawData);
        setMapAndShowDataOnScreen(jsonArray);
    }

    private JSONArray getJsonArrayByString(String rawJsonInString) {
        try {
            return Utils.getJsonArrayByConvertationString(rawJsonInString);
        } catch (JSONException e) {
            String messageToShow = getResources().getString(R.string.failed_conversion_to_json);
            Toast.makeText(getBaseContext(), messageToShow, Toast.LENGTH_LONG).show();
            return null;
        }
    }

//    private void fillDataSet(JSONArray jsonArray) {
//        itemOfNamesList = getNameOdItemsList(jsonArray);
//        mAdapter.notifyDataSetChanged();
//    }

    private void setMapAndShowDataOnScreen(JSONArray jsonArray) {
        dataMap = new HashMap<>();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_list);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        itemOfNamesList = getNameOfItemsList(jsonArray);
        mAdapter = new DataAdapter(itemOfNamesList);
        mRecyclerView.setAdapter(mAdapter);

        //fillDataSet(jsonArray);

        /*for (int i = 0 ; i < jsonArray.length(); i++) {
            try {
                final JSONObject jsonItem = jsonArray.getJSONObject(i);
                String nameOfItem = Utils.getNameItemByJsonObj(jsonItem);

                dataMap.put(nameOfItem, jsonItem.toString());

                String detailItemData = Utils.getStringWithAllJsonData(jsonItem);
                addTextViewToScrollView(nameOfItem, detailItemData);
            } catch (JSONException e) { }
        }*/
    }

    public List<String> getNameOfItemsList(JSONArray jsonArray) {
        List<String> result = new ArrayList<>();
        for (int i = 0 ; i < jsonArray.length(); i++) {
            try {
                final JSONObject jsonItem = jsonArray.getJSONObject(i);
                String nameOfItem = Utils.getNameItemByJsonObj(jsonItem);
                dataMap.put(nameOfItem, jsonItem.toString());

                result.add(nameOfItem);
            } catch (JSONException e) { }
        }
        return result;
    }

    public void myClickHandler(View target) {
        TextView textView = (TextView) target;
        String nameOfItem = textView.getText().toString();
        String jsonItem = dataMap.get(nameOfItem);
        if (jsonItem == null || jsonItem.isEmpty()) {
            Toast.makeText(
                    getBaseContext(),
                    getResources().getString(R.string.could_not_retrieve_item_data),
                    Toast.LENGTH_LONG
            ).show();
            return;
        }
        showDetailInfoInActivity(jsonItem);
    }

    private void showDetailInfoInActivity(String jsonItemInString) {
        if (jsonItemInString == null || jsonItemInString.isEmpty())
            return;

        Intent intent = new Intent(this, DetailJsonInfo.class);
        intent.putExtra(JSON_RAW_DATA_ITEM, jsonItemInString);
        startActivity(intent);
    }
}

