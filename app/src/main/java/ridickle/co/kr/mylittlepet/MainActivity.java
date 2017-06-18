package ridickle.co.kr.mylittlepet;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import static ridickle.co.kr.mylittlepet.R.id.tab;

public class MainActivity extends AppCompatActivity {
    Toolbar toolBar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    TabLayout tabLayout;
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = (TabLayout) findViewById(tab);
        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);

        drawerSetting();

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


    private void drawerSetting(){
        // 1. 네비게이션 버튼 활성화 (햄버그바)
        toolBar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.mipmap.ic_launcher);
        actionBar.setDisplayHomeAsUpEnabled(true);

        // 2. id 연결 및 리스트 구현
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                menuItem.setChecked(true);
                drawerLayout.closeDrawers();

                int id = menuItem.getItemId();
                switch(id){
                    case R.id.settingItem1: // 로그아웃
                        MyApplication.getOAuthLoginInstance().logout(getApplicationContext());
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        finish();
                        break;
                    case R.id.settingItem2:
                        break;

                    case R.id.settingItem3:
                        break;
                }
                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch(id){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;

            case R.id.action_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
