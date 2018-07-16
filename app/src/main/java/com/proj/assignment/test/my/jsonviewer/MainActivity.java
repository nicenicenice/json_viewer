package com.proj.assignment.test.my.jsonviewer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.proj.assignment.test.my.jsonviewer.utils.Utils;

public class MainActivity extends AppCompatActivity {

    private AsyncTask<Void, Void, String> mDataLoadFromServiceTask;
    static final String JSON_RAW_DATA = "jsonRawData";
    static ProgressDialog progress;
    static AppCompatActivity that;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        that = this;

        Button refreshButton = (Button) findViewById(R.id.show_json_button);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initializeProgresBar();
                progress.show();
                loadDataFromService();
            }
        });
    }

    private static void showViewerActivity(String jsonRawData) {
        if (jsonRawData == null || jsonRawData.isEmpty())
            return;
        Intent intent = new Intent(that, JsonViewerActivity.class);
        intent.putExtra(JSON_RAW_DATA, jsonRawData);
        that.startActivity(intent);
    }

    private static class DownloadJsonTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            try {
                return Utils.getRawDataFromService();
            } catch (Exception e) {
                return null;
            }
        }
        @Override
        protected void onPostExecute(String result) {
            progress.dismiss();

            if (isCancelled())
                return;

            String messageToShow = "";
            if (result == null || result.isEmpty()) {
                messageToShow = that.getResources().getString(R.string.failed_getting_data);
                Toast.makeText(that, messageToShow, Toast.LENGTH_LONG).show();
                return;
            }
            showViewerActivity(result);
        }
    }

    private void loadDataFromService() {
        mDataLoadFromServiceTask = new DownloadJsonTask().execute();
    }

    private void initializeProgresBar() {
        progress = new ProgressDialog(MainActivity.this);
        progress.setMessage(getResources().getString(R.string.progress_bar_string));
        progress.setCancelable(false);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    }
}