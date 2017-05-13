package salah.basel.nanodegree.outbox.myWidget;

import android.content.Intent;
import android.widget.RemoteViewsService;

import java.util.ArrayList;

import salah.basel.nanodegree.outbox.Model.Article;
import salah.basel.nanodegree.outbox.parser.FireBaseRequestor;

/**
 * Created by Basel on 03/02/2017.
 */

public class MyAppWidgetService extends RemoteViewsService {
    Intent i;
    ArrayList<Article> all;

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        new FireBaseRequestor().getArticleData(this);
        i = intent;
        return new WidgetListAdapter(getApplicationContext(), intent, FireBaseRequestor.widgetArticles);
    }

    public void updateWidgetAdapter(ArrayList<Article> articles) {
        all = articles;
        onGetViewFactory(i);
    }
}
