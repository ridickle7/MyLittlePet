package ridickle.co.kr.mylittlepet.main.fragment1;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import ridickle.co.kr.mylittlepet.MyApplication;
import ridickle.co.kr.mylittlepet.Network.DataBody.Network_User;
import ridickle.co.kr.mylittlepet.R;

public class MainFragment1 extends Fragment implements MainF1Presenter.fragment {
    static MainFragment1 instance;
    MainF1Presenter mPresenter;

    View convertView;
    TabLayout mF1TabLayout;
    ImageView f1ImageURL;
    TextView f1Nickname, f1Introduce, f1LeftText, f1LeftNum, f1CenterText, f1CenterNum, f1RightText, f1RightNum;

    private ViewPager mF1ViewPager;
    private Fragment1TabAdapter vpa;

    public MainFragment1() {
        // Required empty public constructor
        mPresenter = MainF1PresenterImpl.getInstance(this);
    }

    public static MainFragment1 getInstance() {
        if (instance == null)
            instance = new MainFragment1();
        return instance;
    }

    // 게시물 업데이트를 위해 활용
    public void setF1LeftNumText(String inputData) {
        f1LeftNum.setText(inputData);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        convertView = inflater.inflate(R.layout.fragment_main_fragment1, container, false);
        mPresenter.loadItem();

        return convertView;
    }

    @Override
    public void updateView(Network_User user) {
        MyApplication.setUser(user);

        mF1TabLayout = (TabLayout) convertView.findViewById(R.id.f1Tab);
        mF1ViewPager = (ViewPager) convertView.findViewById(R.id.f1viewPager);

        mF1TabLayout = mPresenter.tabSetting(mF1TabLayout);

        vpa = new Fragment1TabAdapter(getChildFragmentManager());
        mF1ViewPager.setAdapter(vpa);
        mF1TabLayout.setupWithViewPager(mF1ViewPager);

        f1ImageURL = (ImageView) convertView.findViewById(R.id.f1imageURL);
        f1Nickname = (TextView) convertView.findViewById(R.id.f1Nickname);
        f1Introduce = (TextView) convertView.findViewById(R.id.f1Introduce);
        f1LeftText = (TextView) convertView.findViewById(R.id.f1Left).findViewById(R.id.f1ItemText);
        f1LeftNum = (TextView) convertView.findViewById(R.id.f1Left).findViewById(R.id.f1ItemNum);
        f1CenterText = (TextView) convertView.findViewById(R.id.f1Center).findViewById(R.id.f1ItemText);
        f1CenterNum = (TextView) convertView.findViewById(R.id.f1Center).findViewById(R.id.f1ItemNum);
        f1RightText = (TextView) convertView.findViewById(R.id.f1Right).findViewById(R.id.f1ItemText);
        f1RightNum = (TextView) convertView.findViewById(R.id.f1Right).findViewById(R.id.f1ItemNum);

        MyApplication.setImage(getActivity(), f1ImageURL, user.getuImageURL());
        f1Nickname.setText(user.getuNickname());
        f1Introduce.setText(user.getuIntroduce());
        f1LeftText.setText("게시물");
        f1LeftNum.setText(0 + "");
        f1CenterText.setText("팔로잉");
        f1CenterNum.setText("" + MyApplication.ifNull(user.getuFollowingList()).size());
        f1RightText.setText("팔로워");
        f1RightNum.setText("" + MyApplication.ifNull(user.getuFollowerList()).size());
    }
}
