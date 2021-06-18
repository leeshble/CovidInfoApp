package com.corona.coronaapp.system;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.corona.coronaapp.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {

    ArrayList<ListViewAdapterData> list = new ArrayList<ListViewAdapterData>();

    String[]cityIdList = {"서울", "부산", "대구", "인천", "광주",
            "대전", "울산", "세종", "경기", "강원", "충북",
            "충남", "전북", "전남", "경북", "경남", "제주"};

    DBHelper dbHelper;
    SQLiteDatabase sqLiteDatabase = null;
    Cursor cursor;


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final Context context = viewGroup.getContext();

        dbHelper = new DBHelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();

        //리스트뷰에 아이템이 인플레이트 되어있는지 확인한후
        //아이템이 없다면 아래처럼 아이템 레이아웃을 인플레이트 하고 view객체에 담는다.
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater. inflate(R.layout.listview_custom, viewGroup, false);
        }

        //이제 아이템에 존재하는 텍스트뷰 객체들을 view객체에서 찾아 가져온다
        TextView itemId = (TextView) view.findViewById(R.id.listId);
        TextView itemTitle = (TextView)view.findViewById(R.id.itemTitle);
        TextView itemPosition = (TextView)view.findViewById(R.id.itemPosition);
        TextView itemStage = (TextView)view.findViewById(R.id.item_stage_text);

        //현재 포지션에 해당하는 아이템에 글자를 적용하기 위해 list배열에서 객체를 가져온다.
        ListViewAdapterData listData = list.get(i);

        itemId.setText(Integer.toString(listData.getId()));
        itemTitle.setText(listData.getTitle());
        itemPosition.setText(cityIdList[listData.getPosition()]);

        WebTextColleter webTextColleter = new WebTextColleter(context);
        Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                new Thread() {
                    @Override
                    public void run() {
                        Document document = null;
                        String local_stage = "";
                        ListViewAdapter listViewAdapter = new ListViewAdapter();
                        try {
                            document = Jsoup.connect("http://ncov.mohw.go.kr/regSocdisBoardView.do?brdId=6&brdGubun=68&ncvContSeq=495").get();
                            local_stage = document.getElementsByAttributeValue("data-city", "step_map_city" + (listData.getPosition() + 1)).get(1).text();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        itemStage.setText(local_stage);
                    }

                }.start();
            }
        };
        return view;
    }

    //ArrayList로 선언된 list 변수에 목록을 채워주기 위함
    public void addItemToList(int id, String title, int position){
        ListViewAdapterData listdata = new ListViewAdapterData();

        listdata.setId(id);
        listdata.setTitle(title);
        listdata.setPosition(position);

        //값들의 조립이 완성된 listdata객체 한개를 list배열에 추가
        list.add(listdata);
    }
}
