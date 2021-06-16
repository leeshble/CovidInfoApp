package com.corona.coronaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh_layout);

        LinearLayout cardListLayout = (LinearLayout)findViewById(R.id.card_list_layout);
        LayoutInflater cardInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View pressCard = cardInflater.inflate(R.layout.big_card, cardListLayout, true);
        View guideCard = cardInflater.inflate(R.layout.big_card, cardListLayout, true);

        TextView itemTitleText1 = (TextView)pressCard.findViewById(R.id.item_title_text);
        itemTitleText1.setText("보도자료");

        TextView itemTitleText2 = (TextView)guideCard.findViewById(R.id.item_title_text);
        itemTitleText2.setText("지침");
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}