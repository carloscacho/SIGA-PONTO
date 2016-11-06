package me.dm7.barcodescanner.zxing.sample.controller;

import android.annotation.TargetApi;
import android.os.Build;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by CarlosEmilio on 06/11/2016.
 */

public class PostPosition {

        public static final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        @TargetApi(Build.VERSION_CODES.KITKAT)
        String post(String url, String json) throws IOException {
            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                return response.body().string();
            }
        }

        String bowlingJson(String player1, String player2) {
            return "{'location':["
                    + "{'latitude':'" + player1 + "'},"
                    + "{'logitude':'" + player2 + "'}"
                    + "]}";
        }

        public  void postJason(String lat, String log) throws IOException {
            PostPosition example = new PostPosition();
            String json = example.bowlingJson(lat, log);
            String response = example.post("http://www.roundsapp.com/post", json);
            System.out.println(response);
        }

}
