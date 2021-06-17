package com.corona.coronaapp;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class add_route extends AppCompatActivity
{
    TextView cancel_btn, confirm_btn, error_dialog_txt;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_route);
        cancel_btn = (TextView)findViewById(R.id.cancel_btn);
        confirm_btn = (TextView)findViewById(R.id.confirm_btn);
        error_dialog_txt = (TextView)findViewById(R.id.error_dialog_txt);
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                error_dialog_txt.setVisibility(View.VISIBLE);
            }
        });
        confirm_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                error_dialog_txt.setVisibility(View.VISIBLE);
            }
        });
    }
}
