package com.corona.coronaapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.corona.coronaapp.R;

public class helper extends Activity
{
    TextView txthelper;
    Button btnhome;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.helper);
        txthelper = (TextView) findViewById(R.id.txthelper);
        btnhome = (Button)findViewById(R.id.btnhome);
        btnhome.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intenthome = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intenthome);
            }
        });
    }
}