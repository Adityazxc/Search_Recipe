package com.example.searchrecipe.activities;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.searchrecipe.Listeners.RandomRecipeResponseListener;
import com.example.searchrecipe.R;
import com.example.searchrecipe.Models.RandomRecipeApiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;


public class RequestManager {
    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    public RequestManager(Context context) {
        this.context = context;
    }

    public void getRandomRecipes(RandomRecipeResponseListener listener){
        callRandomRecipes callRandomRecipes = retrofit.create(callRandomRecipes.class);
        Call<RandomRecipeApiResponse> call = callRandomRecipes.callRandomRecipe(context.getString(R.string.api_key),"10");
        call.enqueue(new Callback<RandomRecipeApiResponse>() {
            @Override
            public void onResponse(@NonNull Call<RandomRecipeApiResponse> call, @NonNull Response<RandomRecipeApiResponse> response) {
                if(!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(),response.message());
            }

            @Override
            public void onFailure(@NonNull Call<RandomRecipeApiResponse> call, @NonNull Throwable t) {
                listener.didError(t.getMessage());

            }
        });
    }
    private interface callRandomRecipes{
                    @GET("recipes/random")
            Call<RandomRecipeApiResponse>callRandomRecipe(
                    @Query("apiKey") String apiKey,
                    @Query("number") String number
            );
    }
}