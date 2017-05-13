package salah.basel.nanodegree.outbox.UI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import salah.basel.nanodegree.outbox.MainActivity;
import salah.basel.nanodegree.outbox.R;

/**
 * Created by Basel on 30/04/2017.
 */

public class DrawerAdapter extends BaseAdapter {
    ArrayList<DrawerItem> items;
    Context context;


    public DrawerAdapter(ArrayList<DrawerItem> items,Context context)
    {
        this.items=items;
        this.context=context;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.navigation_view_item, viewGroup, false);
        TextView textView = (TextView) rowView.findViewById(R.id.navi_menuitem);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon_image);
        textView.setText(items.get(i).getItemName());
        imageView.setImageResource(items.get(i).getImgResID());

        return rowView;
    }
}
