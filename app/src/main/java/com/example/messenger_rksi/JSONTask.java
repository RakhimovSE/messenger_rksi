package com.example.messenger_rksi;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JSONTask extends AsyncTask<String, String, String> {
    public AsyncResponse delegate;

    public JSONTask(AsyncResponse asyncResponse) {
        delegate = asyncResponse;
    }

    @Override
    protected String doInBackground(String... urls) {
        try {
            HttpURLConnection urlConnection = null;
            URL url = new URL(urls[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setDoOutput(true);
            urlConnection.connect();

            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuilder sb = new StringBuilder();

            String line;
            while ((line = br.readLine()) != null) {
                sb.append(String.format("%s\n", line));
            }
            br.close();

            return sb.toString();
        } catch (Exception e) {
            Log.d("d", e.toString());
            return null;
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        delegate.processFinish(s);
    }
}
