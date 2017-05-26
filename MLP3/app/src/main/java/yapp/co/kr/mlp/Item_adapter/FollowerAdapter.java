package yapp.co.kr.mlp.Item_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

import yapp.co.kr.mlp.Item_item.Follower;
import yapp.co.kr.mlp.R;

/**
 * Created by home on 2015-09-05.
 */
public class FollowerAdapter extends ArrayAdapter<Follower> {
    private ArrayList<Follower> list;
    Context context;

    public FollowerAdapter(Context context,
                           int resource, ArrayList<Follower> objects) {
        super(context, resource, objects);
        this.context = context;
        list = objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        final ViewHolder holder;
        if (v == null) {
            LayoutInflater inflater
                    = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_follower, null);

            holder = new ViewHolder();
            holder.image = (ImageView) v.findViewById(R.id.img_profile);
            holder.button = (Button) v.findViewById(R.id.btn_nickname);
            holder.toggleButton = (ToggleButton) v.findViewById(R.id.togglebtn_plus);

            holder.toggleButton.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    if (holder.toggleButton.isChecked()) {
                        holder.toggleButton.setChecked(true);
                        Toast.makeText(context, "position " + position + " is on",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        holder.toggleButton.setChecked(false);
                        Toast.makeText(context, "position " + position + " is off",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        Follower item = list.get(position);


        holder.image.setImageDrawable(item.getProfile());
        holder.button.setText(item.getNickname());
        holder.toggleButton.setText(item.getNickname());

        return v;
    }

    public class ViewHolder {
        ImageView image;
        Button button;
        ToggleButton toggleButton;
    }
}


