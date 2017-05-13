package salah.basel.nanodegree.outbox.Adapters;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import salah.basel.nanodegree.outbox.DataHandlers.FavouriteHandler;
import salah.basel.nanodegree.outbox.MainActivity;
import salah.basel.nanodegree.outbox.R;
import salah.basel.nanodegree.outbox.UI.ArticleFragment.OnListFragmentInteractionListener;
import salah.basel.nanodegree.outbox.Model.Article;
import salah.basel.nanodegree.outbox.UI.dummy.DummyContent;
import salah.basel.nanodegree.outbox.UI.FullArticleDetails;

import java.util.ArrayList;

import static android.support.v7.graphics.Palette.from;


public class MyArticleRecyclerViewAdapter extends RecyclerView.Adapter<MyArticleRecyclerViewAdapter.ViewHolder> {

    private final ArrayList<Article> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyArticleRecyclerViewAdapter(ArrayList<Article> articles, OnListFragmentInteractionListener listener) {
        if (articles == null)
            articles = new ArrayList<>();
        mValues = articles;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_article, parent, false);
        // if(mValues.size()==0)
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);

        holder.articleHead.setText(mValues.get(position).getHead());
        Picasso.with(holder.mView.getContext()).load(mValues.get(position).getPhoto())
                .error(R.drawable.loading)
                .placeholder(R.drawable.loading)
                .resize(800, 600)
                .into(holder.image);
        //   setImagePalete(holder);
        //  holder.mContentView.setText(mValues.get(position).content);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentInteraction(new DummyContent.DummyItem("", "", ""));
                }
                Intent intent = new Intent(v.getContext(), FullArticleDetails.class);
                intent.putExtra("position", position);
                intent.putExtra("time", mValues.get(position).getArt_time());
                intent.putExtra("head", mValues.get(position).getHead());
                intent.putExtra("content", mValues.get(position).getContent());
                intent.putExtra("writer_name", mValues.get(position).getWriter_name());
                intent.putExtra("photo_path", mValues.get(position).getPhoto());
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation((MainActivity) v.getContext(), holder.image, "profile");
                v.getContext().startActivity(intent, options.toBundle());
                //   v.getContext().startActivity(intent);
            }
        });
        holder.favIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FavouriteHandler(v.getContext()).saveArticle(mValues.get(position));

            }
        });
    }

    private void setImagePalete(ViewHolder vh) {
        Bitmap b = ((BitmapDrawable) vh.image.getDrawable()).getBitmap();
        Palette.Builder p = Palette.from(b);
        int color = p.generate().getMutedColor(0);
        vh.articleFooter.setBackgroundColor(color);

    }

    @Override
    public int getItemCount() {
        return mValues.size();
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

        @Override
        public String toString() {
            return super.toString() + " '";
        }
    }
}
