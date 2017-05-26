package yapp.co.kr.mlp.Item_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import yapp.co.kr.mlp.Item_item.Comment;
import yapp.co.kr.mlp.R;

/**
 * Created by home on 2015-10-30.
 */
public class CommentAdapter extends ArrayAdapter<Comment> {
    Context context;
    int resource;
    ArrayList<Comment> list;

    public CommentAdapter(Context context, int resource, ArrayList<Comment> data){
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        list = data;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        final ViewHolder holder;
        if (v == null) {
            LayoutInflater inflater
                    = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_comment, null);

            holder = new ViewHolder();
            holder.profile = (ImageButton) v.findViewById(R.id.profile);
            holder.date = (TextView) v.findViewById(R.id.date);
            holder.nickname = (TextView) v.findViewById(R.id.nickname);
            holder.content = (TextView) v.findViewById(R.id.content);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        Comment item = list.get(position);


        holder.profile.setImageDrawable(item.getProfile());
        holder.date.setText(item.getDate());
        holder.nickname.setText(item.getNickname());
        holder.content.setText(item.getContent());

        return v;
    }

    public class ViewHolder {
        ImageButton profile;
        TextView nickname;
        TextView date;
        TextView content;
    }
}


