package com.corona.coronaapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class add_route extends Activity
{
    TextView cancel_btn, confirm_btn;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_route);
        cancel_btn = (TextView)findViewById(R.id.cancel_btn);
        confirm_btn = (TextView)findViewById(R.id.confirm_btn);
//        sub2_edit.setVisibility(View.INVISIBLE);

        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "취소되었습니다",Toast.LENGTH_SHORT).show();  //토스트 생성

            }
        });
        confirm_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0){
                Toast.makeText(getApplicationContext(), "입력되었습니다",Toast.LENGTH_SHORT).show(); //토스트 생성
            }
        });
    }
}
