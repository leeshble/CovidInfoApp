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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.corona.coronaapp.R;

public class AddRouteDialog {

    private Context context;
    private int city_select;

    public AddRouteDialog(Context context) {
        this.context = context;
    }

    public void callDialog() {
        final Dialog add_route_dialog = new Dialog(context);
        add_route_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        add_route_dialog.setContentView(R.layout.dialog_custom);
        add_route_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        add_route_dialog.show();

        TextView title_text = (TextView)add_route_dialog.findViewById(R.id.title_text);
        TextView route_title_text = (TextView)add_route_dialog.findViewById(R.id.sub1_text);
        EditText route_title_edit = (EditText)add_route_dialog.findViewById(R.id.sub1_edit);
        TextView route_position_text = (TextView)add_route_dialog.findViewById(R.id.sub2_text); 
        Spinner spinner = (Spinner)add_route_dialog.findViewById(R.id.dialog_spinner);
        TextView error_text = (TextView)add_route_dialog.findViewById(R.id.error_dialog_txt);
        Button cancelButton = (Button)add_route_dialog.findViewById(R.id.cancel_btn);
        Button confirmButton = (Button)add_route_dialog.findViewById(R.id.confirm_btn);

        title_text.setText("경로 추가");
        route_title_text.setText("경로 이름");
        route_title_text.setVisibility(View.VISIBLE);
        route_title_edit.setHint("예: 학교");
        route_title_edit.setVisibility(View.VISIBLE);
        route_position_text.setText("경로 위치");
        route_position_text.setVisibility(View.VISIBLE);

        //spinner 설정
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(context, R.array.city_array, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setVisibility(View.VISIBLE);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                city_select = i;
                error_text.setVisibility(View.GONE);
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
                add_route_dialog.dismiss();
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (route_title_edit.getText().toString().equals("")) {
                    error_text.setText("경로 이름을 입력해 주세요");
                    error_text.setVisibility(View.VISIBLE);
                } else {
                    Log.d("Setting", String.valueOf(city_select));
                    add_route_dialog.dismiss();
                }
            }
        });
    }
}
