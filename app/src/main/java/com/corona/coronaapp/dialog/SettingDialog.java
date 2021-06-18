package com.corona.coronaapp.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.corona.coronaapp.R;

public class SettingDialog {

    private Context context;
    private int city_select;
    private int checked = 0;

    public SettingDialog(Context context) {
        this.context = context;
    }

    public void callDialog() {
        final Dialog setting_dialog = new Dialog(context);
        setting_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setting_dialog.setContentView(R.layout.dialog_custom);
        setting_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setting_dialog.show();

        TextView title_text = (TextView)setting_dialog.findViewById(R.id.title_text);
        TextView radio_title_text = (TextView)setting_dialog.findViewById(R.id.sub1_text);
        RadioGroup radioGroup = (RadioGroup)setting_dialog.findViewById(R.id.dialog_radioGroup);
        RadioButton radioButton1 = (RadioButton)setting_dialog.findViewById(R.id.dialog_radioButton1);
        RadioButton radioButton2 = (RadioButton)setting_dialog.findViewById(R.id.dialog_radioButton2);
        Spinner spinner = (Spinner)setting_dialog.findViewById(R.id.dialog_spinner);
        TextView error_text = (TextView)setting_dialog.findViewById(R.id.error_dialog_txt);
        Button cancelButton = (Button)setting_dialog.findViewById(R.id.cancel_btn);
        Button confirmButton = (Button)setting_dialog.findViewById(R.id.confirm_btn);

        title_text.setText("설정");
        radio_title_text.setText("위치 설정");
        radio_title_text.setVisibility(View.VISIBLE);
        radioButton1.setText("현재 위치");
        radioButton1.setChecked(true);
        radioButton2.setText("사용자 지정 위치");
        radioGroup.setVisibility(View.VISIBLE);

        //spinner 설정
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(context, R.array.city_array, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.dialog_radioButton1) {
                    spinner.setVisibility(View.GONE);
                } else if(i == R.id.dialog_radioButton2) {
                    spinner.setVisibility(View.VISIBLE);
                }
                checked = 1;
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                city_select = i;
                error_text.setVisibility(View.GONE);
                checked = 2;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                error_text.setText("지역을 선택해 주세요");
                error_text.setVisibility(View.VISIBLE);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setting_dialog.dismiss();
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checked == 0) {
                    error_text.setText("방식을 선택해 주세요");
                    error_text.setVisibility(View.VISIBLE);
                } else if (checked == 1) {
                    //현재 위치 지정
                    setting_dialog.dismiss();
                } else if (checked == 2) {
                    //선택한 위치로 지정
                    Log.d("Setting", String.valueOf(city_select));
                    setting_dialog.dismiss();
                }
            }
        });

    }
}
