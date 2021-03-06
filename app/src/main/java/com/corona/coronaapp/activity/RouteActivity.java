package com.corona.coronaapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.corona.coronaapp.R;
import com.corona.coronaapp.dialog.AddRouteDialog;
import com.corona.coronaapp.dialog.DeleteRouteDialog;
import com.corona.coronaapp.system.DBHelper;
import com.corona.coronaapp.system.ListViewAdapter;
import com.corona.coronaapp.system.WebTextColleter;

public class RouteActivity extends AppCompatActivity {

    DBHelper dbHelper;
    ImageView back_btn, delete_btn, add_btn;
    ListView listView;
    WebTextColleter webTextColleter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);

        webTextColleter = new WebTextColleter(RouteActivity.this);

        back_btn = (ImageView)findViewById(R.id.back_btn);
        delete_btn = (ImageView)findViewById(R.id.delete_btn);
        add_btn = (ImageView)findViewById(R.id.add_btn);
        listView = (ListView)findViewById(R.id.route_list);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        SwipeRefreshLayout route_swipe_refresh = (SwipeRefreshLayout)findViewById(R.id.route_swipe_refresh);
        route_swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                displayList();
                route_swipe_refresh.setRefreshing(false);
            }
        });

        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteRouteDialog deleteRouteDialog = new DeleteRouteDialog(RouteActivity.this);
                deleteRouteDialog.callDialog();
            }
        });

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddRouteDialog addRouteDialog = new AddRouteDialog(RouteActivity.this);
                addRouteDialog.callDialog();
            }
        });

    }

    public void displayList(){
        //Dbhelper??? ???????????? ????????? ????????? SQLiteDatabase??? ?????? ????????????
        dbHelper = new DBHelper(RouteActivity.this);
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        //Cursor?????? ????????? ????????? ????????????
        Cursor cursor = database.rawQuery("SELECT * FROM list",null);

        //??????????????? ?????? ???????????? ????????? adapter??????
        ListViewAdapter listAdapter = new ListViewAdapter();

        //????????? ???????????? ???????????? adapter??? ?????? list????????? add
        while(cursor.moveToNext()){
            //num ?????? ?????? ???????????? ????????? 0?????? ??????, name??? 1???
            listAdapter.addItemToList(cursor.getInt(0), cursor.getString(1), cursor.getInt(2));
        }
        //??????????????? ????????? ????????? ?????? ????????? adapter??? ??????
        listView.setAdapter(listAdapter);
    }

}