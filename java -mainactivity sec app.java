package com.example.sec_app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Button;
import android.app.AlertDialog;
import android.widget.EditText;
import android.content.Intent;
import android.net.Uri;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView listview;
    Button addbtn;
    ArrayList<String> listurl;
    ArrayAdapter<String> adapter;
    SharedPreferences sharedp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = findViewById(R.id.listv);
        addbtn = findViewById(R.id.addbtn);
        sharedp = getSharedPreferences("url_pref", MODE_PRIVATE);


        if (!sharedp.contains("urls")) {
            sharedp.edit().putString("urls",
                    "https://soft98.ir").apply();
        }


        String savedUrls = sharedp.getString("urls", "");
        listurl = new ArrayList<>(Arrays.asList(savedUrls.split(",")));


        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listurl);
        listview.setAdapter(adapter);


        listview.setOnItemClickListener((adapterView, view, position, id) -> show(position));


        addbtn.setOnClickListener(v -> Add());
    }


    private void Add() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("افزودن آدرس جدید");

        final EditText input = new EditText(this);
        input.setHint("مثلاً https://example.com");
        builder.setView(input);

        builder.setPositiveButton("افزودن", (dialog, which) -> {
            String newUrl = input.getText().toString().trim();
            if (!newUrl.isEmpty()) {
                listurl.add(newUrl);
                save();
                adapter.notifyDataSetChanged();
            }
        });

        builder.setNegativeButton("انصراف", null);
        builder.show();
    }


    private void show(int position) {
        String[] options = {"باز کردن", "ویرایش", "حذف"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setItems(options, (dialog, which) -> {
            switch (which) {
                case 0:
                    open(position);
                    break;
                case 1:
                    edit(position);
                    break;
                case 2:
                    delete(position);
                    break;
            }
        });
        builder.show();
    }


    private void open(int position) {
        String url = listurl.get(position);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }


    private void edit(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("ویرایش آدرس");

        final EditText input = new EditText(this);
        input.setText(listurl.get(position));
        builder.setView(input);

        builder.setPositiveButton("ذخیره", (dialog, which) -> {
            String editedUrl = input.getText().toString().trim();
            if (!editedUrl.isEmpty()) {
                listurl.set(position, editedUrl);
                save();
                adapter.notifyDataSetChanged();
            }
        });

        builder.setNegativeButton("انصراف", null);
        builder.show();
    }


    private void delete(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("حذف آدرس");
        builder.setMessage("آیا از حذف این آدرس مطمئن هستید؟");

        builder.setPositiveButton("بله", (dialog, which) -> {
            listurl.remove(position);
            save();
            adapter.notifyDataSetChanged();
        });

        builder.setNegativeButton("خیر", null);
        builder.show();
    }


    private void save() {
        StringBuilder sb = new StringBuilder();
        for (String url : listurl) {
            sb.append(url).append(",");
        }
        sharedp.edit().putString("urls", sb.toString()).apply();
    }
}
