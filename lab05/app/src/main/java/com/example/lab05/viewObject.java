package com.example.lab05;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.model.Mohoc;

public class viewObject extends AppCompatActivity {
    ListView lstmonhoc;
    ArrayAdapter<Mohoc>adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_object);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addView();
        addEvent();
    }

    private void addEvent() {
    }

    private void addView() {
        lstmonhoc=findViewById(R.id.lstMonhoc);
        adapter=new ArrayAdapter<>(viewObject.this,android.R.layout.activity_list_item);
        lstmonhoc.setAdapter(adapter);
        adapter.add(new Mohoc(1,"Toán",45));
        adapter.add(new Mohoc(2,"Văn",45));
        adapter.add(new Mohoc(3,"Anh",45));
        adapter.add(new Mohoc(4,"Lịch Sử",45));
    }
}