package com.example.searchrecipe.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.searchrecipe.Adapter.InstructionAdapter;
import com.example.searchrecipe.Adapter.SimilarRecipeAdapter;
import com.example.searchrecipe.Adapter.ingredientsAdapter;
import com.example.searchrecipe.Listeners.InstructionListener;
import com.example.searchrecipe.Listeners.RecipeClickListener;
import com.example.searchrecipe.Listeners.RecipeDetailsListener;
import com.example.searchrecipe.Listeners.SimilarRecipeListener;
import com.example.searchrecipe.Models.InstructionResponse;
import com.example.searchrecipe.Models.RecipeDetailsResponse;
import com.example.searchrecipe.Models.SimilarRecipeResponse;
import com.example.searchrecipe.R;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;

import java.util.List;

public class RecipeDetailsActivity extends AppCompatActivity {
    int id;

    TextView textView_meal_name,textView_meal_source,textView_meal_summary;
    ImageView imageView_meal_image;
    RecyclerView recycler_meal_ingredients,recycler_meal_similar,recycler_meal_instruction;
    RequestManager manager;
    ProgressDialog dialog;
    ingredientsAdapter  ingredientsAdapter;
    SimilarRecipeAdapter similarRecipeAdapter;
    InstructionAdapter instructionAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        findViews();


        id=Integer.parseInt(getIntent().getStringExtra("id"));
        manager = new RequestManager(this);
        manager.getRecipeDetails(recipeDetailsListener,id);
        manager.getSimilarRecipe(similarRecipeListener, id);
        manager.getInstruction(instructionListener, id);
        dialog=new ProgressDialog(this);
        dialog.setTitle("Loading Details...");
        dialog.show();

    }
    private void findViews(){
        textView_meal_name = findViewById(R.id.textView_meal_name);
        textView_meal_source= findViewById(R.id.textView_meal_source);
        textView_meal_summary=findViewById(R.id.textView_meal_summary);
        imageView_meal_image=findViewById(R.id.imageView_meal_image);
        recycler_meal_ingredients=findViewById(R.id.recycler_meal_ingredients);
        recycler_meal_similar=findViewById(R.id.recycler_meal_similar);
        recycler_meal_instruction=findViewById(R.id.recycler_meal_instruction);

    }

    private final RecipeDetailsListener recipeDetailsListener=new RecipeDetailsListener() {
        @Override
        public void didFetch(RecipeDetailsResponse response, String message) {
            dialog.dismiss();
            textView_meal_name.setText(response.title);
            textView_meal_source.setText(response.sourceName);
            textView_meal_summary.setText(Jsoup.parse(response.summary).text());
            Picasso.get().load(response.image).into(imageView_meal_image);

            recycler_meal_ingredients.setHasFixedSize(true);
            recycler_meal_ingredients.setLayoutManager(new LinearLayoutManager(RecipeDetailsActivity.this, LinearLayoutManager.HORIZONTAL,false));
            ingredientsAdapter = new ingredientsAdapter(RecipeDetailsActivity.this,response.extendedIngredients);
            recycler_meal_ingredients.setAdapter(ingredientsAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(RecipeDetailsActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    private final SimilarRecipeListener similarRecipeListener = new SimilarRecipeListener() {
        @Override
        public void didFetch(List<SimilarRecipeResponse> response, String message) {
            recycler_meal_similar.setHasFixedSize(true);
            recycler_meal_similar.setLayoutManager(new LinearLayoutManager(RecipeDetailsActivity.this,LinearLayoutManager.HORIZONTAL,false));
            similarRecipeAdapter =new SimilarRecipeAdapter(RecipeDetailsActivity.this,response,recipeClickListener);
            recycler_meal_similar.setAdapter(similarRecipeAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(RecipeDetailsActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    private  final RecipeClickListener recipeClickListener= id -> startActivity(new Intent(RecipeDetailsActivity.this,RecipeDetailsActivity.class)
            .putExtra("id", id));

    private final InstructionListener instructionListener =new InstructionListener() {
        @Override
        public void didFetch(List<InstructionResponse> response, String message) {
            recycler_meal_instruction.setHasFixedSize(true);
            recycler_meal_instruction.setLayoutManager(new LinearLayoutManager(RecipeDetailsActivity.this, LinearLayoutManager.VERTICAL,false));
            instructionAdapter = new InstructionAdapter(RecipeDetailsActivity.this,response);
            recycler_meal_instruction.setAdapter(instructionAdapter);


        }

        @Override
        public void didError(String message) {

        }
    };
}