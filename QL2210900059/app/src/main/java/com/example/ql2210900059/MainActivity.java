package com.example.ql2210900059;

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

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {
    String dbName59 = "QLsanpham.db";    // Đổi tên database theo yêu cầu
    String dbPath59 = "/databases/";    // Đổi tên path database
    SQLiteDatabase db59 = null;         // Database connection
    SanPhamAdapter adapter59;           // Adapter để hiển thị sản phẩm
    ListView lvSanPham59;               // ListView để hiển thị danh sách sản phẩm
    Button btnthem59;                   // Button để thêm sản phẩm mới
    SanPham sp59;                       // Đối tượng SanPham để thao tác
    int posUpdate59;                    // Vị trí sản phẩm cần cập nhật

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Set padding cho layout chính, để tránh bị che bởi các hệ thống thanh trạng thái
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Xử lý sao chép cơ sở dữ liệu
        xulycopy();

        // Khởi tạo các view
        addview();

        // Hiển thị dữ liệu sản phẩm từ database
        hienthisanpham();

        // Thiết lập sự kiện cho các nút và listview
        addevent();
    }

    private void addevent() {
        btnthem59 = findViewById(R.id.btnadd59);
        btnthem59.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mở Activity thêm/sửa sản phẩm mới
                Intent intent = new Intent(MainActivity.this, ThemSua.class);
                intent.putExtra("TRANGTHAI", "THEM");
                startActivityForResult(intent, 113);
            }
        });

        // Xử lý sự kiện khi người dùng chọn một sản phẩm từ ListView
        lvSanPham59.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                sp59 = adapter59.getItem(position);  // Lấy đối tượng sản phẩm
                posUpdate59 = position;  // Lưu vị trí sản phẩm cần cập nhật
                return false;
            }
        });
    }

    // Xử lý kết quả từ Activity thêm/sửa sản phẩm
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        SanPham spNew = (SanPham) data.getSerializableExtra("SANPHAM");

        // Nếu là thêm mới sản phẩm
        if (resultCode == 114 && requestCode == 113) {
            adapter59.add(spNew);
            try {
                ContentValues values = new ContentValues();
                values.put("MaSanPham", spNew.getMaSanPham());
                values.put("TenSanPham", spNew.getTenSanPham());
                values.put("SoLuong", spNew.getSoLuong());
                values.put("DonGia", spNew.getDonGia());

                if (db59.insert("SanPham59", null, values) > 0) {
                    Toast.makeText(MainActivity.this, "Thêm mới thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Thêm mới thất bại", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Log.e("Loi:", e.toString());
            }
        }

        // Nếu là cập nhật sản phẩm
        if (requestCode == 113 && resultCode == 115) {
            try {
                ContentValues values = new ContentValues();
                values.put("TenSanPham", spNew.getTenSanPham());
                values.put("SoLuong", spNew.getSoLuong());
                values.put("DonGia", spNew.getDonGia());

                db59.update("SanPham59", values, "MaSanPham=?", new String[]{spNew.getMaSanPham() + ""});
                adapter59.getItem(posUpdate59).setTenSanPham(spNew.getTenSanPham());
                adapter59.getItem(posUpdate59).setSoLuong(spNew.getSoLuong());
                adapter59.getItem(posUpdate59).setDonGia(spNew.getDonGia());
                adapter59.notifyDataSetChanged();
            } catch (Exception e) {
                Log.e("Loi:", e.toString());
            }
        }
    }

    // Hiển thị danh sách sản phẩm từ cơ sở dữ liệu
    private void hienthisanpham() {
        db59 = openOrCreateDatabase(dbName59, MODE_PRIVATE, null);
        Cursor cursor = db59.rawQuery("SELECT * FROM SanPham    ", null);
        while (cursor.moveToNext()) {
            int ma = cursor.getInt(0);
            String ten = cursor.getString(1);
            int soLuong = cursor.getInt(2);
            double donGia = cursor.getDouble(3);
            adapter59.add(new SanPham(ma, ten, soLuong, donGia));
        }
    }

    // Khởi tạo các view trong layout
    private void addview() {
        lvSanPham59 = findViewById(R.id.lstsanpham59);
        adapter59 = new SanPhamAdapter(MainActivity.this, R.layout.sanpham_item);
        lvSanPham59.setAdapter(adapter59);
        registerForContextMenu(lvSanPham59);
    }

    // Sao chép cơ sở dữ liệu nếu chưa có
    private void xulycopy() {
        try {
            File dbFile = getDatabasePath(dbName59);
            if (!dbFile.exists()) {
                copyDataFromAsset();
                Toast.makeText(MainActivity.this, "Sao chép thành công", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Log.e("Loi", e.toString());
        }
    }

    // Sao chép cơ sở dữ liệu từ asset
    private void copyDataFromAsset() {
        try {
            InputStream myInput = getAssets().open(dbName59);
            String outFileName = getApplicationInfo().dataDir + dbPath59 + dbName59;
            File f = new File(getApplicationInfo().dataDir + dbPath59);
            if (!f.exists()) f.mkdir();
            OutputStream myOutput = new FileOutputStream(outFileName);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (Exception ex) {
            Log.e("LOI", ex.toString());
        }
    }

    // Tạo menu context cho các item trong ListView
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mnuSua59) {
            Intent intent = new Intent(MainActivity.this, ThemSua.class);
            intent.putExtra("TRANGTHAI", "SUA");
            intent.putExtra("SANPHAM", sp59);
            startActivityForResult(intent, 113);
        }
        if (item.getItemId() == R.id.mnuChitiet59){
            Intent intent=new Intent(MainActivity.this, Chitiet.class);
            intent.putExtra("SanPham",sp59);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.mnuXoa59) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Xác nhận xóa");
            builder.setMessage("Bạn thật sự muốn xóa?");
            builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    db59.delete("SanPham59", "MaSanPham=?", new String[]{sp59.getMaSanPham() + ""});
                    adapter59.remove(sp59);
                    adapter59.notifyDataSetChanged();
                }
            });
            builder.setNegativeButton("Không", null);
            AlertDialog dialog = builder.create();
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context, menu);
    }
}
