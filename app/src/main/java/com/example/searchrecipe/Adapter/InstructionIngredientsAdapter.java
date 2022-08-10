package com.example.searchrecipe.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.searchrecipe.Models.Ingredient;
import com.example.searchrecipe.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class InstructionIngredientsAdapter extends RecyclerView.Adapter<InstructionIngredientsViewHolder>{

    Context context;
    List<Ingredient> list;
    public InstructionIngredientsAdapter(Context context, List<Ingredient> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InstructionIngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionIngredientsViewHolder(LayoutInflater.from(context).inflate(R.layout.list_instruction_step_items,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull InstructionIngredientsViewHolder holder, int position) {
        holder.textView_instruction_step_item.setText(list.get(position).name);
        holder.textView_instruction_step_item.setSelected(true);
        Picasso.get().load("https://spoonacular.com/cdn/ingredients_100x100/"+list.get(position).image).into(holder.imageView_instruction_step_item);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class InstructionIngredientsViewHolder extends RecyclerView.ViewHolder{
    ImageView imageView_instruction_step_item;
    TextView textView_instruction_step_item;
    public InstructionIngredientsViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView_instruction_step_item=itemView.findViewById(R.id.imageView_instruction_step_item);
        textView_instruction_step_item=itemView.findViewById(R.id.textView_instruction_step_item);
    }
}