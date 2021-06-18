package com.corona.coronaapp.dialog;

import android.app.Dialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
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
import com.corona.coronaapp.activity.RouteActivity;
import com.corona.coronaapp.system.DBHelper;

public class DeleteRouteDialog {

    DBHelper dbHelper;
    SQLiteDatabase sqLiteDatabase = null;
    private Context context;

    public DeleteRouteDialog(Context context) {
        this.context = context;
    }

    public void callDialog() {
        final Dialog delete_route_dialog = new Dialog(context);
        delete_route_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        delete_route_dialog.setContentView(R.layout.dialog_custom);
        delete_route_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        delete_route_dialog.show();

        dbHelper = new DBHelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();

        TextView title_text = (TextView)delete_route_dialog.findViewById(R.id.title_text);
        TextView route_title_text = (TextView)delete_route_dialog.findViewById(R.id.sub1_text);
        EditText route_title_edit = (EditText)delete_route_dialog.findViewById(R.id.sub1_edit);
        TextView error_text = (TextView)delete_route_dialog.findViewById(R.id.error_dialog_txt);
        Button cancelButton = (Button)delete_route_dialog.findViewById(R.id.cancel_btn);
        Button confirmButton = (Button)delete_route_dialog.findViewById(R.id.confirm_btn);

        title_text.setText("경로 삭제");
        route_title_text.setText("삭제할 경로 번호");
        route_title_text.setVisibility(View.VISIBLE);
        route_title_edit.setHint("예: 1");
        route_title_edit.setVisibility(View.VISIBLE);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete_route_dialog.dismiss();
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (route_title_edit.getText().toString().equals("")) {
                    error_text.setText("삭제할 경로 번호를 입력해 주세요");
                    error_text.setVisibility(View.VISIBLE);
                } else {
                    sqLiteDatabase.execSQL("DELETE FROM list WHERE id = " + route_title_edit.getText());
                    RouteActivity routeActivity = new RouteActivity();
                    delete_route_dialog.dismiss();
                }
            }
        });
    }
}
