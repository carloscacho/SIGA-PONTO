package me.dm7.barcodescanner.zxing.sample.controller;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import me.dm7.barcodescanner.zxing.sample.model.database.DataBaseSMD;

/**
 * Created by CarlosEmilio on 31/08/2016.
 * this is a class singleton of access bd
 */
public class DataBaseController {

    private SQLiteDatabase connDataBase;
    private DataBaseSMD dataBaseSMD;

    static private DataBaseController instance = null;


    /**
     * Constructor of DataBase
     * @param context
     */
    private DataBaseController(Context context){


        //create bd and conex of base data BDSMD
        try {
            dataBaseSMD = new DataBaseSMD(context);
            connDataBase = dataBaseSMD.getWritableDatabase();
            Log.i("BD", "Banco de dados Criado com sucesso");

            //if ocorrer um erro na criação do banco de dados
        }catch (Exception error){
            Log.e("BD", "Erro na criação do banco: " + error.getMessage());
            AlertDialog.Builder ald = new AlertDialog.Builder(context);
            ald.setMessage("Erro na criação do banco: " + error.getMessage());
            ald.setNeutralButton("OK", null);
            ald.show();
        }


    }

    /**
     * This method controller the access of BD
     * @param context send by activity
     * @return the new instance create or instace create early for access data base
     */
    static public DataBaseController getInstance(Context context){
        if(instance == null){
            instance = new DataBaseController(context);
            Log.i("BD", "New access create");
            return instance;

        }else {
            Log.i("BD", "Access early create");
            return instance;
        }
    }

//    /**
//     *
//     * @param context this necessary for create arrayAd
//     * @return a array with number of voucher checked
//     *
//     */
//    public ArrayList<ListItemPontos> getArrayVoucher(Context context){
//        ArrayList<ListItemPontos> stringArrayAdapter = new ArrayList<>();
//        ListItemPontos item;
//        //get All voucher Checked
//        Cursor cs = connDataBase.query("VOUCHERIN",null,null,null,null,null,null);
//
//        if(cs.getCount() > 0) {
//            cs.moveToFirst();
//
//            do {
//                String number = cs.getString(1);
//                item = new ListItemPontos("Empresa X", "10", number, "0" );
//                stringArrayAdapter.add(item);
//
//            }while(cs.moveToNext());
//        }
//
//
//        cs.close();
//
//        return stringArrayAdapter;
//    }


    /**
     * Insert elements in DB
     * @param voucher number of voucher checked in qr-code
     */

    public void insertElementsVoucherIn(String voucher){
        Log.i("Voucher", voucher);
        ContentValues contValue = new ContentValues();
        contValue.put("VoNumber", voucher);
        connDataBase.insertOrThrow("VOUCHERIN", null, contValue);

    }

    public void insertElementsTest(){

        for (int i = 0; i < 30; i++) {
            ContentValues contValue = new ContentValues();
            contValue.put("VoNumber", "Number text"  + i);
            connDataBase.insertOrThrow("VOUCHERIN", null, contValue);
        }

    }


    public SQLiteDatabase getConnDataBase() {
        return connDataBase;
    }

    public DataBaseSMD getDataBaseSMD() {
        return dataBaseSMD;
    }

}
