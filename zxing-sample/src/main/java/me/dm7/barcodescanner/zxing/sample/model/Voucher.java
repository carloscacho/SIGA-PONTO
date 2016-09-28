package me.dm7.barcodescanner.zxing.sample.model;

/**
 * Created by CarlosEmilio on 26/09/2016.
 */
public class Voucher {

    //attributes
    private long numVoucher;
    private boolean checked;
    private boolean extra;
    private Course LinkedCourse;

    //constructor
    public Voucher(){
        //nothing
    }

    public Voucher(long numVoucher, boolean checked){
        this.numVoucher = numVoucher;
        this.checked = checked;
        this.extra = false;
    }

    public Voucher(long numVoucher, boolean checked, Course course) {
        this.numVoucher = numVoucher;
        this.checked = checked;
        this.extra = false;
        LinkedCourse = course;
    }


    //methods
    public long getNumVoucher() {
        return numVoucher;
    }
    
    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isExtra() {
        return extra;
    }

    public void setExtra(boolean extra) {
        this.extra = extra;
    }
    
    public Course getLinkedCourse() {
        return LinkedCourse;
    }

    public void setLinkedCourse(Course linkedCourse) {
        this.LinkedCourse = linkedCourse;
    }


}
