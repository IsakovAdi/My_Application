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

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodHolder> {

    List<Food> foodList;
    Context context;
    public FoodAdapter(Context context, List<Food> foodList){
        this.foodList =foodList;
        this.context = context;
    }

    @NonNull
    @Override
    public FoodHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.food_item, parent , false);
        return new FoodHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodHolder holder, int position) {
        holder.imageView1.setImageResource(foodList.get(position).getFoodImage());
        holder.textView1.setText(foodList.get(position).getFoodName());
        holder.textView2.setText(foodList.get(position).getFoodDescription());
        holder.textView3.setText(foodList.get(position).getTemperature());
        holder.textView4.setText(foodList.get(position).getCost());
        holder.imageView2.setImageResource(foodList.get(position).getCoffeeIcon());
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public static class FoodHolder extends RecyclerView.ViewHolder{

        ImageView imageView1;
        TextView textView1;
        TextView textView2;
        TextView textView3;
        ImageView imageView2;
        TextView textView4;

        public FoodHolder(@NonNull View itemView) {
            super(itemView);

            imageView1 = itemView.findViewById(R.id.image);
            textView1 = itemView.findViewById(R.id.foodName);
            textView2 = itemView.findViewById(R.id.description);
            textView3 = itemView.findViewById(R.id.temperature);
            textView4 = itemView.findViewById(R.id.cost);
            imageView2 = itemView.findViewById(R.id.coffee_icon);

        }
    }

}
