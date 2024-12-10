package com.example.lab062;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.model.sanpham;

public class activity_Chi_Tiet extends AppCompatActivity {
    EditText edtMa,edtTen,edtGia;
    Button btnXoa,btnThoat;
    Intent intent;
    sanpham sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chi_tiet);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addview();
        addevent();
    }

    private void addevent() {
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("SANPHAM",sp);
                setResult(115,intent);
                finish();
            }
        });
    }

    private void addview() {
        edtMa=findViewById(R.id.edtMasp);
        edtTen=findViewById(R.id.edtTensp);
        edtGia=findViewById(R.id.edtGiasp);
        btnXoa=findViewById(R.id.btnxoasp);btnThoat=findViewById(R.id.btntrove);
        intent=getIntent();
        sp= (sanpham)
                intent.getSerializableExtra("SANPHAM");
        edtMa.setText(sp.getMa());
        edtTen.setText(sp.getTen());
        edtGia.setText(sp.getGia()+"");
    }
}