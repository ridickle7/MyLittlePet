package ridickle.co.kr.mylittlepet.main.fragment3.fragment3_1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import java.util.ArrayList;

import ridickle.co.kr.mylittlepet.MyApplication;
import ridickle.co.kr.mylittlepet.MySharedPreference;
import ridickle.co.kr.mylittlepet.Network.DataBody.Network_Content;
import ridickle.co.kr.mylittlepet.R;
import ridickle.co.kr.mylittlepet.Util.RecyclerViewPresenter;
import ridickle.co.kr.mylittlepet.detailContent.DetailContentActivity;

/**
 * Created by ridickle on 2017. 10. 3..
 */

public class Fragment3_1ListAdapter extends RecyclerView.Adapter<Fragment3_1ListAdapter.MyPageImageViewHolder> implements ItemClickListener {
    ArrayList<Network_Content> myImageList;
    Context context;
    int flag;
    RecyclerViewPresenter f3_1ListPresenter;
    private final AdapterView.OnItemClickListener listener = null;

    public Fragment3_1ListAdapter(Context context, int flag, ArrayList<Network_Content> list) {
        this.context = context;
        this.f3_1ListPresenter = new RecyclerViewPresenter();
        myImageList = list;
    }

    @Override
    public MyPageImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_main_fragment3_1, parent, false);
        MyPageImageViewHolder viewHolder = new MyPageImageViewHolder(itemView, this);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyPageImageViewHolder holder, int position) {
        Network_Content temp = myImageList.get(position);
        MyApplication.setImage(context, holder.image, temp.getcImageURL());
    }

    @Override
    public void onItemClick(int position) {
        MySharedPreference.getDefaultInstance().setData(MySharedPreference.CONTENT_ID, myImageList.get(position).get_id());
        Intent intent = new Intent(context.getApplicationContext(), DetailContentActivity.class);
        context.startActivity(intent);
    }

    static class MyPageImageViewHolder extends RecyclerView.ViewHolder {
        ImageView image;

        public MyPageImageViewHolder(View itemView, final ItemClickListener itemClickListener) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.f3_1Image);
            GridLayoutManager.LayoutParams params = (GridLayoutManager.LayoutParams) itemView.getLayoutParams();
            params.width = MyApplication.width / 3;
            params.height = MyApplication.width / 3;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.onItemClick(getAdapterPosition());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return myImageList.size();
    }
}
