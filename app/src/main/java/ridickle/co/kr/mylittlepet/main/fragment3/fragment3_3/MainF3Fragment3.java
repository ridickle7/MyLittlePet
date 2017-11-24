package ridickle.co.kr.mylittlepet.main.fragment3.fragment3_3;

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

public class MainF3Fragment3 extends Fragment implements MainF3_3Presenter.fragment {
    static MainF3Fragment3 instance;
    MainF3_3Presenter mPresenter;

    View convertView = null;
    TextView f3_3InfoGender, f3_3InfoBirth, f3_3InfoSpecify, f3_3InfoWeight, f3_3InfoAddress;
    private LinearLayout f3_3TagList;


    public MainF3Fragment3() {
        // Required empty public constructor
        mPresenter = MainF3_3PresenterImpl.getInstance(this);
    }

    public static MainF3Fragment3 newInstance() {
        if (instance == null)
            instance = new MainF3Fragment3();
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        convertView = inflater.inflate(R.layout.fragment_tab3_fragment3, container, false);

        mPresenter.loadItem();
        return convertView;
    }

    @Override
    public void updateView(Network_User user) {
        f3_3TagList = (LinearLayout) convertView.findViewById(R.id.f3_3TagList);

        f3_3InfoGender = (TextView) convertView.findViewById(R.id.f3_3InfoGender).findViewById(R.id.f3_3InfoName);
        ((TextView)convertView.findViewById(R.id.f3_3InfoGender).findViewById(R.id.f3_3InfoTitle)).setText("성별");
        f3_3InfoBirth = (TextView) convertView.findViewById(R.id.f3_3InfoBirth).findViewById(R.id.f3_3InfoName);
        ((TextView) convertView.findViewById(R.id.f3_3InfoBirth).findViewById(R.id.f3_3InfoTitle)).setText("생일");
        f3_3InfoSpecify = (TextView) convertView.findViewById(R.id.f3_3InfoSpecify).findViewById(R.id.f3_3InfoName);
        ((TextView) convertView.findViewById(R.id.f3_3InfoSpecify).findViewById(R.id.f3_3InfoTitle)).setText("품종");
        f3_3InfoWeight = (TextView) convertView.findViewById(R.id.f3_3InfoWeight).findViewById(R.id.f3_3InfoName);
        ((TextView) convertView.findViewById(R.id.f3_3InfoWeight).findViewById(R.id.f3_3InfoTitle)).setText("몸무게");
        f3_3InfoAddress = (TextView) convertView.findViewById(R.id.f3_3InfoAddress).findViewById(R.id.f3_3InfoName);
        ((TextView) convertView.findViewById(R.id.f3_3InfoAddress).findViewById(R.id.f3_3InfoTitle)).setText("지역");

        ArrayList<Integer> tagIdList = mPresenter.setTag(f3_3TagList);
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

        f3_3InfoGender.setText(user.getuGender() ? "암컷" : "수컷");
        f3_3InfoBirth.setText(user.getuAge() + "");
        f3_3InfoSpecify.setText(user.getuSpecify());
        f3_3InfoWeight.setText(user.getuWeight() == 0 ? "소형" : (user.getuWeight() == 1) ? "중형" : "대형");
        f3_3InfoAddress.setText(user.getuAddress());
    }
}
