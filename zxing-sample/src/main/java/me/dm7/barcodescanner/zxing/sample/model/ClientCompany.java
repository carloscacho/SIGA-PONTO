package me.dm7.barcodescanner.zxing.sample.model;

import java.util.ArrayList;

/**
 * Created by CarlosEmilio on 26/09/2016.
 */

public class ClientCompany {
    private String companyName;
    private int companyCT;
    private ArrayList<Voucher> listOfVoucher;
    private int totalExtraVoucher;

    /**
     * Constructor of company CT
     *
     * @param companyName constructor name
     * @param CT          number max of voucher por mouth
     */
    private ClientCompany(String companyName, int CT) {
        this.companyName = companyName;
        this.companyCT = CT;

        listOfVoucher = new ArrayList<>();
        totalExtraVoucher = 0;
    }

    private boolean loadVoucher(long numberVoucher) {

        Voucher voucher = new Voucher(numberVoucher, false);
        if (listOfVoucher.size() - totalExtraVoucher < companyCT)
            listOfVoucher.add(voucher);
        else
            return false;
        return true;

    }

    /**
     * clear List of vouchers loaded in the mouth
     */
    private void clearListVoucher() {
        listOfVoucher.clear();
        totalExtraVoucher--;
    }

    /**
     * Check-in in list Voucher
     *
     * @param numberVoucher are the check-in
     * @return true in case voucher localize, false case else
     */
    private boolean checkinVoucher(long numberVoucher) {
        //the same voucher change true value
        Voucher tempVoucher = new Voucher(numberVoucher, true);
        for (int i = 0; i < listOfVoucher.size(); i++) {
            if (listOfVoucher.get(i).getNumVoucher() == numberVoucher) {
                // case localize voucher
                listOfVoucher.remove(i);
                listOfVoucher.add(tempVoucher);
                return true;

            }
        }
        // case not localize
        return false;

    }

    /**
     * Includ extra voucher for the business
     *
     * @param numberVoucher of the extra voucher
     */
    private void includExtraVoucher(long numberVoucher) {
        Voucher voucherTemp = new Voucher(numberVoucher, false);
        voucherTemp.setExtra(true);
        listOfVoucher.add(voucherTemp);
        totalExtraVoucher++;

    }


    //Gets And Sets
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getCompanyCT() {
        return companyCT;
    }

    public void setCompanyCT(int companyCT) {
        this.companyCT = companyCT;
    }

    public ArrayList<Voucher> getListOfVoucher() {
        return listOfVoucher;
    }

    public void setListOfVoucher(ArrayList<Voucher> listOfVoucher) {
        this.listOfVoucher = listOfVoucher;
    }

    public int getTotalExtraVoucher() {
        return totalExtraVoucher;
    }

    public void setTotalExtraVoucher(int totalExtraVoucher) {
        this.totalExtraVoucher = totalExtraVoucher;
    }
}
