package ridickle.co.kr.mylittlepet.main.fragment1.fragment1_3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ridickle.co.kr.mylittlepet.R;
import ridickle.co.kr.mylittlepet.main.MainPresenter;
import ridickle.co.kr.mylittlepet.main.MainPresenterImpl;

/**
 * Created by ridickle on 2017. 9. 30..
 */

public class MainF1Fragment3 extends Fragment implements MainPresenter.Fragment1_3 {
    static MainF1Fragment3 instance;
    MainPresenter mPresenter;
    TextView f1_3InfoGender, f1_3InfoBirth, f1_3InfoSpecify, f1_3InfoWeight, f1_3InfoAddress;
    private LinearLayout f1_3TagList;


    public MainF1Fragment3() {
        // Required empty public constructor
        mPresenter = MainPresenterImpl.newInstance();
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
        View convertView = inflater.inflate(R.layout.fragment_tab1_fragment3, container, false);

        mPresenter.uiSetting(convertView, this);
        mPresenter.fragmentSetting(null, null, this, MainPresenterImpl.MAINACTIVITY_FRAGMENT1_3);
        return convertView;
    }

    @Override
    public void viewSetting() {

    }

    @Override
    public void settingUI(View view) {
        f1_3TagList = (LinearLayout) view.findViewById(R.id.f1_3TagList);

        f1_3InfoGender = (TextView) view.findViewById(R.id.f1_3InfoGender).findViewById(R.id.f1_3InfoName);
        ((TextView)view.findViewById(R.id.f1_3InfoGender).findViewById(R.id.f1_3InfoTitle)).setText("성별");
        f1_3InfoBirth = (TextView) view.findViewById(R.id.f1_3InfoBirth).findViewById(R.id.f1_3InfoName);
        ((TextView) view.findViewById(R.id.f1_3InfoBirth).findViewById(R.id.f1_3InfoTitle)).setText("생일");
        f1_3InfoSpecify = (TextView) view.findViewById(R.id.f1_3InfoSpecify).findViewById(R.id.f1_3InfoName);
        ((TextView) view.findViewById(R.id.f1_3InfoSpecify).findViewById(R.id.f1_3InfoTitle)).setText("품종");
        f1_3InfoWeight = (TextView) view.findViewById(R.id.f1_3InfoWeight).findViewById(R.id.f1_3InfoName);
        ((TextView) view.findViewById(R.id.f1_3InfoWeight).findViewById(R.id.f1_3InfoTitle)).setText("몸무게");
        f1_3InfoAddress = (TextView) view.findViewById(R.id.f1_3InfoAddress).findViewById(R.id.f1_3InfoName);
        ((TextView) view.findViewById(R.id.f1_3InfoAddress).findViewById(R.id.f1_3InfoTitle)).setText("지역");

        mPresenter.f1_3Tag(f1_3TagList, this);
        mPresenter.f1_3Info(this);
    }

    @Override
    public void tagSetting(View view, ArrayList<Integer> idList) {
        for (int i = 0; i < idList.size(); i++) {
            final TextView tagItem = (TextView) view.findViewById(idList.get(i));
            tagItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mPresenter.clickTag((MainPresenter.Fragment1_3) instance, tagItem.getId() + "");
                }
            });
        }
    }

    @Override
    public void tagClicked(String tagName) {
        Toast.makeText(getActivity(), tagName, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void infoSetting(ArrayList<String> dummyList) {
        f1_3InfoGender.setText(dummyList.get(0));
        f1_3InfoBirth.setText(dummyList.get(1));
        f1_3InfoSpecify.setText(dummyList.get(2));
        f1_3InfoWeight.setText(dummyList.get(3));
        f1_3InfoAddress.setText(dummyList.get(4));
    }
}
