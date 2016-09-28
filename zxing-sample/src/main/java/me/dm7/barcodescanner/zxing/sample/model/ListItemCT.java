package me.dm7.barcodescanner.zxing.sample.model;

/**
 * Created by CarlosEmilio on 26/09/2016.
 */

public class ListItemCT {
    private String nameCT;
    private String numberCT;
    private String vouchers;
    private String checkIn;


    public ListItemCT(String nameCT, String numberCT, String vouchers, String checkIn) {
        this.nameCT = nameCT;
        this.numberCT = numberCT;
        this.vouchers = vouchers;
        this.checkIn = checkIn;

    }

    public String getNameCT() {
        return nameCT;
    }

    public void setNameCT(String nameCT) {
        this.nameCT = nameCT;
    }

    public String getNumberCT() {
        return numberCT;
    }

    public void setNumberCT(String numberCT) {
        this.numberCT = numberCT;
    }

    public String getVouchers() {
        return vouchers;
    }

    public void setVouchers(String vouchers) {
        this.vouchers = vouchers;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }
}
