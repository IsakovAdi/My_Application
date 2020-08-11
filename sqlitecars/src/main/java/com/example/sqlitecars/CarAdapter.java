package com.example.sqlitecars;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.Holder> {

    List<Car> carList;
    RecyclerOnClickListener listener;

    public void setOnItemClickListener(RecyclerOnClickListener listener){
        this.listener = listener;
    }

    public CarAdapter(List<Car> carList) {
        this.carList = carList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_item, parent, false);
        return new Holder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
       holder.title.setText(carList.get(position).getName());
       holder.price.setText(carList.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        TextView title, price;
        public Holder(@NonNull View itemView, final RecyclerOnClickListener listener) {
            super(itemView);
            title = itemView.findViewById(R.id.carName);
            price = itemView.findViewById(R.id.carPrice);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("TAG", String.valueOf(getAdapterPosition()));
                    listener.onClick(getAdapterPosition());
                }
            });
        }
    }

    public interface RecyclerOnClickListener{
        void onClick(int position);
    }
}
