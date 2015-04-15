package com.example.diego.sms_send;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;


public class MainActivity extends Activity {
    EditText PhoneTx,SmsTx;
    TextView SmsRx,PhoneRx;
    Button BtnEnviar,BtnAPN,Btn_Foto,Btn_AudioIntrusion,Btn_AudioAccesoNoAutorizado;
    ToggleButton TB_Camara;
    String Numero,Mensaje;
    String TAG_APN="#55#",TAG_FOTO="#03#",TAG_ONCAMARA="#01#",
    TAG_OFFCAMARA="#02#",TAG_SETMASTER="00";
    private SMSReceiver mReceiver ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LevantarXML();
        Botones();
        mReceiver= new SMSReceiver(SmsRx);

    }


    private void LevantarXML() {
        SmsTx= (EditText) findViewById(R.id.editSmsEnviar);
        SmsRx= (TextView) findViewById(R.id.textSmsRecibir);
        PhoneTx=(EditText) findViewById(R.id.editPhone);
        PhoneRx=(TextView)findViewById(R.id.textRxTelefono);
        BtnEnviar=(Button) findViewById(R.id.btnEnviar);
        BtnAPN=(Button) findViewById(R.id.Btn_Apn);
        Btn_Foto=(Button) findViewById(R.id.Btn_Foto);
        Btn_AudioAccesoNoAutorizado=(Button) findViewById(R.id.Btn_AudioAccNoAut);
        Btn_AudioIntrusion=(Button) findViewById(R.id.Btn_AudioIntrusion);
        TB_Camara=(ToggleButton)findViewById(R.id.TB_Activar);
    }

    private void Botones() {

       TB_Camara.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

               if(isChecked){
                   sendSMS(PhoneTx.getText().toString(),TAG_ONCAMARA);
               }else{
                   sendSMS(PhoneTx.getText().toString(),TAG_OFFCAMARA);

               }
           }
       });


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
                Numero = PhoneTx.getText().toString();
                Mensaje = TAG_APN;
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
        Btn_AudioIntrusion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AudioIntrusion();
            }
        });
        Btn_AudioAccesoNoAutorizado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AudioAccesoNoAutorizado();
            }
        });
    }



    private void AudioIntrusion() {
        MediaPlayer mp=MediaPlayer.create(this,R.raw.alarmadeintrusion);
        mp.start();

    }

    private void AudioAccesoNoAutorizado() {
        MediaPlayer mp=MediaPlayer.create(getApplicationContext(), R.raw.accesonoautorizado);
        mp.start();
    }

    private void sendSMS(String phoneNumber, String message)
        {
            PendingIntent sentPI = PendingIntent.getActivity(this, 0,
                    new Intent(this,MainActivity.class), 0);
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(phoneNumber, null, message, sentPI, null);


        }


}
