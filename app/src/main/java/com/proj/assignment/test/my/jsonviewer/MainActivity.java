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
    ProgressDialog progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button refreshButton = (Button) findViewById(R.id.show_json_button);
        assert refreshButton != null;
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initializeProgresBar();
                progress.show();

                loadDataFromService();
            }
        });
    }

    private void showViewerActivity(String jsonRawData) {
        if (jsonRawData == null || jsonRawData.isEmpty())
            return;
        Intent intent = new Intent(this, JsonViewerActivity.class);
        intent.putExtra("jsonRawData", jsonRawData);
        startActivity(intent);
    }

    private class DownloadJsonTask extends AsyncTask<Void, Void, String> {

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
            if (isCancelled()) return;

            String messageToShow = "";
            if (result == null || result.isEmpty()) {
                messageToShow = getResources().getString(R.string.failed_getting_data);
                progress.dismiss();
                Toast.makeText(getBaseContext(), messageToShow, Toast.LENGTH_LONG).show();
                return;
            }
            progress.dismiss();
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