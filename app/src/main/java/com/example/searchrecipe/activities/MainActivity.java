package com.example.searchrecipe.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.searchrecipe.Adapter.RandomRecipeAdapter;
import com.example.searchrecipe.Listeners.RandomRecipeResponseListener;
import com.example.searchrecipe.R;
import com.example.searchrecipe.Models.RandomRecipeApiResponse;


public class MainActivity extends AppCompatActivity {

    ProgressDialog dialog;
    RequestManager manager;
    RandomRecipeAdapter randomRecipeAdapeter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialog=new ProgressDialog(this);
        dialog.setTitle("Loading....");

        manager=new RequestManager(this);
        manager.getRandomRecipes(randomRecipeResponseListener);
        dialog.show();


    }


    private final RandomRecipeResponseListener randomRecipeResponseListener= new RandomRecipeResponseListener() {
        @Override
        public void didFetch(RandomRecipeApiResponse response, String message) {
            recyclerView=findViewById(R.id.recycler_random);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,1));
            randomRecipeAdapeter = new RandomRecipeAdapter(MainActivity.this,response.recipes);
            recyclerView.setAdapter(randomRecipeAdapeter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(MainActivity.this,message, Toast.LENGTH_SHORT).show();
        }
    };











}