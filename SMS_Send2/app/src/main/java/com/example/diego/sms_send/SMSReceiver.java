package com.example.diego.sms_send;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Diego on 12/04/2015.
 */
public class SMSReceiver extends BroadcastReceiver {
private TextView SmsRx;

    public SMSReceiver(TextView SmsRx){
    this.SmsRx=SmsRx;
    }
    @Override
    public void onReceive(Context context, Intent intent) {

        LinearLayout layout=new LinearLayout(context);
       View view =View.inflate(context,R.layout.activity_main,layout);
        SmsRx= (TextView)view.findViewById(R.id.textSmsRecibir);

        Bundle bundle = intent.getExtras();
        Object messages[]=(Object[])bundle.get("pdus");
        SmsMessage smsMessage[]=new SmsMessage[messages.length];

        for(int n=0;n<messages.length;n++){
            smsMessage[n]=SmsMessage.createFromPdu((byte[])messages[n]);
      }


        Toast toast =Toast.makeText(context,"SMS Recibido: "+ smsMessage[0].getMessageBody(),Toast.LENGTH_LONG);//+" "+
         //       smsMessage[1].getMessageBody(),Toast.LENGTH_LONG);
        SmsRx.setText(smsMessage[0].getMessageBody());

        
        toast.show();

    }

   
}
