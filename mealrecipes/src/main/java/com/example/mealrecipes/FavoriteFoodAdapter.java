package com.example.mealrecipes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FavoriteFoodAdapter extends RecyclerView.Adapter<FavoriteFoodAdapter.FavoriteFoodHolder> {

    List<Food> favoriteFoodList;
    Context context;
    OnClickListener listener;

    public void setOnItemClickListener(OnClickListener listener){
        this.listener = listener;
    }

    public FavoriteFoodAdapter(Context context, List<Food> favoriteFoodList){
        this.context = context;
        this.favoriteFoodList = favoriteFoodList;
    }
    @NonNull
    @Override
    public FavoriteFoodHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.favorite_food_item, parent, false);
        return new FavoriteFoodHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteFoodHolder holder, int position) {
        holder.foodImage.setImageResource(favoriteFoodList.get(position).getFoodImage());
        holder.foodName.setText(favoriteFoodList.get(position).getFoodName());
        holder.foodDescription.setText(favoriteFoodList.get(position).getFoodDescription());
        holder.temperature.setText(favoriteFoodList.get(position).getTemperature());
        holder.cost.setText(favoriteFoodList.get(position).getCost());

        if (favoriteFoodList.get(position).getTemperature().equals("Hot")){
            holder.coffeeIcon.setImageResource(R.drawable.coffee_hot);
        }
        else {
            holder.coffeeIcon.setImageResource(R.drawable.coffee_cold);
        }
    }

    @Override
    public int getItemCount() {
        return favoriteFoodList.size();
    }

    public static class FavoriteFoodHolder extends RecyclerView.ViewHolder {

        ImageView foodImage;
        TextView foodName;
        TextView foodDescription;
        ImageView coffeeIcon;
        TextView temperature;
        TextView cost;
        ImageView deleteIcon;
        public FavoriteFoodHolder(@NonNull View itemView, final OnClickListener listener) {
            super(itemView);

            foodImage = itemView.findViewById(R.id.coffeeImage);
            foodName = itemView.findViewById(R.id.foodName);
            foodDescription = itemView.findViewById(R.id.description);
            coffeeIcon = itemView.findViewById(R.id.coffeeIcon);
            temperature = itemView.findViewById(R.id.temperature);
            cost = itemView.findViewById(R.id.cost);
            deleteIcon = itemView.findViewById(R.id.deleteIcon);

            deleteIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onDeleteClick(getAdapterPosition());
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(getAdapterPosition());
                }
            });
        }
    }

    public interface OnClickListener{
        void onClick(int position);
        void onDeleteClick(int position);
    }
}
