package com.example.ql2210900059;

import java.io.Serializable;

public class SanPham implements Serializable {
    private int MaSanPham;
    private String TenSanPham;
    private int SoLuong;
    private double DonGia;

    public SanPham(){

    }

    // Constructor
    public SanPham(int maSanPham, String tenSanPham, int soLuong, double donGia) {
        MaSanPham = maSanPham;
        TenSanPham = tenSanPham;
        SoLuong = soLuong;
        DonGia = donGia;
    }

    // Getters and Setters
    public int getMaSanPham() {
        return MaSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        MaSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return TenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        TenSanPham = tenSanPham;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }

    public double getDonGia() {
        return DonGia;
    }

    public void setDonGia(double donGia) {
        DonGia = donGia;
    }

    @Override
    public String toString() {
        return "Mã: " + MaSanPham + ", Tên: " + TenSanPham + ", Số lượng: " + SoLuong + ", Đơn giá: " + DonGia;
    }
}
