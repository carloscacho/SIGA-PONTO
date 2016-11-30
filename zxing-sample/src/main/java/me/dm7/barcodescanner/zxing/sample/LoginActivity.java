package me.dm7.barcodescanner.zxing.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import me.dm7.barcodescanner.zxing.sample.controller.ClientController;

public class LoginActivity extends AppCompatActivity {

    private EditText edtLogin;
    private EditText edtSenha;
    private TextView txtMsg;
    private Button btnLogar;

    private boolean isAccesed = false;

    private ClientController clientController;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtLogin = (EditText) findViewById(R.id.edtLogin);
        edtSenha = (EditText) findViewById(R.id.edtPassApp);

        btnLogar = (Button) findViewById(R.id.btnLogin);
        txtMsg = (TextView) findViewById(R.id.txtMsg);

        clientController = new ClientController(this);


    }

    @Override
    protected void onResume() {
        super.onResume();

        //verifica já existe acesso
        if(clientController.verifyAccess()){
            isAccesed = true;
            loginSIGA(new View(this));
        }
    }

    public void loginSIGA(View v){
        String strLogin = edtLogin.getText().toString();
        String strSenha = edtSenha.getText().toString();


        if(isAccesed || (strLogin.equals("carlos") && strSenha.equals("1234"))){

            clientController.CreateAccess(strLogin);

            Intent it = new Intent(this,MainActivity.class);
            startActivity(it);

        }else{
            txtMsg.setText("Login e/ou Senha incorretos " +
                    "Tente novamente");
        }

    }

}
