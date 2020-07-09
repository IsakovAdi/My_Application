package com.example.music_shop;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.music_shop.adapters.OrderAdapter;
import com.example.music_shop.models.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {

    List<Order> orderList = new ArrayList<>();
    public static final String TAG = "SecondTag";
    ListView orderView;
    TextView emptyCart;
    private int status;
    ImageView emptyImage;
    Button proceedBtn;
    
    String[] addresses = {"abuhanifa1199@gmail.com", "isakov.adilet@mail.ru"};
    String subject = "Music shop. Android Testing";
    List<Order> messageList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        orderView = findViewById(R.id.orderView);
        emptyCart = findViewById(R.id.emptyCart);
        emptyImage = findViewById(R.id.emptyImage);
        proceedBtn = findViewById(R.id.proceed);

        Intent intent = getIntent();
        status = intent.getIntExtra("status", 0);

        double counter = 0;

        final StringBuilder message = new StringBuilder();

        messageList = (List<Order>) intent.getSerializableExtra("orders");

        for (Order order:messageList) {
            counter = counter + order.getGoodPrice();
            message.append("Customer name: ").append(order.getUserName()).append("\n").append("Goods name: ").append(order.getGoodName()).append("\n").append("Quantity: ").append(order.getGoodQuantity()).append("\n").append("Order price: ").append(order.getGoodPrice()).append("\n\n");
        }
        message.append("\n\n Итоговая цена: " + counter);

        proceedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                intent.putExtra(Intent.EXTRA_EMAIL, addresses);
                intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                intent.putExtra(Intent.EXTRA_TEXT, String.valueOf(message));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }

            }
        });



        if (status == 1) {

                orderList = (List<Order>) intent.getSerializableExtra("orders");
                OrderAdapter adapter = new OrderAdapter(this, R.layout.list_item, orderList);
                orderView.setAdapter(adapter);
            }
        else {
            orderView.setVisibility(View.GONE);
            emptyCart.setVisibility(View.VISIBLE);
            emptyImage.setVisibility(View.VISIBLE);
            proceedBtn.setVisibility(View.GONE);
        }
    }
}