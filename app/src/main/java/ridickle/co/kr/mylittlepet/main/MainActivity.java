package ridickle.co.kr.mylittlepet.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import ridickle.co.kr.mylittlepet.R;
import ridickle.co.kr.mylittlepet.login.LoginActivity;
import ridickle.co.kr.mylittlepet.main.fragment1.fragment1_1.MainF1Fragment1;
import ridickle.co.kr.mylittlepet.main.fragment3.MainFragment3;

public class MainActivity extends AppCompatActivity implements MainPresenter.view {
    MainPresenter mPresenter;
    Toolbar toolBar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = MainPresenterImpl.getInstance(this);
        mPresenter.loadItem();
    }

    @Override
    public void updateView() {
        mTabLayout = (TabLayout) findViewById(R.id.tab);
        mTabLayout = mPresenter.tabSetting(mTabLayout);

        drawerSetting();

        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        mTabLayout.getTabAt(2).select();
                    }
                }, 100);
        MainModel.replaceFragment(this, MainFragment3.getInstance(), R.id.frameLayout);
    }

    private void drawerSetting() {
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
                switch (id) {
                    case R.id.settingItem1: // 로그아웃
                        LoginActivity.getOAuthLoginInstance().logout(getApplicationContext());
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
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;

            case R.id.action_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void tabClick(String str) {
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        MainF1Fragment1.newInstance().onActivityResult(requestCode, resultCode, data);
    }
}