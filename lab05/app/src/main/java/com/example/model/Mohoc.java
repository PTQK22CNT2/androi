package com.example.model;

import androidx.annotation.NonNull;

public class Mohoc {
    private  int mamh;
    private  String tenmh;
    private  int sotiet;

    public Mohoc(int mamh, String tenmh, int sotiet) {
        this.mamh = mamh;
        this.tenmh = tenmh;
        this.sotiet = sotiet;
    }



    public void setMamh(int mamh) {
        this.mamh = mamh;
    }

    public void setTenmh(String tenmh) {
        this.tenmh = tenmh;
    }

    public void setSotiet(int sotiet) {
        this.sotiet = sotiet;
    }
    @NonNull
    @Override
    public  String toString(){
        return  mamh+""+tenmh+""+sotiet;
    }
}
