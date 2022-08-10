package com.example.searchrecipe.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.searchrecipe.Models.InstructionResponse;
import com.example.searchrecipe.R;

import java.util.ConcurrentModificationException;
import java.util.List;

public class InstructionAdapter extends RecyclerView.Adapter<InstructionsViewHolder> {

    Context context;
    List<InstructionResponse> list;

    public InstructionAdapter(Context context, List<InstructionResponse> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InstructionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionsViewHolder(LayoutInflater.from(context).inflate(R.layout.list_instruction,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull InstructionsViewHolder holder, int position) {
        holder.textView_instruction_name.setText(list.get(position).name);
        holder.recycler_instruction_step.setHasFixedSize(true);
        holder.recycler_instruction_step.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        InstructionStepAdapter stepAdapter= new InstructionStepAdapter(context, list.get(position).steps);
        holder.recycler_instruction_step.setAdapter(stepAdapter);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class InstructionsViewHolder extends RecyclerView.ViewHolder {
    TextView textView_instruction_name;
    RecyclerView recycler_instruction_step;
    public InstructionsViewHolder(@NonNull View itemView) {
        super(itemView);
        textView_instruction_name=itemView.findViewById(R.id.textView_instruction_name);
        recycler_instruction_step=itemView.findViewById(R.id.recycler_instruction_step);
    }
}