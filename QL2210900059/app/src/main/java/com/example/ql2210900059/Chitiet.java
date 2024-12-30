package com.example.ql2210900059;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Chitiet extends AppCompatActivity {
    Intent intent59;
    EditText edtMaSP59, edtTenSP59, edtSL59, edtDonGia59;
    Button btnquaylai59;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chitiet);

        // Set padding cho layout chính để tránh bị che bởi thanh trạng thái hệ thống
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addview();
        addevent();
    }

    private void addevent() {
        // Sự kiện khi người dùng nhấn nút "Quay lại"
        btnquaylai59.setOnClickListener(v -> finish());
    }

    private void addview() {
        intent59 = getIntent();  // Nhận dữ liệu từ Intent

        // Ánh xạ các View
        edtMaSP59 = findViewById(R.id.edtMaSP59);
        edtTenSP59 = findViewById(R.id.edtTenSP59);
        edtSL59 = findViewById(R.id.edtSL59);
        edtDonGia59 = findViewById(R.id.edtDonGia59);
        btnquaylai59 = findViewById(R.id.btnquaylai59);

        // Lấy sản phẩm từ Intent và hiển thị thông tin
        SanPham sp59 = (SanPham) intent59.getSerializableExtra("SanPham");
        edtMaSP59.setText(String.valueOf(sp59.getMaSanPham()));  // Hiển thị mã sản phẩm
        edtMaSP59.setEnabled(false);  // Không cho phép chỉnh sửa mã sản phẩm
        edtTenSP59.setText(sp59.getTenSanPham());  // Hiển thị tên sản phẩm
        edtSL59.setText(String.valueOf(sp59.getSoLuong()));  // Hiển thị số lượng
        edtDonGia59.setText(String.format("%.2f", sp59.getDonGia()));  // Hiển thị đơn giá
    }
}
