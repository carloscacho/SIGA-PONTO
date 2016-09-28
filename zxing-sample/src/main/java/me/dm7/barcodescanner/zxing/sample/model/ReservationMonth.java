package me.dm7.barcodescanner.zxing.sample.model;

import java.util.ArrayList;

/**
 * Created by CarlosEmilio on 28/09/2016.
 */

public class ReservationMonth {
    private int month;
    private ArrayList<Voucher> reservedCourses;
    private int totalExtraVoucher;


    public ReservationMonth(int month) {
        this.month = month;
        reservedCourses = new ArrayList<>();
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
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
    }


}
