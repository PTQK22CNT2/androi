package com.example.ql2210900059;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ThemSua extends AppCompatActivity {

    Intent intent59;  // Intent nhận dữ liệu
    EditText edtMaSP59, edtTenSP59, edtSL59, edtDonGia59;
    Button btnaddsua59, btnthoat59;
    String trangthai59;  // Đánh dấu cho biết thêm mới hay sửa
    TextView title59;  // Title để hiển thị "Thêm sản phẩm" hoặc "Sửa sản phẩm"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_themsua);  // Gọi layout

        // Set padding cho layout chính, tránh bị che bởi thanh trạng thái hệ thống
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addview();
        addevent();
    }

    private void addview() {
        intent59 = getIntent();  // Nhận dữ liệu từ Intent
        trangthai59 = intent59.getStringExtra("TRANGTHAI");  // Lấy trạng thái (thêm hay sửa)

        // Ánh xạ các View
        title59 = findViewById(R.id.title59);
        edtMaSP59 = findViewById(R.id.edtMaSP59);
        edtTenSP59 = findViewById(R.id.edtTenSP59);
        edtSL59 = findViewById(R.id.edtSL59);
        edtDonGia59 = findViewById(R.id.edtDonGia59);
        btnaddsua59 = findViewById(R.id.btnaddsua59);
        btnthoat59 = findViewById(R.id.btnthoat59);

        // Kiểm tra trạng thái (THÊM hoặc SỬA) để thay đổi giao diện
        if ("THEM".equals(trangthai59)) {
            title59.setText("Thêm sản phẩm");
            btnaddsua59.setText("Thêm");
        } else {
            title59.setText("Sửa sản phẩm");
            btnaddsua59.setText("Sửa");

            // Lấy sản phẩm cần sửa và hiển thị thông tin
            SanPham sp = (SanPham) intent59.getSerializableExtra("SANPHAM");
            edtMaSP59.setText(String.valueOf(sp.getMaSanPham()));  // Hiển thị mã sản phẩm
            edtMaSP59.setEnabled(false);  // Không cho phép chỉnh sửa mã sản phẩm
            edtTenSP59.setText(sp.getTenSanPham());  // Hiển thị tên sản phẩm
            edtSL59.setText(String.valueOf(sp.getSoLuong()));  // Hiển thị số lượng
            edtDonGia59.setText(String.valueOf(sp.getDonGia()));  // Hiển thị đơn giá
        }
    }

    private void addevent() {
        // Sự kiện khi người dùng nhấn "Thêm" hoặc "Sửa"
        btnaddsua59.setOnClickListener(v -> {
            // Lấy thông tin sản phẩm từ các EditText
            String maSP = edtMaSP59.getText().toString();
            String tenSP = edtTenSP59.getText().toString();
            String slSP = edtSL59.getText().toString();
            String donGia = edtDonGia59.getText().toString();

            // Kiểm tra thông tin nhập vào và tạo đối tượng SanPham mới
            if (!maSP.isEmpty() && !tenSP.isEmpty() && !slSP.isEmpty() && !donGia.isEmpty()) {
                SanPham sp = new SanPham();
                sp.setMaSanPham(Integer.parseInt(maSP));
                sp.setTenSanPham(tenSP);
                sp.setSoLuong(Integer.parseInt(slSP));
                sp.setDonGia(Double.parseDouble(donGia));

                // Nếu trạng thái là "THEM", gửi đối tượng sản phẩm mới
                if ("THEM".equals(trangthai59)) {
                    intent59.putExtra("SANPHAM", sp);
                    setResult(114, intent59);  // Trả kết quả là 114
                }
                // Nếu trạng thái là "SỬA", cập nhật sản phẩm
                else {
                    intent59.putExtra("SANPHAM", sp);
                    setResult(115, intent59);  // Trả kết quả là 115
                }

                finish();  // Đóng Activity
            } else {
                // Nếu có trường thông tin không hợp lệ, thông báo lỗi
                Toast.makeText(ThemSua.this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            }
        });

        // Sự kiện khi người dùng nhấn "Thoát"
        btnthoat59.setOnClickListener(v -> {
            finish();  // Đóng Activity và quay lại màn hình trước
        });
    }
}
