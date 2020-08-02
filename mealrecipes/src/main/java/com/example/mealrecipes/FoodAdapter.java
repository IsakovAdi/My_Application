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
    RecyclerOnClickListener listener;

    public void setOnItemClickListener(RecyclerOnClickListener listener){
        this.listener = listener;
    }
    public FoodAdapter(Context context, List<Food> foodList){
        this.foodList =foodList;
        this.context = context;
    }

    @NonNull
    @Override
    public FoodHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.food_item, parent , false);
        return new FoodHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodHolder holder, int position) {
        holder.imageView1.setImageResource(foodList.get(position).getFoodImage());
        holder.textView1.setText(foodList.get(position).getFoodName());
        holder.textView2.setText(foodList.get(position).getFoodDescription());
        holder.textView3.setText(foodList.get(position).getTemperature());
        holder.textView4.setText(foodList.get(position).getCost());

        if (foodList.get(position).getTemperature().equals("Hot")){
            holder.coffeeIcon.setImageResource(R.drawable.coffee_hot);
        }
        else {
            holder.coffeeIcon.setImageResource(R.drawable.coffee_cold);
        }

    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public static class FoodHolder extends RecyclerView.ViewHolder{

        ImageView imageView1, favoriteIcon, favoriteIconFull;
        TextView textView1;
        TextView textView2;
        TextView textView3;
        ImageView coffeeIcon;
        TextView textView4;

        public FoodHolder(@NonNull View itemView, final RecyclerOnClickListener listener) {
            super(itemView);

            imageView1 = itemView.findViewById(R.id.coffeeImage);
            textView1 = itemView.findViewById(R.id.foodName);
            textView2 = itemView.findViewById(R.id.description);
            textView3 = itemView.findViewById(R.id.temperature);
            textView4 = itemView.findViewById(R.id.cost);
            coffeeIcon = itemView.findViewById(R.id.coffeeIcon);
            favoriteIcon = itemView.findViewById(R.id.favoriteIcon);
            favoriteIconFull = itemView.findViewById(R.id.favoriteIconFull);

            favoriteIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    favoriteIcon.setVisibility(View.INVISIBLE);
                    favoriteIconFull.setVisibility(View.VISIBLE);
                    listener.onFavoriteClick(getAdapterPosition());
                    // добавление в избранные
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    listener.onClick(position);
                }
            });
        }
    }

    public interface RecyclerOnClickListener{
        void onClick(int position);
        void onFavoriteClick(int position);
    }

}
