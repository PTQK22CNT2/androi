package com.example.model;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.lab06_menu.R;

public class AdapterNhanVien extends ArrayAdapter<nhanvien> {
    private Activity context;
    private int resource;
    public AdapterNhanVien(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context=context;
        this.resource=resource;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View converView, @NonNull ViewGroup parent){
        View view=this.context.getLayoutInflater().inflate(this.resource,null);
        ImageView imgHinh=view.findViewById(R.id.imgHinh);
        TextView txtMa=view.findViewById(R.id.txtMa);
        TextView txtTen=view.findViewById(R.id.txtTen);
        nhanvien nv=getItem(position);
        if(nv.isNam())
            imgHinh.setImageResource(R.drawable.nam);
        else
            imgHinh.setImageResource(R.drawable.nu);
        txtMa.setText(nv.getMa());
        txtTen.setText(nv.getTen());
        return view;
    }
}
