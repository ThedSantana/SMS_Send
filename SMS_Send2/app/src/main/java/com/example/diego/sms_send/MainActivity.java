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
    Button BtnEnviar,BtnAPN;
    String Numero,Mensaje;
    String TAG_APN="#55#";


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

    }





        private void sendSMS(String phoneNumber, String message)
        {
            String SENT = "SMS_SENT";
            String DELIVERED = "SMS_DELIVERED";

            /*PendingIntent sentPI = PendingIntent.getBroadcast(this, 0,
             //       new Intent(SENT), 0);


            PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0,
                    new Intent(DELIVERED), 0);

            //---when the SMS has been sent---
            registerReceiver(new BroadcastReceiver(){
                @Override
                public void onReceive(Context arg0, Intent arg1) {
                    switch (getResultCode())
                    {
                        case Activity.RESULT_OK:
                            Toast.makeText(getBaseContext(), "SMS sent",
                                    Toast.LENGTH_SHORT).show();
                            break;
                        case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                            Toast.makeText(getBaseContext(), "Generic failure",
                                    Toast.LENGTH_SHORT).show();
                            break;
                        case SmsManager.RESULT_ERROR_NO_SERVICE:
                            Toast.makeText(getBaseContext(), "No service",
                                    Toast.LENGTH_SHORT).show();
                            break;
                        case SmsManager.RESULT_ERROR_NULL_PDU:
                            Toast.makeText(getBaseContext(), "Null PDU",
                                    Toast.LENGTH_SHORT).show();
                            break;
                        case SmsManager.RESULT_ERROR_RADIO_OFF:
                            Toast.makeText(getBaseContext(), "Radio off",
                                    Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }, new IntentFilter(SENT));

            //---when the SMS has been delivered---
            registerReceiver(new BroadcastReceiver(){
                @Override
                public void onReceive(Context arg0, Intent arg1) {
                    switch (getResultCode())
                    {
                        case Activity.RESULT_OK:
                            Toast.makeText(getBaseContext(), "SMS Entregado",
                                    Toast.LENGTH_SHORT).show();
                            break;
                        case Activity.RESULT_CANCELED:
                            Toast.makeText(getBaseContext(), "SMS No Entregado",
                                    Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }, new IntentFilter(DELIVERED));*/
            PendingIntent sentPI = PendingIntent.getActivity(this, 0,
                    new Intent(this,MainActivity.class), 0);
            SmsManager sms = SmsManager.getDefault();
        //    sms.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI);
            sms.sendTextMessage(phoneNumber, null, message, sentPI, null);


        }





    private void LevantarXML() {
        SmsTx= (EditText) findViewById(R.id.editSmsEnviar);
        SmsRx= (TextView) findViewById(R.id.textSmsRecibir);
        PhoneTx=(EditText) findViewById(R.id.editPhone);
        PhoneRx=(TextView)findViewById(R.id.textRxTelefono);
        BtnEnviar=(Button) findViewById(R.id.btnEnviar);
        BtnAPN=(Button) findViewById(R.id.Btn_Apn);
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
