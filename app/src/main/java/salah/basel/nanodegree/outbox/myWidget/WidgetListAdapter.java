package salah.basel.nanodegree.outbox.myWidget;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

import salah.basel.nanodegree.outbox.Model.Article;
import salah.basel.nanodegree.outbox.R;

/**
 * Created by Basel on 12/05/2017.
 */

public class WidgetListAdapter implements RemoteViewsService.RemoteViewsFactory {
    Context context;
    ArrayList<Article> articles;
    public WidgetListAdapter(Context context, Intent intent,ArrayList<Article> Articles) {
this.context=context;
articles= Articles;
this.onDataSetChanged();
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        if(articles==null)
            articles=new ArrayList<>();
        return articles.size();
    }

    @Override
    public RemoteViews getViewAt(int i) {
        final  RemoteViews remoteView = new RemoteViews(
                context.getPackageName(), R.layout.widget_layout);
        remoteView.setTextViewText(R.id.update,articles.get(i).getHead());
        remoteView.setImageViewResource(R.id.widget_pic,R.drawable.logo);
        try {
            Bitmap b = Picasso.with(context).load(articles.get(i).getPhoto()).resize(450,400).get();
            remoteView.setImageViewBitmap(R.id.widget_pic, b);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return remoteView;
    }

    @Override
    public RemoteViews getLoadingView() {
        final  RemoteViews remoteView = new RemoteViews(
                context.getPackageName(), R.layout.widget_layout);

        return remoteView;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView articleHead;
        public ImageView image;
        public Article mItem;
        public LinearLayout articleFooter;
        public ImageView favIcon;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            image = (ImageView) view.findViewById(R.id.article_image);
            articleHead = (TextView) view.findViewById(R.id.article_head);
            articleFooter = (LinearLayout) view.findViewById(R.id.article_footer);
            favIcon = (ImageView) view.findViewById(R.id.favourite);
        }
    }
    }
