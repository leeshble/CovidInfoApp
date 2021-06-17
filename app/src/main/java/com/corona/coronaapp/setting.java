package com.corona.coronaapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class setting extends Activity
{
    private Context context;
    public setting(Context context)
    {
        this.context = context;
    }
    public void callDialog() {
        final Dialog connection_dialog = new Dialog(context);   //다이얼로그 초기화
        connection_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);    //타이틀 제거
        connection_dialog.setContentView(R.layout.setting);   // xml 레이아웃 파일과 연결
        connection_dialog.show();   //다이얼로그 보이기
        connection_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // 투명 배경

        final TextView title_text = (TextView) connection_dialog.findViewById(R.id.title_text);
        final EditText port_edit = (EditText) connection_dialog.findViewById(R.id.sub2_edit);
        final TextView cancel_btn = (TextView) connection_dialog.findViewById(R.id.cancel_btn);
        final TextView confirm_btn = (TextView) connection_dialog.findViewById(R.id.confirm_btn);
        final TextView error_text = (TextView) connection_dialog.findViewById(R.id.error_dialog_txt);

//        //커스텀 다이얼로그 내부 표시 설정
//        title_text.setText("Setting");
//        port_edit.setHint("xxxx");
//        port_edit.setInputType(2);
//        cancel_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                connection_dialog.dismiss();
//            }
//        });
//        confirm_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (port_edit.getText().toString().equals("")) {
//                    error_text.setVisibility(View.VISIBLE);
//                } else {
//                    connection_dialog.dismiss();
//                }
//            }
//        });
    }
}

