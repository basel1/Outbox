package salah.basel.nanodegree.outbox.Adapters;

import android.content.Context;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import salah.basel.nanodegree.outbox.Logging.L;
import salah.basel.nanodegree.outbox.Model.Writer;
import salah.basel.nanodegree.outbox.R;

public class WritersRecycleAdapter extends RecyclerView.Adapter<WritersRecycleAdapter.ViewHolder> implements AdapterView.OnItemClickListener, View.OnClickListener {

    ArrayList<Writer> Writers;
    Context context;

    public WritersRecycleAdapter(FragmentActivity activity, ArrayList<Writer> writers) {

        Writers = writers;
        this.context = activity.getBaseContext();
        if (Writers == null) {
            Writers = new ArrayList<>();
            Writer s = new Writer();
            s.setName("Waiting for network connection");

            Writers.add(s);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.writer_list_item, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.sp_name.setText(Writers.get(position).getName());
        holder.sp_job.setText((Writers.get(position).getSection()));
        if (Writers.get(position).getPhoto() != "")
            Picasso.with(context).load(Writers.get(position).getPhoto())
                    .error(R.drawable.writericon)
                    .placeholder(R.drawable.writericon)
                    .into(holder.sp_image);
        holder.view.setId(position);

    }


    @Override
    public int getItemCount() {
        return Writers.size();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onClick(View v) {
        int position = v.getId();
        L.m("position " + position);

       /* Intent intent=new Intent(context, WriterDetails.class);
        intent.putExtra("position",position);
context.startActivity(intent);*/
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView sp_name;
        TextView sp_job;
        ImageView sp_image;
        View view;

        public ViewHolder(View view) {
            super(view);
            sp_image = (ImageView) view.findViewById(R.id.thumb);
            sp_name = (TextView) view.findViewById(R.id.sp_name);
            sp_job = (TextView) view.findViewById(R.id.sp_job);
            this.view = view;
        }


    }
}
