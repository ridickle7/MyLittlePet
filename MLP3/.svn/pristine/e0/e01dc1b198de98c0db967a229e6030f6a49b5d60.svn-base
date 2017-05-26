package yapp.co.kr.mlp.Item_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import yapp.co.kr.mlp.Item_item.Pet;
import yapp.co.kr.mlp.R;

/**
 * Created by home on 2015-08-28.
 */
public class MainItemAdapter extends ArrayAdapter<Pet> {

    private Context ctx;
    private int resource;
    private List<Pet> data;
    private LayoutInflater mInflater;

    public MainItemAdapter(Context context, int resource, List<Pet> data) {
        super(context, resource, data); //ArrayAdapter에 있는 생성자 선언 메소드(?)를 super을 통해 얻어온다.
        ctx = context;
        this.resource = resource;
        this.data = data;
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(resource, null);

            holder = new ViewHolder();
            holder.image = (ImageView) convertView.findViewById(R.id.image);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.gender = (TextView) convertView.findViewById(R.id.gender);
            holder.birthday = (TextView) convertView.findViewById(R.id.birthday);
            holder.specific = (TextView) convertView.findViewById(R.id.specific);
            holder.introduction_write = (TextView) convertView.findViewById(R.id.introduction_write);

            holder.image.setFocusable(false);

            convertView.setTag(holder);
        }

        // 캐시된 뷰가 있을 경우 저장된 뷰홀더를 사용한다
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.image.setImageResource(R.mipmap.photo);
        holder.name.setText(data.get(position).getName().toString());
        holder.gender.setText(data.get(position).getGender().toString());
        holder.birthday.setText(data.get(position).getBirthday().toString());
        holder.specific.setText(data.get(position).getSpecific().toString());
        holder.introduction_write.setText(data.get(position).getIntroduction_write().toString());

        return convertView;
    }

    public class ViewHolder {
        public ImageView image;
        public TextView name;
        public TextView gender;
        public TextView birthday;
        public TextView specific;
        public TextView introduction_write;
    }
}