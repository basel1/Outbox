package salah.basel.nanodegree.outbox.parser;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import salah.basel.nanodegree.outbox.Logging.L;
import salah.basel.nanodegree.outbox.Model.*;
import salah.basel.nanodegree.outbox.UI.ArticleFragment;
import salah.basel.nanodegree.outbox.UI.EditionFragment;
import salah.basel.nanodegree.outbox.UI.WritersFragment;
import salah.basel.nanodegree.outbox.myWidget.MyAppWidget;
import salah.basel.nanodegree.outbox.myWidget.MyAppWidgetService;

/**
 * Created by Basel on 05/07/2016.
 */
public class FireBaseRequestor {
    public static final String FIRE_BASE_ROOT_URL = "https://outbox-a5d20.firebaseio.com/";
    public static final String FIRE_BASE_ARTICLE = "Articles";
    public static final String FIRE_BASE_WRITER = "Writers";
    public static final String FIRE_BASE_EDITION = "Editions";
    static boolean calledAlready = false;
   public static ArrayList<Article> widgetArticles;
FirebaseDatabase mDatabase;
    public FirebaseDatabase getDatabase()
{
    if (!calledAlready)
    {
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        calledAlready = true;
    }

    FirebaseDatabase database = FirebaseDatabase.getInstance();
return database;
}
    public void getArticleData(final ArticleFragment articleFragment) {
        final ArrayList<Article> arList;
      //  Firebase ref = new Firebase(FIRE_BASE_ROOT_URL).child(FIRE_BASE_ARTICLE);
        // ref.child("Writer").setValue(MyApplication.getInstance().getWriterData());

        DatabaseReference mDatabase;
        mDatabase=getDatabase().getReference(FIRE_BASE_ARTICLE);

        mDatabase.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                configerArticleObjects(dataSnapshot, articleFragment);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                L.m("firebase error: " + databaseError.getMessage());
            }
        });
    }

    public void getWriterData(final WritersFragment context) {
        DatabaseReference mDatabase;

        mDatabase = getDatabase().getReference(FIRE_BASE_WRITER);
        //  Firebase ref=new Firebase(FIRE_BASE_ROOT_URL).child(FIRE_BASE_WRITER);
        // ref.child("Writer").setValue(MyApplication.getInstance().getWriterData());
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                configerWriterObjects(dataSnapshot,context);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                L.m("firebase error: " + databaseError.getMessage());

            }
        });
    }

    private void configerWriterObjects(DataSnapshot snapshot, WritersFragment writersFragment) {
        int size = (int) snapshot.getChildrenCount();
        ArrayList<Writer> Articles = new ArrayList<>(size);
        Writer ses;
        Iterable<DataSnapshot> snapshots = snapshot.getChildren();
        for (int i = 0; i < size; i++) {
            ses = snapshots.iterator().next().getValue(Writer.class);
            //       ses= snapshot.child(String.valueOf(i)).getValue(Article.class);
            Articles.add(ses);
        }
        writersFragment.updateAdapter(Articles);
    }

    public void getEditionData(final EditionFragment editionFragment) {
        DatabaseReference mDatabase;

        mDatabase = getDatabase().getReference(FIRE_BASE_EDITION);
        // Firebase ref=new Firebase(FIRE_BASE_ROOT_URL).child(FIRE_BASE_EDITION);
        // ref.child("Writer").setValue(MyApplication.getInstance().getWriterData());
        Query query = mDatabase.orderByChild("number");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                configerEditionrObjects(dataSnapshot,editionFragment);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                L.m("firebase error: " + databaseError.getMessage());
            }
        });
       /* query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                L.m("snapppp" + s);
                configerEditionrObjects(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                L.m("snap" + dataSnapshot.getValue());
                configerEditionrObjects(dataSnapshot);

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });*/
    }


    private void configerEditionrObjects(DataSnapshot snapshot, EditionFragment editionFragment) {
        int size = (int) snapshot.getChildrenCount();
        ArrayList<Edition> Editions = new ArrayList<>(size);
        Edition ses;
        Iterable<DataSnapshot> snapshots = snapshot.getChildren();
        for (int i = 0; i < size; i++) {
            ses = snapshots.iterator().next().getValue(Edition.class);

            Editions.add(ses);
        }
        editionFragment.updateAdapter(Editions);
    }

    private ArrayList<Edition> sortEditions(ArrayList<Edition> Editions) {
        ArrayList<Edition> data = new ArrayList<>(Editions.size());
        for (int i = 0; i < Editions.size(); i++)
            data.add(new Edition());
        for (int i = 0; i < Editions.size(); i++) {
   /*         int index= Integer.parseInt(Editions.get(i).getOrder())-1;
            data.add(index,Editions.get(i));
L.m(Editions.get(i).getName()+" index "+index);*/
        }


        return data;
    }

    private void configerArticleObjects(DataSnapshot snapshot, ArticleFragment articleFragment) {
        int size = (int) snapshot.getChildrenCount();
        ArrayList<Article> Articles = new ArrayList<>(size);
        Article ses;
        Iterable<DataSnapshot> snapshots = snapshot.getChildren();
        for (int i = 0; i < size; i++) {
            ses = snapshots.iterator().next().getValue(Article.class);
            //       ses= snapshot.child(String.valueOf(i)).getValue(Article.class);
            Articles.add(ses);
        }
        articleFragment.updateAdapter(Articles);
    }
    private void configerArticleObjects(DataSnapshot snapshot, Context widget) {
        int size = (int) snapshot.getChildrenCount();
        ArrayList<Article> Articles = new ArrayList<>(size);
        Article ses;
        Iterable<DataSnapshot> snapshots = snapshot.getChildren();
        for (int i = 0; i < size; i++) {
            ses = snapshots.iterator().next().getValue(Article.class);
            //       ses= snapshot.child(String.valueOf(i)).getValue(Article.class);
            if(i>280)
            Articles.add(ses);
        }
       widgetArticles= Articles;
        AppWidgetManager man = AppWidgetManager.getInstance(widget.getApplicationContext());
        int[] ids = man.getAppWidgetIds(
                new ComponentName(widget.getApplicationContext(),MyAppWidget.class));
        Intent updateIntent = new Intent();
        updateIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        updateIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
        widget.sendBroadcast(updateIntent);
      //  widget.updateWidgetAdapter(Articles);
    }
    private static final String ACTION_DATA_UPDATED = "salah.basel.nanodegree.outbox.ACTION_DATA_UPDATED";

    public void excuteRequestes() {
    //    getArticleData(null);
        getWriterData(null);
        getEditionData(null);
    }

    public void getArticleData(final Context myAppWidget) {
        DatabaseReference mDatabase;
        mDatabase=getDatabase().getReference(FIRE_BASE_ARTICLE);

        mDatabase.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                configerArticleObjects(dataSnapshot, myAppWidget);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                L.m("firebase error: " + databaseError.getMessage());
            }
        });
    }
}
