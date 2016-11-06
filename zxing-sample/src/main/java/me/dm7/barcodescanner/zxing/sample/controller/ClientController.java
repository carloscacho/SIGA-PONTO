package me.dm7.barcodescanner.zxing.sample.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

import me.dm7.barcodescanner.zxing.sample.model.ClientCompany;
import me.dm7.barcodescanner.zxing.sample.model.Course;
import me.dm7.barcodescanner.zxing.sample.model.ListItemClientCompany;
import me.dm7.barcodescanner.zxing.sample.model.ReservationMonth;
import me.dm7.barcodescanner.zxing.sample.model.Voucher;

/**
 * Created by CarlosEmilio on 28/09/2016.
 *
 */

public class ClientController {
    private ArrayList<ClientCompany> clients;
    private ArrayList<Course> courses;
    private DataBaseController dbController;


    //===================================================
    //
    //     Create client model
    //
    //===================================================

    /**
     * @param context
     */
    public ClientController(Context context) {
        clients = new ArrayList<>();
        courses = new ArrayList<>();
        dbController = DataBaseController.getInstance(context);
    }


    /**
     * @param ClientName
     * @param ClientCT
     * @return
     */
    public boolean addClient(int id, String ClientName, int ClientCT) {
        ClientCompany client = new ClientCompany(ClientName, ClientCT);
        client.setIdCompany(id);
        if (!clients.contains(client))
            clients.add(client);
        else
            return false;
        return true;
    }


    /**
     * Get all elements in BD put in Array of Client Company
     */
    public void preloadClients() {
        Cursor cs = dbController.getConnDataBase().query("CLIENT_COMPANY", null, null, null, null, null, null);

        if (cs.getCount() > 0) {
            cs.moveToFirst();

            do {
                int id = cs.getInt(0);
                String name = cs.getString(1);
                int ct = cs.getInt(2);
                addClient(id, name, ct);


            } while (cs.moveToNext());
        }


        cs.close();

    }


    //===================================================
    //
    //     Create Course model
    //
    //===================================================

    /**
     * @param course
     * @return
     */
    public boolean addCourse(Course course) {

        if (!courses.contains(course))
            courses.add(course);
        else
            return false;
        return true;
    }


    /**
     * Get all elements in BD put in Array of Client Company
     */
    public void preloadCourse() {
        Cursor cs = dbController.getConnDataBase().query("COURSE", null, null, null, null, null, null);

        if (cs.getCount() > 0) {
            cs.moveToFirst();

            do {

                addCourse(createCourse(cs));

            } while (cs.moveToNext());
        }


        cs.close();

    }

    public Course createCourse(Cursor cs) {
        int id = cs.getInt(0);
        String name = cs.getString(1);
        int date = cs.getInt(2);

        Course course = new Course(name, date);
        course.setIdCourse(id);

        return course;
    }


    //===================================================
    //
    //     Create Voucher model
    //
    //===================================================

    public void preLoadVoucher() {
        Cursor cs = dbController.getConnDataBase().query("VOUCHER", null, null, null, null, null, null);

        if (cs.getCount() > 0) {
            cs.moveToFirst();

            do {
                int idCourse = cs.getInt(1);
                int idClient = cs.getInt(2);
                int voucherN = Integer.parseInt(cs.getString(3));
                int status = Integer.parseInt(cs.getString(4));
                int extra = cs.getInt(5);
                addVoucher(idCourse, idClient, voucherN, status, extra);

            } while (cs.moveToNext());
        }


        cs.close();
    }


    private void addVoucher(int idCourse, int idClient, int voucherN, int status, int extra) {
        Cursor cs = dbController.getConnDataBase().query("COURSE", null, null, null, null, null, null);
        cs.moveToFirst();
        Course link = createCourse(cs);

        Voucher voucher = new Voucher(voucherN, false, link);
        //change int to boolean
        voucher.setExtra(extra != 0);
        voucher.setChecked(status != 0);

        for (int i = 0; i < clients.size(); i++) {
            if (clients.get(i).getIdCompany() == idClient) {
                int key = link.getDateCourse() / 100;
                if (!clients.get(i).getListOfReservation().containsKey(key)) {
                    clients.get(i).getListOfReservation().put(key, new ReservationMonth(key));
                    clients.get(i).getListOfReservation().get(key).getReservedCourses().add(voucher);
                } else {
                    clients.get(i).getListOfReservation().get(key).getReservedCourses().add(voucher);
                }
            }
        }
    }

//    /**
//     * make all preload
//     *
//     * @param context of create connection
//     * @return object copy of
//     */
//    public ClientController loadClientController(Context context) {
//        ClientController clients = new ClientController(context);
//        clients.preloadClients();
//        clients.preloadCourse();
//        clients.preLoadVoucher();
//
//        return clients;
//    }

