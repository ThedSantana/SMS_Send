package com.example.diego.sms_send;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    EditText PhoneTx,SmsTx;
    TextView SmsRx,PhoneRx;
    Button BtnEnviar;


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

                String Numero=PhoneTx.getText().toString();
                String Mensaje=SmsTx.getText().toString();
                SendSms(Numero,Mensaje);
            }
        });
    }

    private void SendSms(String Num,String Mens) {



           SmsManager manager = SmsManager.getDefault();
           manager.sendTextMessage(Num,null,Mens,null,null);
           Toast.makeText(getApplicationContext(),"SMS Enviado",Toast.LENGTH_SHORT).show();


        }

    private void LevantarXML() {
        SmsTx= (EditText) findViewById(R.id.editSmsEnviar);
        SmsRx= (TextView) findViewById(R.id.textSmsRecibir);
        PhoneTx=(EditText) findViewById(R.id.editPhone);
        PhoneRx=(TextView)findViewById(R.id.textRxTelefono);
        BtnEnviar=(Button) findViewById(R.id.btnEnviar);
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
