package com.bilouro.Alunos;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();

        Object[] messages = (Object[]) bundle.get("pdus");
        byte[] message = (byte[]) messages[0];
        SmsMessage sms = SmsMessage.createFromPdu( message );
        String telefone = sms.getDisplayOriginatingAddress();

        Toast.makeText(context, "SMS recebido do telefone: " + telefone, Toast.LENGTH_LONG).show();
        MediaPlayer.create(context, R.raw.music).start();
    }

}