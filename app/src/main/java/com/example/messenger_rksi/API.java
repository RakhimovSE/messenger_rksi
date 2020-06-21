package com.example.messenger_rksi;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import org.json.JSONObject;

public class API {
    View view;

    public API(View view) {
        this.view = view;
    }

    public void Auth(String login, String password) {
        String url = String.format("https://messenger-rksi.herokuapp.com/login?login=%s&password=%s", login, password);

        new JSONTask(new AsyncResponse() {
            @Override
            public void processFinish(String output) {
                int userId;
                try {
                    userId = new JSONObject(output).getInt("userId");
                } catch(Exception e) {
                    userId = 0;
                }
                System.out.println(userId);

                Context context = view.getContext();
                String text = String.format("user id: %d", userId);
                int duration = Toast.LENGTH_SHORT;
                Toast.makeText(context, text, duration).show();
            }
        }).execute(url);
    }
}
