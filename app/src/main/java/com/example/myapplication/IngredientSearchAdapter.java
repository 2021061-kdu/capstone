package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class IngredientSearchAdapter extends RecyclerView.Adapter<IngredientSearchAdapter.ViewHolder> {

    private List<Ingredient> fullList;
    private List<Ingredient> filteredList;
    private Context context;

    public IngredientSearchAdapter(List<Ingredient> ingredientList, Context context) {
        this.fullList = new ArrayList<>(ingredientList);
        this.filteredList = new ArrayList<>(ingredientList);
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.ingredient_name);
            image = itemView.findViewById(R.id.ingredient_image);
        }
    }

    @Override
    public IngredientSearchAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_search_result, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Ingredient item = filteredList.get(position);
        holder.name.setText(item.getName());
        holder.image.setImageResource(item.getImageResId());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, AddDetailActivity.class);
            intent.putExtra("ingredient", item);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    public void filter(String keyword) {
        filteredList.clear();
        if (keyword.isEmpty()) {
            filteredList.addAll(fullList);
        } else {
            for (Ingredient ing : fullList) {
                if (ing.getName().toLowerCase().contains(keyword.toLowerCase())) {
                    filteredList.add(ing);
                }
            }
        }
        notifyDataSetChanged();
    }
}