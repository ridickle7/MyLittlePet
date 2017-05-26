package yapp.co.kr.mlp.Item_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import yapp.co.kr.mlp.Item_item.Boast;
import yapp.co.kr.mlp.R;

/**
 * Created by home on 2015-08-30.
 */
public class NavigationAdapter extends ArrayAdapter<Boast> {
    Context context;
    private ArrayList<Boast> list;

    public NavigationAdapter(Context context,
                         int resource, ArrayList<Boast> objects) {
        super(context, resource, objects);
        this.context = context;
        list = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_boast, null);
        }

        return v;
    }
}