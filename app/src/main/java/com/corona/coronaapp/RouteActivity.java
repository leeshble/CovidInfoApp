package com.corona.coronaapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class RouteActivity extends AppCompatActivity {

    ImageView back_btn,add_btn;
    View addrouteView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);

        back_btn = (ImageView)findViewById(R.id.back_btn);
        add_btn = (ImageView)findViewById(R.id.add_btn);
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
                route_swipe_refresh.setRefreshing(false);
            }
        });

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addrouteView = (View)View.inflate(RouteActivity.this, R.layout.add_route,null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(RouteActivity.this);
                dlg.setView(addrouteView);
                dlg.show();
            }
        });

    }
}