    /**
     * only test
     */
    public void insertElementsTest() {
        Random random = new Random();

        //create company
        try {

            for (int i = 0; i < 30; i++) {
                ContentValues contValue = new ContentValues();
                contValue.put("CompanyName", "Empresa " + i);
                contValue.put("CompanyCT", Math.abs(random.nextInt() % 10) + 1);
                dbController.getConnDataBase().insertOrThrow("CLIENT_COMPANY", null, contValue);
            }


            // create courses
            for (int i = 0; i < 10; i++) {
                ContentValues contValue = new ContentValues();
                contValue.put("CourseName", "Curso " + i);
                contValue.put("CourseDate", constructorDate(random));
                dbController.getConnDataBase().insertOrThrow("COURSE", null, contValue);
            }

            // create voucher
            for (int i = 0; i < 50; i++) {
                ContentValues contValue = new ContentValues();
                contValue.put("IdCourse", (i % 10) + 1);
                contValue.put("IdClient", (Math.abs(random.nextInt()) % 30) + 1);
                contValue.put("VoucherNumber", Integer.toString(17000 + i));
                contValue.put("Status", (i % 2));
                contValue.put("IsExtra", (Math.abs(random.nextInt()) % 2));
                dbController.getConnDataBase().insertOrThrow("VOUCHER", null, contValue);
            }

        } catch (Exception e) {
            Log.e("Insert BD", e.getMessage());
        }

    }

    private int constructorDate(Random rand) {
        int day = (Math.abs(rand.nextInt()) % 31) + 1;
        int month = (Math.abs(rand.nextInt()) % 12) + 1;
        int year = 2016;

        int date = (((year * 100) + month) * 100) + day;
        Log.i("Date: ", Integer.toString(date));

        return date;
    }


    public ArrayList<ListItemClientCompany> getArrayVoucher() {
        ArrayList<ListItemClientCompany> stringArrayAdapter = new ArrayList<>();
        ListItemClientCompany item;

//        Cursor csCourse = dbController.getConnDataBase().query("COURSE", null, null, null, null, null, null);
//        Cursor csClient = dbController.getConnDataBase().query("CLIENT_COMPANY", null, null, null, null, null, null);
//        Cursor csVoucher = dbController.getConnDataBase().query("VOUCHER", null, null, null, null, null, null);
//


        for (int i = 0; i < clients.size(); i++) {
            String name = clients.get(i).getCompanyName();
            String ct = Integer.toString(clients.get(i).getCompanyCT());
            //String numberVoucher = Integer.toString(clients.get(i).getListOfReservation().get(constructorKey()).getReservedCourses().size());
            //String check = Integer.toString(countCheckIn(i));
            item = new ListItemClientCompany(name, ct, "10" ,   "2" );
            stringArrayAdapter.add(item);
        }

        return stringArrayAdapter;

    }

    public ArrayList<ListItemClientCompany> getArrayVoucherWithOutPreload() {
        ArrayList<ListItemClientCompany> stringArrayAdapter = new ArrayList<>();
        ListItemClientCompany item;
        String totalVoucher = "0";
        int countCheck = 0;
        Cursor csCourse = dbController.getConnDataBase().query("COURSE", null, null, null, null, null, null);
        Cursor csClient = dbController.getConnDataBase().query("CLIENT_COMPANY", null, null, null, null, null, null);

//
        if (csClient.getCount() > 0) {
            //initialize
            csClient.moveToFirst();

            csCourse.moveToFirst();

            do {
                //get all elements of csClient
                int id = csClient.getInt(0);
                String name = csClient.getString(1);
                int ct = csClient.getInt(2);


                //find voucher of the client x
                Cursor csVoucher = dbController.getConnDataBase().query("VOUCHER",null, ("idClient == " + id), null,null,null,null);
                if(csVoucher.getCount() > 0){
                    csVoucher.moveToFirst();
                    totalVoucher = String.valueOf(csVoucher.getCount());

                    do{
                        //count total of voucher is checked
                        if(csVoucher.getInt(2) == 1){
                            countCheck++;
                        }
                    }while (csVoucher.moveToNext());

                }

                //create item list company with total of voucher and total of checkin
                item = new ListItemClientCompany(name, Integer.toString(ct), totalVoucher , Integer.toString(countCheck) );
                stringArrayAdapter.add(item);

                //clear the variables
                totalVoucher = "0";
                countCheck = 0;

            } while (csClient.moveToNext());
        }






        return stringArrayAdapter;

    }

    /**
     * @param index
     * @return
     */
    public int countCheckIn(int index) {
        int count = 0;

        for (int i = 0; i < clients.get(index).getListOfReservation().size(); i++) {
            if (clients.get(index).getListOfReservation().get(constructorKey()).getReservedCourses().get(i).isChecked())
                count++;
        }
        return count;
    }

    private int constructorKey() {
        Date today = new Date();
        int month = today.getMonth();
        int year = new GregorianCalendar().get(Calendar.YEAR);

        int date = ((year * 100) + month);
        Log.i("Date: ", Integer.toString(date));

        return date;
    }

}
