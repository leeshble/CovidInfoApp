package com.corona.coronaapp;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class setting extends Activity {
    RadioButton gps, choose_local;
    RadioGroup choice;
    EditText sub2_edit;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        choice = (RadioGroup)findViewById(R.id.choice);
        gps = (RadioButton)findViewById(R.id.gps);
        choose_local = (RadioButton)findViewById(R.id.choose_local);
        sub2_edit = (EditText)findViewById(R.id.sub2_edit);
        choice.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0){
                switch(choice.getCheckedRadioButtonId()){
                    case R.id.gps:
                        // 현재위치 클릭시
                        sub2_edit.setVisibility(View.INVISIBLE);
                        Toast toast = Toast.makeText(setting.this, "토스트 연습",Toast.LENGTH_SHORT); //토스트 생성
                        toast.show();
                    case R.id.choose_local:
                        // 위치찾기 클릭시
                        sub2_edit.setVisibility(View.VISIBLE);
                    default:
                        Toast toast2 = Toast.makeText(setting.this, "뭐든 클릭해라",Toast.LENGTH_SHORT); //토스트 생성
                        toast2.show();
                }
            }
        });



    }
}
