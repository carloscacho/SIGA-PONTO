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
        super(context, "UNIVAGHACKBD", null, 1);
    }


    /**
     * Create the tables if not exist yet
     * @param db connection of Data Base
     */
    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL("CREATE TABLE IF NOT EXISTS usuario(" +
                "_id            INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "login    VARCHAR(20)," +
                "nome    VARCHAR(80)," +
                "senha      VARCHAR(50)," +
                "dadosID      VARCHAR(50)" +
                ");");

        db.execSQL("CREATE TABLE IF NOT EXISTS batidaregistro(" +
                "_id            INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "batidaregistroID INTEGER"+
                "escolaID     INTEGER," +
                "ponto     DATE,"+
                "aprovado     INTEGER,"+
                "motivo      VARCHAR(255),"+
                ");");

        db.execSQL("CREATE TABLE IF NOT EXISTS escola(" +
                "_id            INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "escolaID       INTEGER,"+
                "descricao      INTEGER,"+
                "latitude       VARCHAR(255)," +
                "longitude      VARCHAR(255),"+
                "enderecoID        INTEGER"+
                ");");

        db.execSQL("CREATE TABLE IF NOT EXISTS entradasaida(" +
                "_id            INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "batidaregistroID       INTEGER,"+
                "horaentrada       VARCHAR(20)," +
                "horasaida     VARCHAR(20)"+
                 ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
