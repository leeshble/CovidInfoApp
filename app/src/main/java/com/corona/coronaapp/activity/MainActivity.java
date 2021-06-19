package com.corona.coronaapp.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.corona.coronaapp.dialog.SettingDialog;
import com.corona.coronaapp.system.DBHelper;
import com.corona.coronaapp.system.GpsAdapter;
import com.corona.coronaapp.system.GpsTracker;
import com.corona.coronaapp.R;
import com.corona.coronaapp.system.WebTextColleter;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity
{
    private DBHelper dbHelper;
    Cursor cursor;
    SQLiteDatabase sqLiteDatabase = null;
    private String city = "";
    private GpsTracker gpsTracker;
    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int PERMISSIONS_REQUEST_CODE = 100;
    String[] REQUIRED_PERMISSIONS  = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
    double latitude, longitude;
    String address;
    WebTextColleter webTextColleter;
    GpsAdapter gpsAdapter;

    View settingView;
    ImageView setting_btn;
    LinearLayout path_btn, item_more_btn1, item_more_btn2;
    TextView userPositionText;
    TextView level_text;
    TextView guide_text1;
    TextView press_text1, press_text2, press_text3, press_text4, press_text5;

    String[]cityIdList = {"서울", "부산", "대구", "인천", "광주",
            "대전", "울산", "세종", "경기", "강원", "충북",
            "충남", "전북", "전남", "경북", "경남", "제주"};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(MainActivity.this);
        sqLiteDatabase = dbHelper.getWritableDatabase();

        gpsAdapter = new GpsAdapter(MainActivity.this);

        if (!gpsAdapter.checkLocationServicesStatus()) {
            gpsAdapter.showDialogForLocationServiceSetting();
        }else {
            gpsAdapter.checkRunTimePermission();
        }

        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh_layout);
        userPositionText = (TextView)findViewById(R.id.user_position_text);
        setting_btn = (ImageView)findViewById(R.id.setting_btn);
        path_btn = (LinearLayout) findViewById(R.id.path_button);
        item_more_btn1 = (LinearLayout) findViewById(R.id.item_more_btn1);
        item_more_btn2 = (LinearLayout) findViewById(R.id.item_more_btn2);

        level_text = (TextView)findViewById(R.id.level_text);

        guide_text1 = (TextView)findViewById(R.id.guide_text1);

        press_text1 = (TextView)findViewById(R.id.press_text1);
        press_text2 = (TextView)findViewById(R.id.press_text2);
        press_text3 = (TextView)findViewById(R.id.press_text3);
        press_text4 = (TextView)findViewById(R.id.press_text4);
        press_text5 = (TextView)findViewById(R.id.press_text5);


        //GPS로 위치 설정
        gpsTracker = new GpsTracker(MainActivity.this);
        userPositionText.setText(setTitleGps());
        
        webTextColleter = new WebTextColleter(MainActivity.this);

        webTextColleter.webTextColleting(city);
        webTextColleter.pressInfo();

        //초기 화면 셋업
        guide_text1.setText(setGuideText());
        setPressText();
        level_text.setText(setStage());
        
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                webTextColleter.webTextColleting(city);
                webTextColleter.pressInfo();
                userPositionText.setText(setTitleGps());
                guide_text1.setText(setGuideText().replaceAll("\"", ""));
                setPressText();
                level_text.setText(setStage());
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        setting_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SettingDialog settingDialog = new SettingDialog(MainActivity.this);
                settingDialog.callDialog();
            }
        });

        path_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RouteActivity.class);
                startActivity(intent);
            }
        });

        item_more_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://ncov.mohw.go.kr/socdisBoardView.do?brdId=6&brdGubun=1"));
                startActivity(intent);
            }
        });

        item_more_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://ncov.mohw.go.kr/tcmBoardList.do?brdId=3&brdGubun="));
                startActivity(intent);
            }
        });

    }

    public String setStage() {
        cursor = sqLiteDatabase.rawQuery("SELECT Grade FROM Current_Info WHERE Info_ID = 1", null);
        String[] grade_text = new String[1];
        for (int i = 0; i < 1; i++) {
            cursor.moveToNext();
            grade_text[i] = cursor.getString(0);
        }
        cursor.close();
        return grade_text[0];
    }

    //상단 위치 지정
    public String setTitleGps() {
        latitude = gpsTracker.getLatitude();
        longitude = gpsTracker.getLongitude();

        address = gpsAdapter.getCurrentAddress(latitude, longitude);
        if (address.equals(null)) {
            address = "오류 주소 미발견";
        }
        String[] split_address = address.split(" ");
        String[] split_city = split_address[1].split("");
        city = split_city[0] + split_city[1];
        for (int i = 0; i < cityIdList.length; i++) {
            if (cityIdList[i].equals(city)) {
                sqLiteDatabase.execSQL("UPDATE Setting SET Local_ID = '" + i + "' WHERE Setting_ID = 1");
                break;
            }
        }
        Log.d("TAG", city);
        String refine_address = split_address[2] + " " + split_address[3];
        return refine_address;
    }

    //지침 업데이트
    public String setGuideText() {
        String stage = level_text.getText().toString();

        cursor = sqLiteDatabase.rawQuery("SELECT GuideText FROM Guideline", null);
        String[] guide_text = new String[6];
        for (int i = 0; i < 5; i++) {
            cursor.moveToNext();
            guide_text[i] = cursor.getString(0);
        }
        cursor.close();

        int state = 0;
        if (stage.equals("1")) {
            state = 1;
        } else if (stage.equals("2")) {
            state = 2;
        } else if (stage.equals("3")) {
            state = 3;
        } else if (stage.equals("4")) {
            state = 4;
        } else if (stage.equals("5")) {
            state = 5;
        }
        return guide_text[state];
    }

    //보도자료 업데이트
    public void setPressText() {
        webTextColleter.pressInfo();
        String[] press_text = new String[5];
        cursor = sqLiteDatabase.rawQuery("SELECT News_Info FROM News", null);
        for (int i = 0; i < 5; i++) {
            cursor.moveToNext();
            press_text[i] = cursor.getString(0);
        }
        cursor.close();
        press_text1.setText(press_text[0]);
        press_text2.setText(press_text[1]);
        press_text3.setText(press_text[2]);
        press_text4.setText(press_text[3]);
        press_text5.setText(press_text[4]);

    }
}