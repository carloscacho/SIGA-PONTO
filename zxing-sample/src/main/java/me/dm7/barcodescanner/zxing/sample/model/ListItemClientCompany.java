package me.dm7.barcodescanner.zxing.sample.model;

/**
 * Created by CarlosEmilio on 26/09/2016.
 */

public class ListItemClientCompany {
    private String nameClientCompany;
    private String numberCT;
    private String vouchers;
    private String checkIn;


    public ListItemClientCompany(String nameClientCompany, String numberCT, String vouchers, String checkIn) {
        this.nameClientCompany = nameClientCompany;
        this.numberCT = numberCT;
        this.vouchers = vouchers;
        this.checkIn = checkIn;

    }

    public String getNameClientCompany() {
        return nameClientCompany;
    }

    public void setNameClientCompany(String nameClientCompany) {
        this.nameClientCompany = nameClientCompany;
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
