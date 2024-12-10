package com.example.test;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText edtA;
    EditText edtB;
    EditText edtKQ;
    Button btnTinh;
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
        //Ánh xạ view
        edtA=findViewById(R.id.edtA);
        edtB=findViewById(R.id.edtB);
        edtKQ=findViewById(R.id.edtKQ);
        btnTinh=findViewById(R.id.btnTinh);
        //thao tác cho người dùng
        btnTinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a= Integer.parseInt(edtA.getText().toString());//lấy dữ liệu từ edt a,ép sang kiểu int
                int b= Integer.parseInt(edtB.getText().toString());
                int c= a+b;
                edtKQ.setText(c+"");//gán c để hiêển thị kết qủa
            }
        });
    }
}