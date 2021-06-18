package com.corona.coronaapp.system;

import android.content.Context;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class WebTextColleter{

    DBHelper dbHelper;
    String stages;
    Context context;
    String[]cityIdList = {"서울", "부산", "대구", "인천", "광주",
            "대전", "울산", "세종", "경기", "강원", "충북",
            "충남", "전북", "전남", "경북", "경남", "제주"};

    public WebTextColleter(Context context) {
        this.context = context;
    }

    public void webTextColleting(String city) {
        new Thread() {
            public void run() {
                Log.d("RUN", local_stage(find_city(city)));
            }
        }.start();
    }

    private String find_city(String city) {
        dbHelper = new DBHelper(context);
        String cityId = "";
        for (int i = 0; i < cityIdList.length; i++) {
            if (cityIdList[i].equals(city)) {
                cityId = "step_map_city" + (i + 1);
                break;
            }
        }
        return cityId;
    }

    private String local_stage(String cityId) {
        Document document = null;
        String local_info = "";
        try {
            document = Jsoup.connect("http://ncov.mohw.go.kr/regSocdisBoardView.do?brdId=6&brdGubun=68&ncvContSeq=495").get();

            local_info = document.getElementsByAttributeValue("data-city", cityId).first().text();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return local_info;
    }
}
