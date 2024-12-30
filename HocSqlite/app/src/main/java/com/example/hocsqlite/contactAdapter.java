package com.example.hocsqlite;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class contactAdapter  extends ArrayAdapter<Contact> {
    Activity context;
    int resource;
    public contactAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        this.context = (Activity) context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View customview =  inflater.inflate(resource,null);
        TextView txtma ,txtten,txtdienthoai;
        txtma=customview.findViewById(R.id.txtMa);
        txtma=customview.findViewById(R.id.txtTen);
        txtma=customview.findViewById(R.id.txtDienthoai);
        Contact ct =getItem(position);
        txtma.setText(ct.getMa()+"");
        txtma.setText(ct.getTen());
        txtma.setText(ct.getDienthoai());
        return customview;
    }
}
