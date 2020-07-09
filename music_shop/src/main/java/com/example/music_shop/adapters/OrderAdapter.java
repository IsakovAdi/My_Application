package com.example.music_shop.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.music_shop.R;
import com.example.music_shop.models.Order;

import java.util.List;

public class OrderAdapter extends ArrayAdapter<Order> {

    Context context;
    int resource;


    public OrderAdapter(@NonNull Context context, int resource, @NonNull List<Order> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String goodName = getItem(position).getGoodName();
        int goodQuantity = getItem(position).getGoodQuantity();
        double goodPrice = getItem(position).getGoodPrice();
        String goodUser = getItem(position).getUserName();

        LayoutInflater layoutInflater = LayoutInflater.from(context);

        convertView = layoutInflater.inflate(R.layout.list_item, parent, false);     // подразумевается как list_view
//        convertView = layoutInflater.inflate(resource, parent, false);     // подразумевается как list_view
        TextView tvGoodName = convertView.findViewById(R.id.tvGoodName);
        TextView tvGoodUser = convertView.findViewById(R.id.tvGoodUser);
        TextView tvGoodQuantity = convertView.findViewById(R.id.tvGoodQuantity);
        TextView tvGoodPrice = convertView.findViewById(R.id.tvGoodPrice);

        ImageView goodImage = convertView.findViewById(R.id.goodImage);

        tvGoodName.setText("товар: "+goodName);
        tvGoodQuantity.setText("количество: "+String.valueOf(goodQuantity));
        tvGoodPrice.setText("Цена: "+String.valueOf(goodPrice));
        tvGoodUser.setText("Заказчик: "+goodUser);

        switch (goodName){
            case "Guitar":
                goodImage.setImageResource(R.drawable.guitar);
                break;
            case "Keyboard":
                goodImage.setImageResource(R.drawable.keyboard);
                break;
            case "Rock":
                goodImage.setImageResource(R.drawable.rock);
                break;
            case "Drums":
                goodImage.setImageResource(R.drawable.drums);
                break;
        }
        return convertView;
    }
}
