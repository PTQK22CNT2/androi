package com.example.lab05;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class ListviewArraylist extends AppCompatActivity {
    ListView lstMonhoc;
    ArrayList<String> arrListMonhoc;
    ArrayAdapter<String>adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listview_arraylist);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addView();
        addEvent();
    }

    private void addView() {
        lstMonhoc=findViewById(R.id.lstMonhoc);
        arrListMonhoc=new ArrayList<>();
        adapter=new ArrayAdapter<String>(ListviewArraylist.this, android.R.layout.simple_list_item_1,arrListMonhoc);

        lstMonhoc.setAdapter(adapter);
        arrListMonhoc.add("toán");
        arrListMonhoc.add("văn");
        arrListMonhoc.add("anh");
    }

    private void addEvent() {

       lstMonhoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

           }
       });
        lstMonhoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }
}