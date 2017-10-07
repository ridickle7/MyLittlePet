package ridickle.co.kr.mylittlepet.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import ridickle.co.kr.mylittlepet.R;
import ridickle.co.kr.mylittlepet.main.fragment1.MainFragment1;
import ridickle.co.kr.mylittlepet.main.fragment2.MainFragment2;
import ridickle.co.kr.mylittlepet.main.fragment3.MainFragment3;
import ridickle.co.kr.mylittlepet.main.fragment4.MainFragment4;
import ridickle.co.kr.mylittlepet.main.fragment5.MainFragment5;

/**
 * Created by ridickle on 2017. 9. 29..
 */

public class MainModel {
    AtomicInteger sNextGeneratedId;

    // FrameLayout 내 fragment 교체하기
    public void replaceFragment(MainPresenter.View activity, Fragment fragment, int frameLayoutId) {
        FragmentManager fm = ((FragmentActivity) activity).getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(frameLayoutId, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commitAllowingStateLoss();
    }

    // 각각의 탭을 눌렀을 때 반응
    public void setCurrentTabFragment(MainPresenter.View activity, int tabPosition, int flag) {
        if (flag == 0)
            switch (tabPosition) {
                case 0:
                    replaceFragment(activity, MainFragment1.newInstance(), R.id.frameLayout);
                    break;
                case 1:
                    replaceFragment(activity, MainFragment2.newInstance(), R.id.frameLayout);
                    break;
                case 2:
                    replaceFragment(activity, MainFragment3.newInstance(), R.id.frameLayout);
                    break;
                case 3:
                    replaceFragment(activity, MainFragment4.newInstance(), R.id.frameLayout);
                    break;
                case 4:
                    replaceFragment(activity, MainFragment5.newInstance(), R.id.frameLayout);
                    break;
            }
    }

    public ArrayList<Integer> tagInflating(final LinearLayout layout, ArrayList<String> dummyList) {
        sNextGeneratedId = new AtomicInteger(1);

        ArrayList<Integer> textList = new ArrayList<>();
        for (int i = 0; i < dummyList.size(); ) {
            LinearLayout itemRow = new LinearLayout(layout.getContext());
            itemRow.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            itemRow.setOrientation(LinearLayout.HORIZONTAL);

            for (int j = 0; j < 4; j++, i++) {
                TextView tagText = new TextView(layout.getContext());
                tagText.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                tagText.setPadding(30, 30, 30, 30);
                final int newId = generateViewId();
                tagText.setId(newId);
                textList.add(newId);
                tagText.setText(dummyList.get(i));

                itemRow.addView(tagText);
            }
            layout.addView((View) itemRow);
        }

        return textList;
    }

    /**
     * @return a generated ID value
     */
    public int generateViewId() {
        for (; ; ) {
            final int result = sNextGeneratedId.get();
            // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
            int newValue = result + 1;
            if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
            if (sNextGeneratedId.compareAndSet(result, newValue)) {
                return result;
            }
        }
    }

}
