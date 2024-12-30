package com.example.hocsqlite;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {
    String dbName = "ContactDB.db";
    String dbPath = "/databases/";
    SQLiteDatabase db = null;
    contactAdapter adapter;
    ListView lvContact;
    Button btnThem;
    Contact ct;
    int posUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xuLyCopy(); // Sao chép cơ sở dữ liệu từ thư mục assets nếu chưa tồn tại
        addView(); // Ánh xạ các thành phần giao diện và thiết lập adapter
        hienThiContact(); // Hiển thị danh sách liên hệ từ cơ sở dữ liệu
        addEvent(); // Gắn sự kiện cho các thành phần giao diện
    }

    // Phương thức ánh xạ giao diện và thiết lập adapter cho ListView
    private void addView() {
        lvContact = findViewById(R.id.lvContact); // Ánh xạ ListView
        adapter = new contactAdapter(MainActivity.this, R.layout.contactitem); // Tạo adapter
        lvContact.setAdapter(adapter); // Gắn adapter vào ListView
        btnThem = findViewById(R.id.btnThem); // Ánh xạ nút thêm liên hệ
        registerForContextMenu(lvContact); // Đăng ký menu ngữ cảnh cho ListView
    }

    // Phương thức gắn sự kiện cho các thành phần giao diện
    private void addEvent() {
        // Xử lý sự kiện bấm nút Thêm
        btnThem.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ThemSuaActivity.class);
            intent.putExtra("TRANGTHAI", "THEM"); // Gửi trạng thái thêm
            startActivityForResult(intent, 113); // Mở màn hình thêm mới
        });

        // Xử lý sự kiện khi người dùng chọn một mục trong danh sách
        lvContact.setOnItemClickListener((parent, view, position, id) -> {
            ct = adapter.getItem(position); // Lấy liên hệ được chọn
            posUpdate = position; // Lưu vị trí của liên hệ được chọn
        });
    }

    // Phương thức hiển thị danh sách liên hệ từ cơ sở dữ liệu
    private void hienThiContact() {
        db = openOrCreateDatabase(dbName, MODE_PRIVATE, null); // Mở kết nối cơ sở dữ liệu
        Cursor cursor = db.rawQuery("SELECT * FROM Contact", null); // Lấy dữ liệu từ bảng Contact
        adapter.clear(); // Xóa dữ liệu cũ trong adapter
        while (cursor.moveToNext()) {
            int ma = cursor.getInt(0); // Lấy mã liên hệ
            String ten = cursor.getString(1); // Lấy tên liên hệ
            String dienthoai = cursor.getString(2); // Lấy số điện thoại
            adapter.add(new Contact(ma, ten, dienthoai)); // Thêm vào adapter
        }
        cursor.close(); // Đóng con trỏ
    }

    // Phương thức kiểm tra và sao chép cơ sở dữ liệu từ thư mục assets
    private void xuLyCopy() {
        try {
            File dbFile = getDatabasePath(dbName); // Kiểm tra file cơ sở dữ liệu
            if (!dbFile.exists()) {
                copyDataFromAsset(); // Sao chép cơ sở dữ liệu nếu chưa tồn tại
                Toast.makeText(this, "Database copied successfully", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Log.e("Error", e.toString()); // Ghi log lỗi
        }
    }

    // Phương thức sao chép cơ sở dữ liệu từ thư mục assets vào thiết bị
    private void copyDataFromAsset() {
        try {
            InputStream myInput = getAssets().open(dbName); // Mở file trong thư mục assets
            String outFileName = getApplicationInfo().dataDir + dbPath + dbName; // Đường dẫn lưu file
            File f = new File(getApplicationInfo().dataDir + dbPath);
            if (!f.exists()) f.mkdir(); // Tạo thư mục nếu chưa tồn tại
            OutputStream myOutput = new FileOutputStream(outFileName); // Tạo luồng ghi file
            byte[] buffer = new byte[1024]; // Bộ đệm sao chép
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length); // Ghi dữ liệu vào file đích
            }
            myOutput.flush();
            myOutput.close(); // Đóng luồng ghi
            myInput.close(); // Đóng luồng đọc
        } catch (Exception ex) {
            Log.e("Error", ex.toString()); // Ghi log lỗi
        }
    }

    // Phương thức tạo menu ngữ cảnh cho ListView
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context, menu); // Hiển thị menu ngữ cảnh
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        ct = adapter.getItem(info.position); // Lấy liên hệ được chọn
    }


    // Xử lý sự kiện khi một mục trong menu ngữ cảnh được chọn
    // Nhận kết quả từ Activity khác (thêm hoặc sửa liên hệ)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 113) { // Kiểm tra mã yêu cầu và kết quả trả về
            Contact ctNew = (Contact) data.getSerializableExtra("CONTACT"); // Lấy đối tượng liên hệ mới

            try {
                ContentValues values = new ContentValues(); // Tạo đối tượng lưu trữ dữ liệu
                values.put("Ma", ctNew.getMa());
                values.put("Ten", ctNew.getTen());
                values.put("Dienthoai", ctNew.getDienthoai());

                if ("THEM".equals(data.getStringExtra("TRANGTHAI"))) { // Xử lý thêm mới liên hệ
                    if (db.insert("Contact", null, values) > 0) { // Thêm liên hệ vào cơ sở dữ liệu
                        adapter.add(ctNew); // Thêm liên hệ vào adapter
                        adapter.notifyDataSetChanged(); // Cập nhật giao diện
                        Toast.makeText(this, "Đã thêm thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Thêm không thành công", Toast.LENGTH_SHORT).show();
                    }
                } else if ("SUA".equals(data.getStringExtra("TRANGTHAI"))) { // Xử lý cập nhật liên hệ
                    db.update("Contact", values, "Ma=?", new String[]{String.valueOf(ctNew.getMa())}); // Cập nhật cơ sở dữ liệu
                    adapter.remove(adapter.getItem(posUpdate)); // Xóa liên hệ cũ khỏi adapter
                    adapter.insert(ctNew, posUpdate); // Thêm liên hệ mới vào vị trí cũ
                    adapter.notifyDataSetChanged(); // Cập nhật giao diện
                    Toast.makeText(this, "Đã cập nhật thành công", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Log.e("Lỗi", e.toString());
            }
        }
    }
}
