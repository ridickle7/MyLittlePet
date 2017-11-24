package ridickle.co.kr.mylittlepet.main.fragment1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import ridickle.co.kr.mylittlepet.MyApplication;
import ridickle.co.kr.mylittlepet.MySharedPreference;
import ridickle.co.kr.mylittlepet.Network.DataBody.Network_User;
import ridickle.co.kr.mylittlepet.R;

/**
 * Created by ridickle on 2017. 6. 3..
 */


public class Fragment1ListAdapter extends RecyclerView.Adapter<Fragment1ListAdapter.DogInfoViewHolder> {

    Context context;
    LayoutInflater inflater;
    ArrayList<Network_User> userList;

    public Fragment1ListAdapter(Context ctx, ArrayList<Network_User> list) {
        context = ctx;
        userList = list;
        inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public DogInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the view for this view holder
        View thisItemsView = inflater.inflate(R.layout.item_main_fragment1_doginfo, parent, false);
        // Call the view holder's constructor, and pass the view to it;
        // return that new view holder
        return new DogInfoViewHolder(thisItemsView);
    }

    @Override
    public void onBindViewHolder(final DogInfoViewHolder holder, int position) {
        Network_User userItem = userList.get(position);

        MyApplication.setImage(context, holder.background, userItem.getuImageURL());

        // 좋아요 없을 경우
        if(userItem.getuFollowingList().indexOf(MySharedPreference.getDefaultInstance().getStringData(MySharedPreference.USER_ID)) == 0)
            holder.goodButton.setBackground(context.getResources().getDrawable(R.mipmap.ic_launcher));
        else{
            holder.goodButton.setBackground(context.getResources().getDrawable(R.mipmap.ic_launcher));

        }

        // 암컷 수컷 (암컷 = true)
        if(userItem.getuGender()){
            holder.genderImage.setBackground(context.getResources().getDrawable(R.mipmap.ic_launcher));
        }
        else{
            holder.genderImage.setBackground(context.getResources().getDrawable(R.mipmap.ic_launcher));
        }

        // 강아지 이름
        holder.name.setText(userItem.getuNickname());

        // 강아지 주소
        holder.address.setText(userItem.getuAddress());

        // 거리
//        holder.distanceText.setText(String.valueOf(userItem.getDistance()));

    }

    @Override
    public int getItemCount() {
        return userList.size();
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