package salah.basel.nanodegree.outbox.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;


import salah.basel.nanodegree.outbox.R;

public class FullArticleDetails extends AppCompatActivity {
    private Toolbar toolbar;
    TextView head;
    TextView content;
    TextView writer;
    Intent ret;

    void loadFullArticle(Intent returned) {
        content = (TextView) findViewById(R.id.art_content);

        head = (TextView) findViewById(R.id.text_head2);
        content.setText(returned.getStringExtra("content"));
        head.setText(returned.getStringExtra("head"));
        writer = (TextView) findViewById(R.id.text_writer);
        writer.setText( returned.getStringExtra("writer_name"));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent returned = getIntent();
        setContentView(R.layout.activity_full_article_details);
        loadFullArticle(returned);

        ret = returned;
        TextView time = (TextView) findViewById(R.id.text_time);
        ImageView writer_img = (ImageView) findViewById(R.id.writer_img);


        // Picasso.with(this).load(speakers.get(3).get(i)).error(R.drawable.icon_speaker).placeholder(R.drawable.icon_speaker).into(writer_img);

        ImageView img = (ImageView) findViewById(R.id.art_pic);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        time.setText(returned.getStringExtra("time"));
        Picasso.with(this).load(returned.getStringExtra("photo_path")).placeholder(R.drawable.loading).error(R.drawable.loading).resize(450, 300).centerCrop().into(img);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_full_article_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.shareTopic) {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, "https://www.facebook.com/zmagazine?fref=ts");

            this.startActivity(Intent.createChooser(shareIntent, "share via"));

        }
        if (id == R.id.addfavorite) {
            //  new sqlliteAdapter(this).addNewFavourite(ret.getStringExtra("head"));
            Toast.makeText(FullArticleDetails.this, getResources().getString(R.string.articleAdded), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
