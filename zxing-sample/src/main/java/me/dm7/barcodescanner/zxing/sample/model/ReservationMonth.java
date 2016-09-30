package me.dm7.barcodescanner.zxing.sample.model;

import java.util.ArrayList;

/**
 * Created by CarlosEmilio on 28/09/2016.
 */

public class ReservationMonth {
    private int monthAndYear;
    private ArrayList<Voucher> reservedCourses;
    private int totalExtraVoucher;


    public ReservationMonth(int monthAndYear) {
        this.monthAndYear = monthAndYear;
        reservedCourses = new ArrayList<>();
    }

    public int getMonthAndYear() {
        return monthAndYear;
    }

    public void setMonthAndYear(int monthAndYear) {
        this.monthAndYear = monthAndYear;
    }

    public ArrayList<Voucher> getReservedCourses() {
        return reservedCourses;
    }

    public void setReservedCourses(ArrayList<Voucher> reservedCourses) {
        this.reservedCourses = reservedCourses;
    }

    public int getTotalExtraVoucher() {
        return totalExtraVoucher;
    }

    public void setTotalExtraVoucher(int totalExtraVoucher) {
        this.totalExtraVoucher = totalExtraVoucher;
        totalExtraVoucher++;
    }



}
