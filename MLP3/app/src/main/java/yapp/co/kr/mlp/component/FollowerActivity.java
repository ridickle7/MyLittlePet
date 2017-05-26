package yapp.co.kr.mlp.component;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;

import yapp.co.kr.mlp.Item_adapter.FollowerAdapter;
import yapp.co.kr.mlp.Item_item.Follower;
import yapp.co.kr.mlp.R;

public class FollowerActivity extends ActionBarActivity {

    ListView listView;
    FollowerAdapter adatper_follower;
    ToggleButton toggleButton;
    String follow_flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follower);

        toggleButton = (ToggleButton)findViewById(R.id.togglebtn_plus);
        Intent in = getIntent();
        follow_flag = in.getStringExtra("follow");

        TextView follow_title = (TextView) findViewById(R.id.follow_title);
        if(follow_flag.equals("follower")){
            follow_title.setText("나를 팔로잉한 친구들");
        }else {
            follow_title.setText("내가 팔로잉한 친구들");
        }

        Resources res = getResources();
        ArrayList<Follower> list = new ArrayList<>();
        list.add(new Follower(res.getDrawable(R.mipmap.ic_launcher),
                "my_little_pet",false));
        list.add(new Follower(res.getDrawable(R.mipmap.ic_launcher),
                "rlawldnr2354",false));
        list.add(new Follower(res.getDrawable(R.mipmap.ic_launcher),
                "hy21116",false));
        list.add(new Follower(res.getDrawable(R.mipmap.ic_launcher),
                "a__raming",false));
        list.add(new Follower(res.getDrawable(R.mipmap.ic_launcher),
                "ridickle7",false));
        list.add(new Follower(res.getDrawable(R.mipmap.ic_launcher),
                "my_little_tv",false));
        list.add(new Follower(res.getDrawable(R.mipmap.ic_launcher),
                "my_little_pet",false));
        list.add(new Follower(res.getDrawable(R.mipmap.ic_launcher),
                "rlawldnr2354",false));
        list.add(new Follower(res.getDrawable(R.mipmap.ic_launcher),
                "hy21116",false));
        list.add(new Follower(res.getDrawable(R.mipmap.ic_launcher),
                "a__raming",false));
        list.add(new Follower(res.getDrawable(R.mipmap.ic_launcher),
                "ridickle7",false));
        list.add(new Follower(res.getDrawable(R.mipmap.ic_launcher),
                "my_little_tv",false));

        listView = (ListView)findViewById(R.id.listview_follow);
        listView.setFocusable(false);     //리스트뷰 온아이템 클릭터치 이벤트 없앰
        adatper_follower = new FollowerAdapter(this, R.layout.list_follower, list);
        listView.setAdapter(adatper_follower);
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
        }

        return super.onOptionsItemSelected(item);
    }
}
