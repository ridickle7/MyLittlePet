package ridickle.co.kr.mylittlepet;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.ArrayList;

/**
 * Created by ridickle on 2017. 6. 3..
 */


public class Fragment3ListAdapter extends RecyclerView.Adapter<Fragment3ListAdapter.DogInfoViewHolder> {

    Context context;
    LayoutInflater inflater;
    ArrayList<DogInfo> dogInfoList;

    public Fragment3ListAdapter(Context ctx, ArrayList<DogInfo> list) {
        context = ctx;
        dogInfoList = list;
        inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public DogInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the view for this view holder
        View thisItemsView = inflater.inflate(R.layout.item_main_fragment3_doginfo, parent, false);
        // Call the view holder's constructor, and pass the view to it;
        // return that new view holder
        return new DogInfoViewHolder(thisItemsView);
    }

    @Override
    public void onBindViewHolder(final DogInfoViewHolder holder, int position) {
        DogInfo temp = dogInfoList.get(position);

        // 이미지 처리
        if(!(temp.getAddress().equals(""))){

            Glide.with(context.getApplicationContext()).load(temp.getImgUrl()).into(new SimpleTarget<Drawable>() {
                @Override
                public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                    holder.background.setBackground(resource);
                }
            });


        }

        // 좋아요 없을 경우
        if(temp.getIsGood() == 0)
            holder.goodButton.setBackground(context.getResources().getDrawable(R.mipmap.ic_launcher));
        else{
            holder.goodButton.setBackground(context.getResources().getDrawable(R.mipmap.ic_launcher));

        }

        // 암컷 수컷 (암컷 = 0)
        if(temp.getGender() == 0){
            holder.genderImage.setBackground(context.getResources().getDrawable(R.mipmap.ic_launcher));
        }
        else{
            holder.genderImage.setBackground(context.getResources().getDrawable(R.mipmap.ic_launcher));
        }

        // 강아지 이름
        holder.name.setText(temp.getName());

        // 강아지 주소
        holder.address.setText(temp.getAddress());

        // 거리
        holder.distanceText.setText(String.valueOf(temp.getDistance()));

    }

    @Override
    public int getItemCount() {
        return dogInfoList.size();
    }

    class DogInfoViewHolder extends RecyclerView.ViewHolder{
        protected RelativeLayout background;
        protected ImageButton goodButton;
        protected ImageView genderImage;
        protected TextView name;
        protected TextView address;
        protected ImageView distanceImage;
        protected TextView distanceText;


        public DogInfoViewHolder(View itemView) {
            super(itemView);
            background = (RelativeLayout) itemView.findViewById(R.id.background);
            goodButton = (ImageButton) itemView.findViewById(R.id.goodButton);
            genderImage = (ImageView) itemView.findViewById(R.id.genderImage);
            name = (TextView) itemView.findViewById(R.id.name);
            address = (TextView) itemView.findViewById(R.id.address);
            distanceImage = (ImageView) itemView.findViewById(R.id.distanceImage);
            distanceText = (TextView) itemView.findViewById(R.id.distanceText);
        }
    }
}