package ridickle.co.kr.mylittlepet.main.fragment5;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

import ridickle.co.kr.mylittlepet.MyApplication;
import ridickle.co.kr.mylittlepet.Network.DataBody.Network_Event;
import ridickle.co.kr.mylittlepet.R;
import ridickle.co.kr.mylittlepet.Util.RecyclerViewPresenter;
import ridickle.co.kr.mylittlepet.detailEvent.DetailEventActivity;

/**
 * Created by ridickle on 2017. 10. 3..
 */

public class Fragment5ListAdapter extends RecyclerView.Adapter<Fragment5ListAdapter.PopularEventHolder> {
    Context context;
    ArrayList<Network_Event> popularList;

    public Fragment5ListAdapter(Context context) {
        this.context = context;
        popularList = RecyclerViewPresenter.init();
    }

    public Fragment5ListAdapter(Context context, ArrayList<Network_Event> list) {
        this.context = context;
        popularList = list;
    }

    public class PopularEventHolder extends RecyclerView.ViewHolder {
        ImageView f5Star;
        TextView f5IsEnded;
        ImageView f5Image;
        TextView f5Title;
        TextView f5Term;

        public PopularEventHolder(View itemView) {
            super(itemView);
            f5Star = (ImageView) itemView.findViewById(R.id.f5Star);
            f5IsEnded = (TextView) itemView.findViewById(R.id.f5IsEnded);
            f5Image = (ImageView) itemView.findViewById(R.id.f5Image);
            f5Title = (TextView) itemView.findViewById(R.id.f5Title);
            f5Term = (TextView) itemView.findViewById(R.id.f5Term);
        }
    }

    public PopularEventHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_main_fragment5, parent, false);
        PopularEventHolder viewHolder = new PopularEventHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PopularEventHolder holder, int position) {
        final Network_Event item = popularList.get(position);

        if (new Date().compareTo(new Date(item.geteEndDate())) > 0) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                holder.f5Star.setBackground(context.getDrawable(R.mipmap.ic_launcher));
            } else {
                holder.f5Star.setBackground(context.getResources().getDrawable(R.mipmap.ic_launcher));
            }
            holder.f5IsEnded.setText("마감된 투표");
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                holder.f5Star.setBackground(context.getDrawable(R.mipmap.ic_launcher));
            } else {
                holder.f5Star.setBackground(context.getResources().getDrawable(R.mipmap.ic_launcher));
            }
            holder.f5IsEnded.setText("진행 중인 투표");
        }

        MyApplication.setImage(context, holder.f5Image, item.geteImageURL());
        holder.f5Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailEventActivity.class);
                intent.putExtra("eId", item.get_id());
                ((Activity)context).startActivity(intent);
            }
        });
        holder.f5Title.setText(item.geteName());
        holder.f5Term.setText(MyApplication.getFormattedDate(item.geteStartDate()) + " ~ " + MyApplication.getFormattedDate(item.geteEndDate()));
    }

    @Override
    public int getItemCount() {
        return popularList.size();
    }
}