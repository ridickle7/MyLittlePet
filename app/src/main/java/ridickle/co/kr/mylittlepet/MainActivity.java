package ridickle.co.kr.mylittlepet;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.Toast;

import static ridickle.co.kr.mylittlepet.R.id.tab;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = (TabLayout) findViewById(tab);
        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);

        tabLayout.addTab(tabLayout.newTab().setIcon(R.mipmap.ic_launcher));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.mipmap.ic_launcher));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.mipmap.ic_launcher));
        tabLayout.addTab(tabLayout.newTab().setText("Send"));
        tabLayout.addTab(tabLayout.newTab().setText("Send & Post"));

        // 탭 클릭 리스너
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tabLayout.getSelectedTabPosition();

                Toast.makeText(MainActivity.this, "Tab " + tabLayout.getSelectedTabPosition(), Toast.LENGTH_LONG).show();
                setCurrentTabFragment(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        replaceFragment(MainFragment3.newInstance());   // 기본 설정
    }

    // 각각의 탭을 눌렀을 때 반응
    private void setCurrentTabFragment(int tabPosition)
    {
        switch (tabPosition)
        {
            case 0 :
                replaceFragment(MainFragment1.newInstance());
                break;
            case 1 :
                replaceFragment(MainFragment2.newInstance());
                break;
            case 2 :
                replaceFragment(MainFragment3.newInstance());
                break;
            case 3 :
                replaceFragment(MainFragment4.newInstance());
                break;
            case 4 :
                replaceFragment(MainFragment5.newInstance());
                break;
        }
    }

    // FrameLayout 내 fragment 교체하기
    public void replaceFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameLayout, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }
}
