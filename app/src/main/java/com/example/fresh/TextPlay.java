package com.example.fresh;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Random;

/**
 * Created by boobalan_t on 23-Feb-17.
 */
public class TextPlay extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text);

        final EditText display = (EditText)findViewById(R.id.command);
        Button trycommand = (Button) findViewById(R.id.tryButton);
        final ToggleButton toggle = (ToggleButton)findViewById(R.id.toggle);
        final TextView tv = (TextView)findViewById(R.id.tvInvalid);
        final Button dialog = (Button) findViewById(R.id.dialog);

        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(toggle.isChecked()){
                    display.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                else{
                    display.setInputType(InputType.TYPE_CLASS_TEXT);
                }
            }
        });

        trycommand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String show = display.getText().toString();
                display.setText(show);

                if(show.contentEquals("left")){
                    tv.setGravity(Gravity.LEFT);
                }
                else if(show.contentEquals("right")){
                    tv.setGravity(Gravity.RIGHT);

                }
                else if(show.contentEquals("wtf")){
                    Random crazy = new Random();
                    tv.setText("CRAZY SIZE AND COLOR");
                    tv.setTextColor(Color.rgb(crazy.nextInt(264),crazy.nextInt(264),crazy.nextInt(264) ));
                    tv.setTextSize(crazy.nextInt(75));
                }
                else if(show.contentEquals("blue")){
                    tv.setTextColor(Color.BLUE);
                }
                else{
                    tv.setText("Enter correctly");
                }
            }
        });

        dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog newDialog = new Dialog(TextPlay.this);
                newDialog.setContentView(R.layout.custom_dialog);
                newDialog.setTitle("SmartCard");

                ImageView image1 = (ImageView) newDialog.findViewById(R.id.image);
                image1.setImageResource(R.drawable.tricolor);

                Button button = (Button) newDialog.findViewById(R.id.custom_button);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        newDialog.dismiss();
                    }

                });

                newDialog.show();
            }
        });
    }
}
