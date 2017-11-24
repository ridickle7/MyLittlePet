package ridickle.co.kr.mylittlepet.main.fragment3;

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

public class MainFragment3 extends Fragment implements MainF3Presenter.fragment {
    static MainFragment3 instance;
    MainF3Presenter mPresenter;

    View convertView;
    TabLayout mf3TabLayout;
    ImageView f3ImageURL;
    TextView f3Nickname, f3Introduce, f3LeftText, f3LeftNum, f3CenterText, f3CenterNum, f3RightText, f3RightNum;

    private ViewPager mf3ViewPager;
    private Fragment3TabAdapter vpa;

    public MainFragment3() {
        // Required empty public constructor
        mPresenter = MainF3PresenterImpl.getInstance(this);
    }

    public static MainFragment3 getInstance() {
        if (instance == null)
            instance = new MainFragment3();
        return instance;
    }

    // 게시물 업데이트를 위해 활용
    public void setF3LeftNumText(String inputData) {
        f3LeftNum.setText(inputData);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        convertView = inflater.inflate(R.layout.fragment_main_fragment3, container, false);
        mPresenter.loadItem();

        return convertView;
    }

    @Override
    public void updateView(Network_User user) {
        MyApplication.setUser(user);

        mf3TabLayout = (TabLayout) convertView.findViewById(R.id.f3Tab);
        mf3ViewPager = (ViewPager) convertView.findViewById(R.id.f3viewPager);

        mf3TabLayout = mPresenter.tabSetting(mf3TabLayout);

        vpa = new Fragment3TabAdapter(getChildFragmentManager());
        mf3ViewPager.setAdapter(vpa);
        mf3TabLayout.setupWithViewPager(mf3ViewPager);
        for (int i = 0; i < mf3TabLayout.getTabCount(); i++) {
            mf3TabLayout.getTabAt(i).setIcon(R.mipmap.ic_launcher_round);
        }


        f3ImageURL = (ImageView) convertView.findViewById(R.id.f3imageURL);
        f3Nickname = (TextView) convertView.findViewById(R.id.f3Nickname);
        f3Introduce = (TextView) convertView.findViewById(R.id.f3Introduce);
        f3LeftText = (TextView) convertView.findViewById(R.id.f3Left).findViewById(R.id.f3ItemText);
        f3LeftNum = (TextView) convertView.findViewById(R.id.f3Left).findViewById(R.id.f3ItemNum);
        f3CenterText = (TextView) convertView.findViewById(R.id.f3Center).findViewById(R.id.f3ItemText);
        f3CenterNum = (TextView) convertView.findViewById(R.id.f3Center).findViewById(R.id.f3ItemNum);
        f3RightText = (TextView) convertView.findViewById(R.id.f3Right).findViewById(R.id.f3ItemText);
        f3RightNum = (TextView) convertView.findViewById(R.id.f3Right).findViewById(R.id.f3ItemNum);

        MyApplication.setImage(getActivity(), f3ImageURL, user.getuImageURL());
        f3Nickname.setText(user.getuNickname());
        f3Introduce.setText(user.getuIntroduce());
        f3LeftText.setText("게시물");
        f3LeftNum.setText(0 + "");
        f3CenterText.setText("팔로잉");
        f3CenterNum.setText("" + MyApplication.ifNull(user.getuFollowingList()).size());
        f3RightText.setText("팔로워");
        f3RightNum.setText("" + MyApplication.ifNull(user.getuFollowerList()).size());
    }
}
