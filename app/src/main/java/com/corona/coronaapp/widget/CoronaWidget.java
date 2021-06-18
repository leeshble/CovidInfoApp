package com.corona.coronaapp.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.corona.coronaapp.R;
import com.corona.coronaapp.activity.MainActivity;
import com.corona.coronaapp.system.DBHelper;
import com.corona.coronaapp.system.GpsAdapter;

/**
 * Implementation of App Widget functionality.
 */
public class CoronaWidget extends AppWidgetProvider {

    GpsAdapter gpsAdapter;
    MainActivity mainActivity;
    RemoteViews widget_title_text, widget_stage_text, widget_guide_text, widget_reload_button;

    private static final String SYNC_CLICKED    = "automaticWidgetSyncButtonClick";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.corona_widget);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        mainActivity = new MainActivity();
        gpsAdapter = new GpsAdapter(context);

        //widget_reload_button.setOnClickPendingIntent(R.id.widget_reload_button, );
        widget_title_text.setTextViewText(R.id.widget_title_text, gpsAdapter.setTitleGps());
        widget_stage_text.setTextViewText(R.id.widget_stage_text, mainActivity.setStage());
        widget_guide_text.setTextViewText(R.id.widget_guide_text, mainActivity.setGuideText());

        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created

    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    public void setWidgetText() {
    }
}