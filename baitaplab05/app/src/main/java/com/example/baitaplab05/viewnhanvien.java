package com.example.baitaplab05;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class viewnhanvien extends AppCompatActivity {

    EditText edtMaNV, edtHoTen, edtSoDienThoai;
    Button btnThemMoi;
    ListView lvNhanVien;
    ArrayList<String> danhSachNhanVien;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewnhanvien);

        edtMaNV = findViewById(R.id.edtMaNV);
        edtHoTen = findViewById(R.id.edtHoTen);
        edtSoDienThoai = findViewById(R.id.edtSoDienThoai);
        btnThemMoi = findViewById(R.id.btnThemMoi);
        lvNhanVien = findViewById(R.id.lvNhanVien);

        danhSachNhanVien = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, danhSachNhanVien);
        lvNhanVien.setAdapter(adapter);

        btnThemMoi.setOnClickListener(view -> {
            String maNV = edtMaNV.getText().toString();
            String hoTen = edtHoTen.getText().toString();
            String soDienThoai = edtSoDienThoai.getText().toString();

            if (maNV.isEmpty() || hoTen.isEmpty() || soDienThoai.isEmpty()) {
                Toast.makeText(viewnhanvien.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            } else {
                danhSachNhanVien.add(maNV + " - " + hoTen + " - " + soDienThoai);
                adapter.notifyDataSetChanged();
                edtMaNV.setText("");
                edtHoTen.setText("");
                edtSoDienThoai.setText("");
                Toast.makeText(viewnhanvien.this, "Đã thêm nhân viên", Toast.LENGTH_SHORT).show();
            }
        });

        lvNhanVien.setOnItemClickListener((adapterView, view, i, l) -> {
            String[] info = danhSachNhanVien.get(i).split(" - ");
            edtMaNV.setText(info[0]);
            edtHoTen.setText(info[1]);
            edtSoDienThoai.setText(info[2]);
        });

        lvNhanVien.setOnItemLongClickListener((adapterView, view, i, l) -> {
            danhSachNhanVien.remove(i);
            adapter.notifyDataSetChanged();
            Toast.makeText(viewnhanvien.this, "Đã xóa nhân viên", Toast.LENGTH_SHORT).show();
            return true;
        });
    }
}
