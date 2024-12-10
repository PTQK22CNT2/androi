package com.example.lab05;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ListviewArray extends AppCompatActivity {
    ListView lstMonhoc;
    String[] arrMonhoc;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listview_array);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addView();
        addEvent();
    }

    private void addEvent() {
        lstMonhoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ListviewArray.this,arrMonhoc[1],Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addView() {
        lstMonhoc=findViewById(R.id.lstMonhoc);
        arrMonhoc=new String[]{"toán","văn","anh"};
        adapter=new ArrayAdapter<>(ListviewArray.this,
                android.R.layout.simple_list_item_1,arrMonhoc);
        lstMonhoc.setAdapter(adapter);
    }
}/*
*/