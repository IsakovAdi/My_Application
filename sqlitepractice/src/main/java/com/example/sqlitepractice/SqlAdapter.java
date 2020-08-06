package com.example.sqlitepractice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlitepractice.models.Car;

import java.util.List;

public class SqlAdapter extends RecyclerView.Adapter<SqlAdapter.Holder> {

    List<Car> carList;

    public SqlAdapter(List<Car> carList) {
        this.carList = carList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sql_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.title.setText(carList.get(position).getName());
        holder.desc.setText(carList.get(position).getPrice());

    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView title, desc;
        public Holder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_title);
            desc = itemView.findViewById(R.id.item_desc);
        }
    }
}
