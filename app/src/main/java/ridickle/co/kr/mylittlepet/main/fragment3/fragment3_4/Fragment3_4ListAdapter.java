package ridickle.co.kr.mylittlepet.main.fragment3.fragment3_4;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ridickle.co.kr.mylittlepet.MyApplication;
import ridickle.co.kr.mylittlepet.R;
import ridickle.co.kr.mylittlepet.Util.RecyclerViewPresenter;

/**
 * Created by ridickle on 2017. 10. 3..
 */

public class Fragment3_4ListAdapter extends RecyclerView.Adapter<Fragment3_4ListAdapter.PopularViewHolder> {
    Context context;
    ArrayList<Network_Popular> popularList;

    public Fragment3_4ListAdapter(Context context){
        this.context = context;
        popularList = RecyclerViewPresenter.init();

        for (int i = 0; i < 5; i++)    // Dummy 값
            popularList = RecyclerViewPresenter.add(popularList, new Network_Popular("https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcTW68XNUfjWy2DrPBa65WhxCC7AlOi4VRVmEorGupckpHCuuQn41pzEEF8", "이 달의 드립상" + i, "나는 최강의 드릴이다" + i));
    }

    public class PopularViewHolder extends RecyclerView.ViewHolder {
        ImageView logoImage;
        TextView popularTitle;
        TextView popularName;

        public PopularViewHolder(View itemView) {
            super(itemView);
            logoImage = (ImageView) itemView.findViewById(R.id.f3_4LogoImage);
            popularTitle = (TextView) itemView.findViewById(R.id.f3_4PopularTitle);
            popularName = (TextView) itemView.findViewById(R.id.f3_4PopularName);
        }
    }

    public PopularViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_tab3_fragment4, parent, false);
        PopularViewHolder viewHolder = new PopularViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PopularViewHolder holder, int position) {
        Network_Popular item = popularList.get(position);

        MyApplication.setImage(context, holder.logoImage, item.getLogoImageURL());
        holder.popularTitle.setText(item.getTitle());
        holder.popularName.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        return popularList.size();
    }
}
