package salah.basel.nanodegree.outbox.UI;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import salah.basel.nanodegree.outbox.Adapters.WritersRecycleAdapter;

import salah.basel.nanodegree.outbox.Model.Writer;
import salah.basel.nanodegree.outbox.R;
import salah.basel.nanodegree.outbox.parser.FireBaseRequestor;


public class WritersFragment extends Fragment {

    RecyclerView recyclerView;

    public WritersFragment() {
    }


    public static WritersFragment newInstance() {
        WritersFragment fragment = new WritersFragment();
        Bundle args = new Bundle();
   //     args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
     //       mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.writers_fragment_list, container, false);

        Context context = view.getContext();
             recyclerView = (RecyclerView) view.findViewById(R.id.list);
                recyclerView.setLayoutManager(new LinearLayoutManager(context));

           new FireBaseRequestor().getWriterData(this);

        return view;
    }


    public void updateAdapter(ArrayList<Writer> writers) {
        recyclerView.setAdapter(new WritersRecycleAdapter(getActivity(),writers));
    }
}
