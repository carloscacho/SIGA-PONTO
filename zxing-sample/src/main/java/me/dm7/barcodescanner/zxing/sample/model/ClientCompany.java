package me.dm7.barcodescanner.zxing.sample.model;

import android.support.v4.util.ArrayMap;

import java.util.Date;

/**
 * Created by CarlosEmilio on 26/09/2016.
 */

public class ClientCompany {
    private String companyName;
    private int companyCT;
    private ArrayMap<Integer, ReservationMonth> listOfReservation;
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

        listOfReservation = new ArrayMap<>();
        totalExtraVoucher = 0;
    }

    /**
     *
     * @return
     */
    private void createKey(){
        int month = getIndex();
        listOfReservation.put(month, new ReservationMonth(month));
    }

    /**
     *
     * @param numberVoucher
     * @param course
     * @return
     */
    private boolean addVoucher(long numberVoucher, Course course) {

        int monthAndYear = getIndex();
        Voucher voucher = new Voucher(numberVoucher, false, course);

        if (listOfReservation.get(monthAndYear).getReservedCourses().size() -
                listOfReservation.get(monthAndYear).getTotalExtraVoucher() < companyCT)
            listOfReservation.get(monthAndYear).getReservedCourses().add(voucher);
        else
            return false;
        return true;

    }

    /**
     * clear List of vouchers loaded in the mouth
     */
    private void clearListVoucher() {
        listOfReservation.clear();
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

        int index = getIndex();

        for (int i = 0; i < listOfReservation.size(); i++) {
            if (listOfReservation.get(index).getReservedCourses().get(i).getNumVoucher() == numberVoucher) {
                // case localize voucher
                tempVoucher.setLinkedCourse(listOfReservation.get(index).getReservedCourses().get(i).getLinkedCourse());
                listOfReservation.get(index).getReservedCourses().remove(i);
                listOfReservation.get(index).getReservedCourses().add(tempVoucher);
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
    private void includExtraVoucher(long numberVoucher, Course course) {
        Voucher voucherTemp = new Voucher(numberVoucher, false, course);
        voucherTemp.setExtra(true);

        int index = getIndex();

        listOfReservation.get(index).getReservedCourses().add(voucherTemp);
        totalExtraVoucher++;

    }

    public int getIndex (){
        Date today = new Date();
        int monthAndYear = (today.getMonth() * 1000) + (today.getYear());

        return monthAndYear;
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

    public ArrayMap<Integer, ReservationMonth> getListOfReservation() {
        return listOfReservation;
    }

    public void setListOfReservation(ArrayMap<Integer, ReservationMonth> listOfReservation) {
        this.listOfReservation = listOfReservation;
    }


    public void setTotalExtraVoucher(int totalExtraVoucher) {
        this.totalExtraVoucher = totalExtraVoucher;
    }


}
