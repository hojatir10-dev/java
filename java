package com.example.first_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView listv;
    SharedPreferences sharedp;
    ArrayList<String> websites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listv = findViewById(R.id.listview1);
        sharedp = getSharedPreferences("MyPrefs", MODE_PRIVATE);


        if (!sharedp.contains("sites")) {
            String defaultSites = "https://www.google.com,https://soft98.ir,https://medu.ir,https://www.yahoo.com,https://www.bing.com";
            sharedp.edit().putString("sites", defaultSites).apply();
        }


        String savedSites = sharedp.getString("sites", "");
        websites = new ArrayList<>(Arrays.asList(savedSites.split(",")));

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, websites);
        listv.setAdapter(adapter);


        listv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = websites.get(position);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });
    }
}
