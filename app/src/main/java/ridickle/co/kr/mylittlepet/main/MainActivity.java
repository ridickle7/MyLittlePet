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
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import ridickle.co.kr.mylittlepet.MyApplication;
import ridickle.co.kr.mylittlepet.MySharedPreference;
import ridickle.co.kr.mylittlepet.Noti.NotiActivity;
import ridickle.co.kr.mylittlepet.R;
import ridickle.co.kr.mylittlepet.login.LoginActivity;
import ridickle.co.kr.mylittlepet.main.fragment3.MainFragment3;
import ridickle.co.kr.mylittlepet.main.fragment3.fragment3_1.MainF3Fragment1;

import static ridickle.co.kr.mylittlepet.R.id.nvLogout;
import static ridickle.co.kr.mylittlepet.R.id.nvSetting;

public class MainActivity extends AppCompatActivity implements MainPresenter.view {
    MainPresenter mPresenter;
    Toolbar toolBar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    TabLayout mTabLayout;
//    LinearLayout f2BottomSheet;
//    public static BottomSheetBehavior f2BottomSheetBehavior;

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
//        bottomSheetSetting();

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
        actionBar.setHomeAsUpIndicator(R.drawable.navi_icon);
        actionBar.setDisplayHomeAsUpEnabled(true);

        // 2. id 연결 및 리스트 구현
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                menuItem.setChecked(true);
                drawerLayout.closeDrawers();

                int id = menuItem.getItemId();
                switch (id) {
                    case nvLogout:         // 로그아웃
                        LoginActivity.getOAuthLoginInstance().logout(getApplicationContext());
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        finish();
                        break;

                    case R.id.nvTag:            // 관심 태그 설정
                        break;

                    case R.id.nvNotification:   // 공지사항
                        break;

                    case R.id.nvQnA:            // 문의하기
                        break;

                    case nvSetting:        // 설정
                        break;
                }
                return true;
            }
        });
    }

//    private void bottomSheetSetting() {
//        f2BottomSheet = (LinearLayout) findViewById(R.id.f2BottomSheet);
//        f2BottomSheetBehavior = BottomSheetBehavior.from(f2BottomSheet);
//        f2BottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
//
//        f2BottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
//            private ArrayList<String> inputArr = new ArrayList<String>();
//
//            @Override
//            public void onStateChanged(@NonNull View bottomSheet, int newState) {       // state가 바뀌었을 때 호출됨
//                switch (newState) {
//                    case BottomSheetBehavior.STATE_COLLAPSED:    // 화면에 노출될 때
//                        break;
//
//                    case BottomSheetBehavior.STATE_HIDDEN:      // 숨겨졌을 때
//                        break;
//
//                    default:
//                        break;
//                }
//                Log.d("TAG", "newState " + newState);
//            }
//
//            @Override
//            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
//                // Slide ...
//                // 1이면 완전 펼쳐진 상태
//                // 0이면 peekHeight인 상태
//                // -1이면 숨김 상태
//                Log.i("TAG", "slideOffset " + slideOffset);
//            }
//        });
//    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        // 헤더 설정
        LinearLayout headerLayout = (LinearLayout) navigationView.getHeaderView(0);
        ImageView nvImageURL = (ImageView) headerLayout.findViewById(R.id.nvImageURL);
        MyApplication.setImage(getApplicationContext(), nvImageURL, MySharedPreference.getDefaultInstance().getStringData(MySharedPreference.USER_IMAGEURL));

        TextView nvNickName = (TextView) headerLayout.findViewById(R.id.nvNickName);
        nvNickName.setText(MySharedPreference.getDefaultInstance().getStringData(MySharedPreference.USER_NICKNAME));

        TextView nvProfileButton = (TextView) headerLayout.findViewById(R.id.nvProfileButton);
        nvProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        // 리스트 설정
        String[] menuText = {"로그아웃", "메시지", "관심 태그 설정", "", "공지사항", "문의하기", "설정"};
        for (int i = 0; i < navigationView.getMenu().size(); i++) {
            LinearLayout nvlayout = (LinearLayout) navigationView.getMenu().getItem(i).getActionView();
            ((TextView) nvlayout.findViewById(R.id.itemTitle)).setText(menuText[i]);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;

            case R.id.action_message:
                Intent intent = new Intent(getApplicationContext(), NotiActivity.class);
                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void tabClick(int tabIndex) {
//        Toast.makeText(getApplicationContext(), tabIndex + "", Toast.LENGTH_SHORT).show();
        if (tabIndex == 0)
            setTitle("마이리틀펫");
        else if (tabIndex == 1)
            setTitle("맞춤검색");
        else if (tabIndex == 2)
            setTitle(MySharedPreference.getDefaultInstance().getStringData(MySharedPreference.USER_NICKNAME));
        else if (tabIndex == 3)
            setTitle("마이리틀펫");
        else if (tabIndex == 4)
            setTitle("이 달의 인기왕");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        MainF3Fragment1.newInstance().onActivityResult(requestCode, resultCode, data);
    }
}