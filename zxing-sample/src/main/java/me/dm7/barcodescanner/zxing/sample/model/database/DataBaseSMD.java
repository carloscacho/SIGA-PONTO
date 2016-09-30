package me.dm7.barcodescanner.zxing.sample.model.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by CarlosEmilio on 31/08/2016.
 */
public class DataBaseSMD extends SQLiteOpenHelper{

    /**
     * Create Data Base in the Device
     * @param context of main view
     */
    public DataBaseSMD(Context context){
        super(context, "BD_SMD", null, 1);
    }


    /**
     * Create the tables if not exist yet
     * @param db connection of Data Base
     */
    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL("CREATE TABLE IF NOT EXISTS CLIENT_COMPANY(" +
                "_id    INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "CompanyName    VARCHAR(255)" +
                "CompanyCT    INTEGER" +
                ");");

        db.execSQL("CREATE TABLE IF NOT EXISTS COURSE(" +
                "_id    INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "CourseName   VARCHAR(255)" +
                "CourseDate   VARCHAR(20)"+
                ");");

        db.execSQL("CREATE TABLE IF NOT EXISTS VOUCHER(" +
                "_id    INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "IdCourse INTEGER"+
                "IdClient INTEGER"+
                "VoucherNumber   VARCHAR(255)" +
                "Status   INTEGER"+
                "IsExtra  INTEGER"+
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
