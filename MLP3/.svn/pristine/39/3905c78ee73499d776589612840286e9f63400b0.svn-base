package yapp.co.kr.mlp.Item_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

import yapp.co.kr.mlp.Item_item.ListItem02;
import yapp.co.kr.mlp.R;

/**
 * Created by hayoung on 2015-08-29.
 */
public class BoastSubAdapter extends ArrayAdapter<ListItem02> {

    private ArrayList<ListItem02> list02;
    Context context;

    public BoastSubAdapter(Context context, int resource, ArrayList<ListItem02> objects) {
        super(context, resource, objects);
        this.context = context;
        list02 = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder holder;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_sub, null);

            holder = new ViewHolder();
            holder.detailimage = (ImageView) v.findViewById(R.id.img_sub);
            holder.nickname = (Button) v.findViewById(R.id.btn_sub_nickname);

            v.setTag(holder);

        }

        else{
            holder = (ViewHolder) v.getTag();
        }

        ListItem02 item02 = list02.get(position);

        holder.detailimage = (ImageView) v.findViewById(R.id.img_sub);
        holder.nickname = (Button) v.findViewById(R.id.btn_sub_nickname);

        holder.detailimage.setImageDrawable(item02.getDetailimage());
        holder.nickname.setText(item02.getNickname());

        return v;
    }

    public class ViewHolder {
        ImageView detailimage;
        Button nickname;
    }
}
