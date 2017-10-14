package ridickle.co.kr.mylittlepet.main.fragment1.fragment1_3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import ridickle.co.kr.mylittlepet.Network.DataBody.Network_User;
import ridickle.co.kr.mylittlepet.R;

/**
 * Created by ridickle on 2017. 9. 30..
 */

public class MainF1Fragment3 extends Fragment implements MainF1_3Presenter.fragment {
    static MainF1Fragment3 instance;
    MainF1_3Presenter mPresenter;

    View convertView = null;
    TextView f1_3InfoGender, f1_3InfoBirth, f1_3InfoSpecify, f1_3InfoWeight, f1_3InfoAddress;
    private LinearLayout f1_3TagList;


    public MainF1Fragment3() {
        // Required empty public constructor
        mPresenter = MainF1_3PresenterImpl.getInstance(this);
    }

    public static MainF1Fragment3 newInstance() {
        if (instance == null)
            instance = new MainF1Fragment3();
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        convertView = inflater.inflate(R.layout.fragment_tab1_fragment3, container, false);

        mPresenter.loadItem();
        return convertView;
    }

    @Override
    public void updateView(Network_User user) {
        f1_3TagList = (LinearLayout) convertView.findViewById(R.id.f1_3TagList);

        f1_3InfoGender = (TextView) convertView.findViewById(R.id.f1_3InfoGender).findViewById(R.id.f1_3InfoName);
        ((TextView)convertView.findViewById(R.id.f1_3InfoGender).findViewById(R.id.f1_3InfoTitle)).setText("성별");
        f1_3InfoBirth = (TextView) convertView.findViewById(R.id.f1_3InfoBirth).findViewById(R.id.f1_3InfoName);
        ((TextView) convertView.findViewById(R.id.f1_3InfoBirth).findViewById(R.id.f1_3InfoTitle)).setText("생일");
        f1_3InfoSpecify = (TextView) convertView.findViewById(R.id.f1_3InfoSpecify).findViewById(R.id.f1_3InfoName);
        ((TextView) convertView.findViewById(R.id.f1_3InfoSpecify).findViewById(R.id.f1_3InfoTitle)).setText("품종");
        f1_3InfoWeight = (TextView) convertView.findViewById(R.id.f1_3InfoWeight).findViewById(R.id.f1_3InfoName);
        ((TextView) convertView.findViewById(R.id.f1_3InfoWeight).findViewById(R.id.f1_3InfoTitle)).setText("몸무게");
        f1_3InfoAddress = (TextView) convertView.findViewById(R.id.f1_3InfoAddress).findViewById(R.id.f1_3InfoName);
        ((TextView) convertView.findViewById(R.id.f1_3InfoAddress).findViewById(R.id.f1_3InfoTitle)).setText("지역");

        ArrayList<Integer> tagIdList = mPresenter.setTag(f1_3TagList);
        for (int i = 0; i < tagIdList.size(); i++) {
            final TextView tagItem = (TextView) convertView.findViewById(tagIdList.get(i));
            tagItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Toast.makeText(getActivity(), tagItem.getId() + "", Toast.LENGTH_SHORT).show();
                    // 클릭 시 태그 관련 이미지 띄워주는 화면으로 intent
                    String tagText = tagItem.getText() + "";
                }
            });
        }

        f1_3InfoGender.setText(user.getuGender() ? "암컷" : "수컷");
        f1_3InfoBirth.setText(user.getuAge() + "");
        f1_3InfoSpecify.setText(user.getuSpecify());
        f1_3InfoWeight.setText(user.getuWeight() == 0 ? "소형" : (user.getuWeight() == 1) ? "중형" : "대형");
        f1_3InfoAddress.setText(user.getuAddress());
    }
}
