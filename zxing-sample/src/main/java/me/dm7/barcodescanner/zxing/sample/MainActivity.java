package me.dm7.barcodescanner.zxing.sample;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
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
    private GeoLocationBounds locationBounds = new GeoLocationBounds(  37.422 - 0.20, 37.422 + 0.20, 122.084 - 0.20, 122.084 + 0.20);
    private DataBaseController DBPontos;

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

        //preload
//        clientController.preloadClients();
//        clientController.preloadCourse();
//        clientController.preLoadVoucher();

        //Define layout of Recycle View
        RecyclerView.LayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        lstVoucherIn.setLayoutManager(layout);

        //location
        professorLocation = new PointLocation(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        //get all elements in the Data Base
        //DBPontos.insertElementsTest();


        arrayVoucherIn = clientController.getArrayVoucherWithOutPreload();

        //load all DBPontos-in in list
        lstVoucherIn.setAdapter(new AdapterVoucher(arrayVoucherIn, this));
    }

    public void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


    public void getProfessorLocation(View v){
        GeoEvent event = new GeoEvent() {
            @Override
            public void trigger(Location location) {

                String strLocation = String.valueOf(location.getLatitude())
                 + " " +String.valueOf( location.getLongitude());

                if(locationBounds.contains(location.getLatitude(), location.getLongitude()))
                {
                    Toast.makeText(MainActivity.this, "localização encontrada: " + strLocation, Toast.LENGTH_SHORT).show();
                }else{
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
}