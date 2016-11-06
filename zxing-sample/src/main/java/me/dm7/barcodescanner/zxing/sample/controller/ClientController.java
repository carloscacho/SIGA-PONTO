        package me.dm7.barcodescanner.zxing.sample.controller;

        import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.location.Location;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import me.dm7.barcodescanner.zxing.sample.controller.GeoLocation.GeoLocationBounds;
import me.dm7.barcodescanner.zxing.sample.model.ListItemPontos;

        /**
         * Created by CarlosEmilio on 28/09/2016.
         *
         */

        public class ClientController {

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

                dbController = DataBaseController.getInstance(context);
            }




            /**
             * only test
             */
            public void insertElementsTest(Context context) {


                //create professor
                try {

                    Cursor batidaregistroCursor = dbController.getConnDataBase().query("batidaregistro", null, null, null, null, null, null);

                    if(batidaregistroCursor.getCount() == 0) {

                        ContentValues contValueUser = new ContentValues();



                        // create batidas
                        for (int i = 0; i < 8; i++) {
                            ContentValues contValue = new ContentValues();
                            contValue.put("batidaregistroID", i);
                            contValue.put("escolaID", i % 5);

                            contValue.put("pontoDiaMes", String.valueOf(i + 10));
                            contValue.put("pontoDiaSem", getWeakDate((i % 7) + 1));
                            contValue.put("pontoHora", "09:00");

                            contValue.put("aprovado", i % 2);
                            contValue.put("motivo", "motivo " + i);
                            dbController.getConnDataBase().insertOrThrow("batidaregistro", null, contValue);
                        }

                        // create escola
                        for (int i = 0; i < 6; i++) {
                            ContentValues contValue = new ContentValues();
                            contValue.put("escolaID", i);
                            contValue.put("descricao", "Escola " + i);
                            contValue.put("latitude", Double.toString(37.422 + i));
                            contValue.put("longitude", Double.toString(122.084 + i));
                            contValue.put("enderecoID", i);
                            dbController.getConnDataBase().insertOrThrow("escola", null, contValue);
                        }

                    }

                    batidaregistroCursor.close();

                    }catch(Exception e){
                        Log.e("Insert BD", e.getMessage());
                    }



            }




            public ArrayList<ListItemPontos> getArrayVoucherWithOutPreload() {
                ArrayList<ListItemPontos> stringArrayAdapter = new ArrayList<>();
                ListItemPontos item;
                String nomeEscola = "0";
                int countCheck = 0;



                Cursor batidaregistroCursor = dbController.getConnDataBase().query("batidaregistro", null, null, null, null, null, null);

                if (batidaregistroCursor.getCount() > 0) {
                    //initialize
                    batidaregistroCursor.moveToFirst();

                    do {
                        //get all elements of csClient
                        int idBatida = batidaregistroCursor.getInt(1);
                        int idEscola = batidaregistroCursor.getInt(2);
                        String batidaPontoDiaMes = batidaregistroCursor.getString(3);
                        String batidaPontoDiaSem = batidaregistroCursor.getString(4);
                        String batidaPontoHora = batidaregistroCursor.getString(5);
                        int aprovado = batidaregistroCursor.getInt(6);

                        //find voucher of the client x
                        Cursor escolaCursor = dbController.getConnDataBase().query("escola",null, ("escolaID == " + idEscola), null,null,null,null);
                        if(escolaCursor.getCount() > 0){
                            escolaCursor.moveToFirst();
                            nomeEscola = escolaCursor.getString(2);


                        }


                        String conf = aprovado == 1 ? "Confirmado" : "Não Confirmado";


                        //create item list company with total of voucher and total of checkin
                        item = new ListItemPontos(nomeEscola, batidaPontoDiaSem, batidaPontoDiaMes ,  conf, batidaPontoHora );
                        stringArrayAdapter.add(item);

                        //clear the variables
                        nomeEscola = "0";
                        countCheck = 0;

                        escolaCursor.close();

                    } while (batidaregistroCursor.moveToNext());
                }

                batidaregistroCursor.close();


                return stringArrayAdapter;

            }




                public String getWeakDate(int numberDate){
                    String retorno = "";

                    switch (numberDate) {

                        case 1: retorno = "Domingo"; break;

                        case 2: retorno = "Segunda-feira"; break;

                        case 3: retorno = "Terça-Feira"; break;

                        case 4: retorno = "Quarta-feira"; break;

                        case 5: retorno = "Quinta-feira"; break;

                        case 6: retorno = "Sexta-feira"; break;

                        case 7: retorno = "Sábado"; break;

                    }

                    return retorno;

                }





            public void CreateAccess(String strLogin){

                Cursor batidaregistroCursor = dbController.getConnDataBase().query("usuario", null, null, null, null, null, null);

                if(batidaregistroCursor.getCount() == 0) {

                    ContentValues contValueUser = new ContentValues();

                    contValueUser.put("nome", "Carlos Emilio de A. Cacho");
                    contValueUser.put("login", "carlosemilio");
                    contValueUser.put("senha", "1234");
                    contValueUser.put("dadosID", "1");
                    dbController.getConnDataBase().insertOrThrow("usuario", null, contValueUser);
                }

                batidaregistroCursor.close();

            }


           public boolean verifyAccess(){
               Cursor registroCursor = dbController.getConnDataBase().query("usuario", null, null, null, null, null, null);
               int count =  registroCursor.getCount();
               registroCursor.close();


               return  count > 0;




           }

            public void removeAccess(){

//                Cursor registroCursor = dbController.getConnDataBase().query("usuario", null, null, null, null, null, null);
//                registroCursor.moveToFirst();
//                String strLogin = registroCursor.getString(3);
//                registroCursor.close();
                try {
                    dbController.getConnDataBase().execSQL("TRUNCATE table " + "usuario");
                    //tem um erro aqui não sei que está acontecendo
                }catch (Exception ex){
                    System.out.println("Erro no truncate " + ex.getMessage());
                }


            }

            public boolean isHourAndLocationOk(Location location) {
                Calendar calendar = new GregorianCalendar();
                Date trialTime = new Date();
                calendar.setTime(trialTime);
                String now =  calendar.get(Calendar.HOUR_OF_DAY ) + ":00" ;


                Cursor registroCursor = dbController.getConnDataBase().query("batidaregistro", null, "pontoHora like " + now, null, null, null, null);
                if(registroCursor.getCount() > 0){
                    registroCursor.moveToFirst();
                    int escolaID = registroCursor.getInt(2);

                    Cursor escolaCursor = dbController.getConnDataBase().query("escola", null, "escolaID = " + escolaID, null, null, null, null);

                    if(escolaCursor.getCount() > 0){
                        escolaCursor.moveToFirst();
                        double lat = Double.parseDouble(escolaCursor.getString(3));
                        double lon = Double.parseDouble(escolaCursor.getString(4));

                        GeoLocationBounds geo = new GeoLocationBounds(lat, lon);
                        if(geo.contains(location.getLatitude(), location.getLongitude()))
                            return true;
                    }

                }
                else{
                    return false;
                }
                return false;
            }
        }
