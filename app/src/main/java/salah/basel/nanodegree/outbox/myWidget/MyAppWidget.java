package salah.basel.nanodegree.outbox.myWidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

import java.util.ArrayList;

import salah.basel.nanodegree.outbox.MainActivity;
import salah.basel.nanodegree.outbox.Model.Article;
import salah.basel.nanodegree.outbox.R;
import salah.basel.nanodegree.outbox.parser.FireBaseRequestor;

/**
 * Implementation of App Widget functionality.
 */
public class MyAppWidget extends AppWidgetProvider {
    private Context context;
    private int[] appWidgetIds;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {


        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.my_app_widget);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    private AppWidgetManager appWidgetManager;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        this.appWidgetManager = appWidgetManager;
        this.context = context;
        this.appWidgetIds = appWidgetIds;
        Intent intent = new Intent(context,
                MyAppWidgetService.class);

//new FireBaseRequestor().getArticleData(this);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);


        // Construct the RemoteViews object
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.my_app_widget);
        remoteViews.setRemoteAdapter(R.id.listView, intent);
        Intent clickIntent = new Intent(context,
                MainActivity.class);

        clickIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        clickIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,
                appWidgetIds);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, clickIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setPendingIntentTemplate(R.id.listView, pendingIntent);
        // Update the widgets via the service
        intent.setData(Uri.parse(
                intent.toUri(Intent.URI_INTENT_SCHEME)));
        if (FireBaseRequestor.widgetArticles != null) {
            context.startService(intent);

            // There may be multiple widgets active, so update all of them
            appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
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

    public void updateWidgetAdapter(ArrayList<Article> articles) {
        Intent intent = new Intent(context,
                MyAppWidgetService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,
                appWidgetIds);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.my_app_widget);
        remoteViews.setRemoteAdapter(R.id.listView, intent);
        Intent clickIntent = new Intent(context,
                MainActivity.class);

        clickIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        clickIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,
                appWidgetIds);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, clickIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setPendingIntentTemplate(R.id.listView, pendingIntent);
        // Update the widgets via the service
        //  intent.putExtra("articles",articles.get(0));
        intent.setData(Uri.parse(
                intent.toUri(Intent.URI_INTENT_SCHEME)));
        context.startService(intent);
        appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
    }


}

