package me.dm7.barcodescanner.zxing.sample;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import me.dm7.barcodescanner.zxing.sample.controller.ClientController;
import me.dm7.barcodescanner.zxing.sample.controller.DataBaseController;
import me.dm7.barcodescanner.zxing.sample.controller.GeoLocation.GeoEvent;
import me.dm7.barcodescanner.zxing.sample.controller.GeoLocation.GeoLocationBounds;
import me.dm7.barcodescanner.zxing.sample.controller.GeoLocation.PointLocation;
import me.dm7.barcodescanner.zxing.sample.controller.GeoLocation.polygon.Point;
import me.dm7.barcodescanner.zxing.sample.controller.GeoLocation.polygon.Polygon;
import me.dm7.barcodescanner.zxing.sample.controller.behavior.AdapterVoucher;
import me.dm7.barcodescanner.zxing.sample.model.ListItemPontos;

public class MainActivity extends AppCompatActivity  {
    private static final int ZXING_CAMERA_PERMISSION = 1;


    //List de Voucher
    private RecyclerView lstVoucherIn;
    private ArrayList<ListItemPontos> arrayVoucherIn;
    private ClientController clientController;


    //location
    private PointLocation professorLocation;
    private Class<?> mClss;

    //localizacão de teste
    private GeoLocationBounds locationBounds = new GeoLocationBounds(-15.5908429, -56.1116182);
    private Polygon squareScholl;

    //database
    private DataBaseController DBPontos;

    //tela de carregamento da batida de ponto
    private ProgressDialog progressDialog;


    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_main);
        setupToolbar();

        //create ListView
        lstVoucherIn = (RecyclerView) findViewById(R.id.lstVoucherCheck);
        arrayVoucherIn = new ArrayList<>();

        //create baseData
        DBPontos = DataBaseController.getInstance(this);
        clientController = new ClientController(this);
        clientController.insertElementsTest(this);


        //Define layout of Recycle View
        RecyclerView.LayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        lstVoucherIn.setLayoutManager(layout);

        //location
        professorLocation = new PointLocation(this);


        //create square for test if the point

        squareScholl = Polygon.Builder()
                .addVertex(new Point(-15.641165f, -56.099413f))
                .addVertex(new Point(-15.641310f, -56.100019f))
                .addVertex(new Point(-15.640752f, -56.100046f))
                .addVertex(new Point(-15.640602f, -56.099504f))
                .build();


    }

    @Override
    protected void onResume() {
        super.onResume();

        // reeload list
        arrayVoucherIn = clientController.getArrayVoucherWithOutPreload();
        lstVoucherIn.setAdapter(new AdapterVoucher(arrayVoucherIn, this));
    }

    public void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


    /**
     * Get location of cellphone
     * send to server
     * @param v
     */
    public void getProfessorLocation(View v){
        progressDialog = ProgressDialog.show(this,"ponto batido", "verificando locarlização",true);
        progressDialog.setCancelable(false);
        GeoEvent event = new GeoEvent() {
            @Override
            public void trigger(Location location)  {

                String strLocation = String.valueOf(location.getLatitude())
                 + " " +String.valueOf( location.getLongitude());

                if(squareScholl.contains(new Point((float)location.getLatitude(),(float)location.getLongitude())))
                {


                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this, "localização encontrada: " + strLocation, Toast.LENGTH_SHORT).show();
//                    Send information
//                  try {
//                        PostPosition postPosition = new PostPosition();
//                        postPosition.postJason(Double.toString(location.getLatitude()), Double.toString(location.getLongitude()));
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
                }else{
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this, "localização errada: " + strLocation, Toast.LENGTH_SHORT).show();

                }
            }
        };
        professorLocation.getLocationNow(event);
//
    }



    @Override
    public void onRequestPermissionsResult(int requestCode,  String permissions[], int[] grantResults) {
        switch (requestCode) {
            case ZXING_CAMERA_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(mClss != null) {
                        Intent intent = new Intent(this, mClss);
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(this, "Please grant camera permission to use the QR Scanner", Toast.LENGTH_SHORT).show();
                }
                return;
        }
    }


    /**
     * Exit button
     * @param v
     */
    public void logout(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Sair do Aplicativo");

        builder.setMessage("Tem certeza que deseja sair")
                .setCancelable(false)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        clientController.removeAccess();
                        finish();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
}