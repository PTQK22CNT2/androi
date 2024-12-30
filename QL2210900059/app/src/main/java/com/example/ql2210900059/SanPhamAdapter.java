package com.example.ql2210900059;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import java.text.DecimalFormat;

public class SanPhamAdapter extends ArrayAdapter<SanPham> {
    Activity context59;
    int resource59;
    public SanPhamAdapter(@NonNull Activity context59, int resource59) {
        super(context59, resource59);
        this.context59=context59;
        this.resource59=resource59;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        DecimalFormat df = new DecimalFormat("#.00");
        LayoutInflater inflater59 = context59.getLayoutInflater();
        View customview59 =inflater59.inflate(resource59,null);
        TextView txtTenSP59,txtMaSP59,txtSL59,txtDonGia59,txtThanhTien59;
        txtMaSP59=customview59.findViewById(R.id.txtMaSP59);
        txtTenSP59=customview59.findViewById(R.id.txtTenSP59);
        txtSL59=customview59.findViewById(R.id.txtSL59);
        txtDonGia59=customview59.findViewById(R.id.txtDonGia59);
        txtThanhTien59=customview59.findViewById(R.id.txtThanhTien59);
        SanPham sp59 = getItem(position);
        double donGia = sp59.getDonGia();
        int soLuong = sp59.getSoLuong();
        double thanhTien = soLuong * donGia;
        txtMaSP59.setText(sp59.getMaSanPham()+"");
        txtTenSP59.setText(sp59.getTenSanPham());
        txtDonGia59.setText(df.format(sp59.getDonGia()));
        txtSL59.setText(String.valueOf(soLuong));
        txtThanhTien59.setText(String.format("%.2f", thanhTien));
        return customview59;
    }
}
