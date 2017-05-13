package salah.basel.nanodegree.outbox.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import salah.basel.nanodegree.outbox.Model.Edition;
import salah.basel.nanodegree.outbox.R;

import java.util.ArrayList;

public class MyEditionRecyclerViewAdapter extends RecyclerView.Adapter<MyEditionRecyclerViewAdapter.ViewHolder> {

    ArrayList<Edition> editions;
    public MyEditionRecyclerViewAdapter(ArrayList<Edition> editions) {
        this.editions=editions;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_edition, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.editionNumber.setText(editions.get(position).getNumber());
        holder.editionTitle.setText(editions.get(position).getName());
        Picasso.with(holder.mView.getContext()).load(editions.get(position).getPhoto())
                .error(R.drawable.loading)
                .placeholder(R.drawable.loading)
                .resize(800,600)
                .into(holder.editionImage);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }

    @Override
    public int getItemCount() {
        return editions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView editionTitle;
        public final TextView editionNumber;
        public ImageView editionImage;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            editionTitle = (TextView) view.findViewById(R.id.edition_title);
            editionNumber = (TextView) view.findViewById(R.id.edition_number);
            editionImage= (ImageView) view.findViewById(R.id.edition_image);
        }

        @Override
        public String toString() {
            return super.toString() + " '" ;
        }
    }
}
