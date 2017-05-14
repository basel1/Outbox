package salah.basel.nanodegree.outbox;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

import salah.basel.nanodegree.outbox.Logging.L;
import salah.basel.nanodegree.outbox.UI.ArticleFragment;
import salah.basel.nanodegree.outbox.UI.DrawerAdapter;
import salah.basel.nanodegree.outbox.UI.DrawerItem;
import salah.basel.nanodegree.outbox.UI.EditionFragment;
import salah.basel.nanodegree.outbox.UI.WritersFragment;
import salah.basel.nanodegree.outbox.UI.dummy.DummyContent;
import salah.basel.nanodegree.outbox.myWidget.MyAppWidgetService;
import salah.basel.nanodegree.outbox.parser.FireBaseRequestor;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, ArticleFragment.OnListFragmentInteractionListener {
    private static final String FIRST_TIME = "first_time";
    private Toolbar mToolbar;
    private NavigationView mDrawer;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private int mSelectedId;
    private boolean mUserSawDrawer = false;
    private ListView mDrawerList;
    private ActionBarDrawerToggle toggle;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    DrawerAdapter adapter;

    ArrayList<DrawerItem> dataList;
    // private editionsAdapter editionsAdapter;
    private RecyclerView editionsList;
    private ImageView mainBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //  Firebase.setAndroidContext(this);
        new FireBaseRequestor().getArticleData(this);
       // MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
        AdView adView = (AdView) findViewById(R.id.adview);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();


        adView.loadAd(adRequest);
        if (savedInstanceState == null) {
            initNavigationView();
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new ArticleFragment()).commit();
        }
    }

    private void initNavigationView() {

        mDrawer = (NavigationView) findViewById(R.id.navigation);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawer.setNavigationItemSelectedListener(this);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        mDrawerLayout.openDrawer(GravityCompat.START);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new ArticleFragment()).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.topNews) {
            setTitle(R.string.top_news);
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new ArticleFragment().newInstance(0, 2)).commit();
        } else if (id == R.id.editions) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new EditionFragment()).commit();
            setTitle(R.string.editions);
        } else if (id == R.id.favourite) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new ArticleFragment().newInstance(1, 1)).commit();
            setTitle(R.string.favourite);
        } else if (id == R.id.aboutUs) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new WritersFragment()).commit();
            setTitle(R.string.writers);
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }
}
