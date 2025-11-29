package com.example.myrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Switch layoutSwitch;
    ItemAdapter adapter;
    List<Item> itemList;
    boolean isGrid = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        layoutSwitch = findViewById(R.id.layoutSwitch);

        
        itemList = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            itemList.add(new Item(R.mipmap.ic_launcher, "آیتم " + i, i * 10));
        }

        adapter = new ItemAdapter(itemList);
        recyclerView.setAdapter(adapter);

        
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        layoutSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                recyclerView.setLayoutManager(new GridLayoutManager(this, 2)); // نمایش گرید
            } else {
                recyclerView.setLayoutManager(new LinearLayoutManager(this)); // نمایش خطی
            }
        });
    }
}
