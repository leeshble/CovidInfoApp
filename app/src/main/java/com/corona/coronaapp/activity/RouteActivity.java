package com.corona.coronaapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.corona.coronaapp.R;
import com.corona.coronaapp.dialog.AddRouteDialog;

public class RouteActivity extends AppCompatActivity {

    ImageView back_btn,add_btn;

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
                AddRouteDialog addRouteDialog = new AddRouteDialog(RouteActivity.this);
                addRouteDialog.callDialog();
            }
        });

    }
}