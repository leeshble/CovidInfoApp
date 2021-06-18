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
        //Dbhelper의 읽기모드 객체를 가져와 SQLiteDatabase에 담아 사용준비
        dbHelper = new DBHelper(RouteActivity.this);
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        //Cursor라는 그릇에 목록을 담아주기
        Cursor cursor = database.rawQuery("SELECT * FROM list",null);

        //리스트뷰에 목록 채워주는 도구인 adapter준비
        ListViewAdapter listAdapter = new ListViewAdapter();

        //목록의 개수만큼 순회하여 adapter에 있는 list배열에 add
        while(cursor.moveToNext()){
            //num 행은 가장 첫번째에 있으니 0번이 되고, name은 1번
            listAdapter.addItemToList(cursor.getInt(0), cursor.getString(1), cursor.getInt(2));
        }
        //리스트뷰의 어댑터 대상을 여태 설계한 adapter로 설정
        listView.setAdapter(listAdapter);
    }

}