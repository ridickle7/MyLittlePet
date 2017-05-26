package yapp.co.kr.mlp.Item_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import yapp.co.kr.mlp.Item_item.Boast;
import yapp.co.kr.mlp.R;

/**
 * Created by hayoung on 2015-08-29.
 */
public class BoastAdapter extends ArrayAdapter<Boast> {
    Context context;
    private ArrayList<Boast> list;

    public BoastAdapter(Context context,
                        int resource, ArrayList<Boast> objects) {
        super(context, resource, objects);
        this.context = context;
        list = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder holder;
        if (v == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_boast, null);

            holder = new ViewHolder();
            holder.imageview = (ImageView) v.findViewById(R.id.btn_firstrankimage);
            holder.progressing = (ImageView) v.findViewById(R.id.progressing);
            holder.term = (TextView) v.findViewById(R.id.term);
            holder.missiontext = (TextView) v.findViewById(R.id.txt_mission);

            v.setTag(holder);
        }

        else {
            holder = (ViewHolder) v.getTag();
        }

        Boast item = list.get(position);

        holder.imageview.setImageDrawable(item.getFirstrank());
        holder.progressing.setImageDrawable(item.getProgressing());
        holder.term.setText(item.getTerm());
        holder.missiontext.setText(item.getMission());

        return v;
    }

    public class ViewHolder{
        ImageView imageview;
        ImageView progressing;
        TextView term;
        TextView missiontext;
    }
}