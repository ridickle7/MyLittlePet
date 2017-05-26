package yapp.co.kr.mlp.component;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;

import yapp.co.kr.mlp.Fragment.SectionsFragment1;
import yapp.co.kr.mlp.Fragment.SectionsFragment2;
import yapp.co.kr.mlp.Fragment.SectionsFragment3;
import yapp.co.kr.mlp.Fragment.SectionsFragment4;
import yapp.co.kr.mlp.Helper.CustomerHelper;
import yapp.co.kr.mlp.Item_adapter.NaviDrawerAdapter;
import yapp.co.kr.mlp.Item_item.Nav_list;
import yapp.co.kr.mlp.R;
import yapp.co.kr.mlp.Service.TabsAdapter;

//주석
public class MainActivity extends ActionBarActivity {
    public static final String TAB_INDEX_ON_SAVEDINST = "tabindex";

    TabHost tabHost;
    ViewPager pager;
    TabsAdapter mAdapter;
    CustomerHelper mCustomHelper;

    String name, flag;
    byte[] profile;

    DrawerLayout dlDrawer;
    ActionBarDrawerToggle dtToggle;
    ActionBar actionBar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup(); // setup the tabhost.
        pager = (ViewPager) findViewById(R.id.pager);

        mAdapter = new TabsAdapter(this, getSupportFragmentManager(), tabHost, pager);
        addFragmentToPager(savedInstanceState);

        mCustomHelper = CustomerHelper.getInstance();
        name = mCustomHelper.getUserId();
        profile = Base64.decode(mCustomHelper.getProfile(), Base64.DEFAULT);

        Bitmap bmp = BitmapFactory.decodeByteArray(profile, 0, profile.length);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);      //이거만 추가시키면 앞에 아이콘 생김! (여기서는 네비게이션 드로우어)

        navigation_drawer(bmp);
    }


    // 네비게이션 드로우어 관련 처리     -> 근데 이 오픈소스 업데이트 된 듯... 추후 오픈소스 연구 필요
    private void navigation_drawer(Bitmap bmp) {
        dlDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        dtToggle = new ActionBarDrawerToggle(this, dlDrawer, R.string.app_name, R.string.app_name) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        dlDrawer.setDrawerListener(dtToggle);

        ImageButton navi_cal = (ImageButton) findViewById(R.id.photo);
        TextView nickname = (TextView) findViewById(R.id.dialog_nickname);
        navi_cal.setImageBitmap(bmp);
        nickname.setText(name);

        navi_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlDrawer.closeDrawers();
            }
        });

        ListView nav_list = (ListView) findViewById(R.id.navigation_list);
        ArrayList<Nav_list> subList = new ArrayList<Nav_list>();
        Resources res = getResources();
        Nav_list temp = new Nav_list(res.getDrawable(R.mipmap.nav_notice), "공지사항");
        subList.add(temp);
        temp = new Nav_list(res.getDrawable(R.mipmap.nav_friend), "친구초대");
        subList.add(temp);
        temp = new Nav_list(res.getDrawable(R.mipmap.nav_question), "문의하기");
        subList.add(temp);
        temp = new Nav_list(res.getDrawable(R.mipmap.nav_setting), "설정    ");
        subList.add(temp);
        NaviDrawerAdapter adapters = new NaviDrawerAdapter(getApplicationContext(), R.layout.list_navidrawer, subList);
        nav_list.setAdapter(adapters);
    }

    private void addFragmentToPager(Bundle savedInstanceState) {
        Resources res = getResources();

        ArrayList<View> tab_Indicator = new ArrayList<>();
        View temp;

        temp = LayoutInflater.from(this).inflate(R.layout.tab_icon, null);
        ((TextView) temp.findViewById(R.id.tab_title)).setText("친구추천");
        ((ImageView) temp.findViewById(R.id.tab_icon)).setImageResource(R.drawable.main_selector);
        tab_Indicator.add(temp);

        temp = LayoutInflater.from(this).inflate(R.layout.tab_icon, null);
        ((TextView) temp.findViewById(R.id.tab_title)).setText("자랑하기");
        ((ImageView) temp.findViewById(R.id.tab_icon)).setImageResource(R.drawable.star_selector);
        tab_Indicator.add(temp);

        temp = LayoutInflater.from(this).inflate(R.layout.tab_icon, null);
        ((TextView) temp.findViewById(R.id.tab_title)).setText("산책번개");
        ((ImageView) temp.findViewById(R.id.tab_icon)).setImageResource(R.drawable.thunder_selector);
        tab_Indicator.add(temp);

        temp = LayoutInflater.from(this).inflate(R.layout.tab_icon, null);
        ((TextView) temp.findViewById(R.id.tab_title)).setText("Q & A");
        ((ImageView) temp.findViewById(R.id.tab_icon)).setImageResource(R.drawable.qna_selector);
        tab_Indicator.add(temp);

        mAdapter.addTab(tabHost.newTabSpec("main").setIndicator(tab_Indicator.get(0)),
                SectionsFragment1.class, null);
        mAdapter.addTab(tabHost.newTabSpec("star").setIndicator(tab_Indicator.get(1)),
                SectionsFragment2.class, null);
        mAdapter.addTab(tabHost.newTabSpec("thunder").setIndicator(tab_Indicator.get(2)),
                SectionsFragment3.class, null);
        mAdapter.addTab(tabHost.newTabSpec("qna").setIndicator(tab_Indicator.get(3)),
                SectionsFragment4.class, null);

        for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
            tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.WHITE);
        }

        if (savedInstanceState != null) {
            tabHost.setCurrentTab(savedInstanceState.getInt(TAB_INDEX_ON_SAVEDINST));
            mAdapter.onRestoreInstanceState(savedInstanceState);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        dtToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        dtToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (dtToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}