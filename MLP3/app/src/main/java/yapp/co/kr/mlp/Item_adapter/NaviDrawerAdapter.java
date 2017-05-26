package yapp.co.kr.mlp.Item_adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import yapp.co.kr.mlp.Item_item.Nav_list;
import yapp.co.kr.mlp.R;

/**
 * Created by home on 2015-10-10.
 */
public class NaviDrawerAdapter extends ArrayAdapter<Nav_list> {

    Context context;
    private ArrayList<Nav_list> list;

    public NaviDrawerAdapter(Context context, int resource, ArrayList<Nav_list> objects) {
        super(context, resource, objects);
        this.context = context;
        list = objects;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup parent) {
        View v = convertview;
        ViewHolder holder;
        if (v == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_navidrawer, null);

            holder = new ViewHolder();
            holder.main_image = (ImageView) v.findViewById(R.id.main_image);
            holder.main_text = (TextView) v.findViewById(R.id.main_text);

            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        Nav_list item = list.get(position);
        Log.d("res : ", item.getMain_icon() + "");
        Log.d("res : ", item.getMain_string() + "");

        holder.main_image.setImageDrawable(item.getMain_icon());
        holder.main_text.setText(item.getMain_string());

        return v;
    }

    public class ViewHolder {
        ImageView main_image;
        TextView main_text;
    }
}