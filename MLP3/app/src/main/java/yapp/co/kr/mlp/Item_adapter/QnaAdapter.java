package yapp.co.kr.mlp.Item_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import yapp.co.kr.mlp.Item_item.Qna;
import yapp.co.kr.mlp.R;

/**
 * Created by home on 2015-08-29.
 */
public class QnaAdapter extends ArrayAdapter<Qna> {

    private Context ctx;
    private int resource;
    private List<Qna> data;
    private LayoutInflater mInflater;

    public QnaAdapter(Context context, int resource, ArrayList<Qna> data) {
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
            holder.question = (TextView) convertView.findViewById(R.id.question);
            convertView.setTag(holder);
        }

        // 캐시된 뷰가 있을 경우 저장된 뷰홀더를 사용한다
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.question.setText(data.get(position).getQuestion().toString());

        return convertView;
    }

    public class ViewHolder {
        public TextView id;
        public TextView question;
    }
}