package com.example.lab062;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.model.sanpham;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lsvSanPham;
    ArrayAdapter<sanpham>adtSanpham;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addview();
        addevent();
    }

    private void addevent() {
        lsvSanPham.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                sanpham sp=adtSanpham.getItem(i);
                Intent intent =new Intent(MainActivity.this,activity_Chi_Tiet.class);
                intent.putExtra("SANPHAM",sp);
                startActivityForResult(intent,113);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==113 &&resultCode==115)
        {
            sanpham sp= (sanpham)
                    data.getSerializableExtra("SANPHAM");
            for(int i=0;i<adtSanpham.getCount();i++)
            {
                if(sp.getMa().equals(adtSanpham.getItem(i).getMa()))
                {
                    adtSanpham.remove(adtSanpham.getItem(i));
                    break;
                }
            }
        }
    }

    private void addview() {
        lsvSanPham=findViewById(R.id.lstSanPham);
        adtSanpham =new ArrayAdapter<sanpham>(MainActivity.this, android.R.layout.simple_list_item_1);
        lsvSanPham.setAdapter(adtSanpham);
        adtSanpham.add(new sanpham("001","sach titan",1000000));
        adtSanpham.add(new sanpham("002","sach SAO",1000000));
        adtSanpham.add(new sanpham("003","sach michos",1000000));

    }
}