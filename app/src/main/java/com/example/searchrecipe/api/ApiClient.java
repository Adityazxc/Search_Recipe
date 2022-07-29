package com.example.searchrecipe.api;

import android.os.StrictMode;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ApiClient {
//    private Button buttonSync;
//    public static OkHttpClient requestSync() {
//        int SDK_INT = android.os.Build.VERSION.SDK_INT;
//        if (SDK_INT > 8) {
//            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
//                    .permitAll().build();
//            StrictMode.setThreadPolicy(policy);
//        }
//        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        builder.connectTimeout(5, TimeUnit.SECONDS);
//        builder.readTimeout(5, TimeUnit.SECONDS);
//        builder.writeTimeout(5, TimeUnit.SECONDS);
//        OkHttpClient client = builder.build();
//        Request request = new Request.Builder()
//                .url("https://edamam-recipe-search.p.rapidapi.com/search?q=chicken")
//                .get()
//                .addHeader("X-RapidAPI-Key", "a3c49c7122mshe7e8ea32e4fb810p181213jsnbe2798adddc8")
//                .addHeader("X-RapidAPI-Host", "edamam-recipe-search.p.rapidapi.com")
//                .build();
//        try {
//            Response response = client.newCall(request).execute();
//            //response.body().string() tidak boleh dipanggil 2x
//            String responseString = response.body().string();
//            Toast.makeText(this, responseString, Toast.LENGTH_SHORT).show();
//        } catch (IOException e) {
//            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
//            e.printStackTrace();
//        }
//        Toast.makeText(this, "OkHTTP requestSync", Toast.LENGTH_SHORT).show();
//    }
}
