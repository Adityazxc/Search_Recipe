package com.example.searchrecipe.activities;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.searchrecipe.Listeners.RandomRecipeResponseListener;
import com.example.searchrecipe.Listeners.RecipeDetailsListener;
import com.example.searchrecipe.Listeners.SimilarRecipeListener;
import com.example.searchrecipe.Models.RecipeDetailsResponse;
import com.example.searchrecipe.Models.SimilarRecipeResponse;
import com.example.searchrecipe.R;
import com.example.searchrecipe.Models.RandomRecipeApiResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
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

    public void getRandomRecipes(RandomRecipeResponseListener listener,List<String> tags){
        callRandomRecipes callRandomRecipes = retrofit.create(callRandomRecipes.class);
        Call<RandomRecipeApiResponse> call = callRandomRecipes.callRandomRecipe(context.getString(R.string.api_key),"10",tags);
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
    public void getRecipeDetails(RecipeDetailsListener listener,int id){
        CallRecipeDetails callRecipeDetails= retrofit.create(CallRecipeDetails.class);
        Call<RecipeDetailsResponse> call=callRecipeDetails.callRecipeDetails(id, context.getString(R.string.api_key));
        call.enqueue(new Callback<RecipeDetailsResponse>() {
            @Override
            public void onResponse(@NonNull Call<RecipeDetailsResponse> call, @NonNull Response<RecipeDetailsResponse> response) {
                if(!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(),response.message());
            }

            @Override
            public void onFailure(@NonNull Call<RecipeDetailsResponse> call, @NonNull Throwable t) {
                listener.didError(t.getMessage());

            }
        });

    }



    private interface callRandomRecipes{
                    @GET("recipes/random")
            Call<RandomRecipeApiResponse>callRandomRecipe(
                            @Query("apiKey") String apiKey,
                            @Query("number") String number,
                            @Query("tags")List<String> tags
                            );
    }
    public  void  getSimilarRecipe(SimilarRecipeListener listener, int id){
        CallSimilarRecipes callSimilarRecipes=retrofit.create(CallSimilarRecipes.class);
        Call<List<SimilarRecipeResponse>> call= callSimilarRecipes.callSimilarRecipe(id, "4", context.getString(R.string.api_key));
        call.enqueue(new Callback<List<SimilarRecipeResponse>>() {
            @Override
            public void onResponse(@NonNull Call<List<SimilarRecipeResponse>> call, @NonNull Response<List<SimilarRecipeResponse>> response) {
                if(!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(),response.message());
            }

            @Override
            public void onFailure(@NonNull Call<List<SimilarRecipeResponse>> call, @NonNull Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    private interface CallRecipeDetails{
        @GET("recipes/{id}/information")
        Call<RecipeDetailsResponse>callRecipeDetails(
                @Path("id")int id,
                @Query("apiKey")String apiKey
        );
    }

    private interface CallSimilarRecipes{
        @GET("recipes/{id}/similar")
        Call<List<SimilarRecipeResponse>>callSimilarRecipe(
            @Path("id")int id,
            @Query("number")String number,
            @Query("apiKey")String apiKey
        );
    }
}
