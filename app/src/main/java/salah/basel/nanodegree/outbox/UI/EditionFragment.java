package salah.basel.nanodegree.outbox.UI;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import salah.basel.nanodegree.outbox.Adapters.MyEditionRecyclerViewAdapter;
import salah.basel.nanodegree.outbox.Model.Edition;
import salah.basel.nanodegree.outbox.R;
import salah.basel.nanodegree.outbox.UI.dummy.DummyContent.DummyItem;
import salah.basel.nanodegree.outbox.parser.FireBaseRequestor;

import java.util.ArrayList;


public class EditionFragment extends Fragment {

    private OnListFragmentInteractionListener mListener;
    RecyclerView recyclerView;
    public EditionFragment() {
    }


    public static EditionFragment newInstance(int columnCount) {
        EditionFragment fragment = new EditionFragment();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edition_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
             recyclerView = (RecyclerView) view;

                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            new FireBaseRequestor().getEditionData(this);
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public void updateAdapter(ArrayList<Edition> articles)

    {
        recyclerView.setAdapter(new MyEditionRecyclerViewAdapter(articles));
    }
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyItem item);
    }
}
