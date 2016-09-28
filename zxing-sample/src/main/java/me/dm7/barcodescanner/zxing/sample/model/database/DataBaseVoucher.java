package me.dm7.barcodescanner.zxing.sample.model.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by CarlosEmilio on 31/08/2016.
 */
public class DataBaseVoucher extends SQLiteOpenHelper{

    public DataBaseVoucher(Context context){
        super(context, "BD_SMD", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS VOUCHERIN(" +
                "_id    INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "VoNumber   VARCHAR(255)" +
                ");");

//        db.execSQL("CREATE TABLE IF NOT EXISTS EXP_VOUCHER(" +
//                "_id    INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
//                "VoNumber   VARCHAR(255)" +
//                "CompanyName    VARCHAR(255)" +
//                "Employ_Name    VARCHAR(255)" +
//                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
