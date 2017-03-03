package com.example.fresh;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by boobalan_t on 01-Mar-17.
 */
public class SmartCard extends Activity {

    public static final String PREFERENCE_NAME = "smartrecharge";
    String topUpAmt;
    String cardNo, cardValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.smartcard);
        //SharedPreferences prefCardDetails = this.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);

       // TextView cardTitle = (TextView) findViewById(R.id.cardTitle);
       // final EditText cardNoInput = (EditText) findViewById(R.id.topUp);
        //TextView topUpTitle = (TextView) findViewById(R.id.topUpTitle);
        final EditText topUpAmtInput = (EditText) findViewById(R.id.topUp);
        Button sendRecharge = (Button) findViewById(R.id.send);

       // SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        // Reading from SharedPreferences


/*        if(cardValue != null){
            cardNoInput.setText(cardValue);
            Toast.makeText(getApplicationContext(), "Card details are pre-populated. Please check it.", Toast.LENGTH_LONG ).show();
        }*/

        sendRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences setting = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
                String cardValue = setting.getString("card", "");

                if(cardValue ==  null || TextUtils.isEmpty(cardValue)){
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(SmartCard.this);

                    alertDialog.setTitle("Card detail required");
                    alertDialog.setCancelable(true);
                    alertDialog.setMessage("Please enter last 6 digits of card number:");
                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            }
                    );
                }

                //cardNo = cardNoInput.getText().toString();
                topUpAmt = topUpAmtInput.getText().toString();

                String rechargeSMS = "MYCARD "+ topUpAmt +" "+cardNo;

                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage("9222208888", null, rechargeSMS, null, null);

                    // Writing data to SharedPreferences
                    SharedPreferences settings = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);

                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("card", cardNo);
                    editor.commit();

                    Toast.makeText(getApplicationContext(), "Recharge SMS sent successfully !!!", Toast.LENGTH_LONG ).show();
                }
                catch(Exception e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Recharge SMS sending is failed. Please try again !", Toast.LENGTH_LONG ).show();
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
