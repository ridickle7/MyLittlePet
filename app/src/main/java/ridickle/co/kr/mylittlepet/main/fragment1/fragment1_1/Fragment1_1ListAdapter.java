package ridickle.co.kr.mylittlepet.main.fragment1.fragment1_1;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import ridickle.co.kr.mylittlepet.MyApplication;
import ridickle.co.kr.mylittlepet.R;
import ridickle.co.kr.mylittlepet.main.MainPresenterImpl;
import ridickle.co.kr.mylittlepet.RecyclerViewPresenter;

/**
 * Created by ridickle on 2017. 10. 3..
 */

public class Fragment1_1ListAdapter extends RecyclerView.Adapter<Fragment1_1ListAdapter.MyPageImageViewHolder> {
    ArrayList<Network_MyPageImage> myImageList;
    Context context;
    int flag;
    RecyclerViewPresenter f1_1ListPresenter;

    public Fragment1_1ListAdapter(Context context, int flag) {
        this.context = context;
        this.f1_1ListPresenter = new RecyclerViewPresenter();
        myImageList = f1_1ListPresenter.init();

        if (flag == 0) {
            for (int i = 0; i < 5; i++)    // Dummy 값
                myImageList = RecyclerViewPresenter.add(myImageList, new Network_MyPageImage());
        } else if (flag == 1) {
            for (int i = 0; i < 5; i++)    // Dummy 값
                myImageList = RecyclerViewPresenter.add(myImageList, new Network_MyPageImage());
        }
    }

    @Override
    public MyPageImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_main_fragment1_1, parent, false);
        MyPageImageViewHolder viewHolder = new MyPageImageViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyPageImageViewHolder holder, int position) {
        Network_MyPageImage temp = myImageList.get(position);

        if (flag == 0)
            RecyclerViewPresenter.setImage(context, holder.image, temp.getImageURL(), MainPresenterImpl.MAINACTIVITY_FRAGMENT1_1);

        else if (flag == 1){
            RecyclerViewPresenter.setImage(context, holder.image, temp.getImageURL(), MainPresenterImpl.MAINACTIVITY_FRAGMENT1_1);
        }
    }

    class MyPageImageViewHolder extends RecyclerView.ViewHolder {
        ImageView image;

        public MyPageImageViewHolder(View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.f1_1Image);
            GridLayoutManager.LayoutParams params = (GridLayoutManager.LayoutParams) itemView.getLayoutParams();
            params.width = MyApplication.width / 3;
            params.height = MyApplication.width / 3;
        }
    }

    @Override
    public int getItemCount() {
        return myImageList.size();
    }
}
