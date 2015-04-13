package com.example.diego.sms_send;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    EditText PhoneTx,SmsTx;
    TextView SmsRx,PhoneRx;
    Button BtnEnviar,BtnAPN,Btn_Foto;
    String Numero,Mensaje;
    String TAG_APN="#55#",TAG_FOTO="#03#";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LevantarXML();
        Botones();

    }

    private void Botones() {

        BtnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Numero=PhoneTx.getText().toString();
                Mensaje=SmsTx.getText().toString();
                sendSMS(Numero, Mensaje);
            }
        });
        BtnAPN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Numero=PhoneTx.getText().toString();
                Mensaje=TAG_APN;
                sendSMS(Numero, Mensaje);
            }
        });
        Btn_Foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Numero=PhoneTx.getText().toString();
                Mensaje=TAG_FOTO;
                sendSMS(Numero, Mensaje);
            }
        });


    }





        private void sendSMS(String phoneNumber, String message)
        {
            PendingIntent sentPI = PendingIntent.getActivity(this, 0,
                    new Intent(getApplicationContext(),MainActivity.class), 0);
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(phoneNumber, null, message, sentPI, null);


        }





    private void LevantarXML() {
        SmsTx= (EditText) findViewById(R.id.editSmsEnviar);
        SmsRx= (TextView) findViewById(R.id.textSmsRecibir);
        PhoneTx=(EditText) findViewById(R.id.editPhone);
        PhoneRx=(TextView)findViewById(R.id.textRxTelefono);
        BtnEnviar=(Button) findViewById(R.id.btnEnviar);
        BtnAPN=(Button) findViewById(R.id.Btn_Apn);
        Btn_Foto=(Button) findViewById(R.id.Btn_Foto);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
