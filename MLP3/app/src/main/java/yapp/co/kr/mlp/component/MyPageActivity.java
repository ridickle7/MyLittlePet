package yapp.co.kr.mlp.component;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import yapp.co.kr.mlp.R;

public class MyPageActivity extends ActionBarActivity {

    ImageButton following, follower, love;
    TextView followinger, followerer;
    TextView specific, dog_specific, gender, birth, dog_name, user_name, information;

    ImageView photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        init();
    }

    public void init() {
        photo = (ImageView) findViewById(R.id.image);

        following = (ImageButton) findViewById(R.id.following);
        follower = (ImageButton)findViewById(R.id.follower);
        followinger = (TextView) findViewById(R.id.followinger);
        followerer = (TextView) findViewById(R.id.followerer);
        love = (ImageButton) findViewById(R.id.love);

        dog_name = (TextView) findViewById(R.id.dog_name);
        birth = (TextView) findViewById(R.id.birth);
        information = (TextView) findViewById(R.id.information);
        gender = (TextView) findViewById(R.id.gender);
        dog_specific =  (TextView) findViewById(R.id.dog_specific);

        follower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), FollowerActivity.class);
                in.putExtra("follow", "follower");
                startActivity(in);
            }
        });

        following.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), FollowerActivity.class);
                in.putExtra("follow", "following");
                startActivity(in);
            }
        });

        love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_page, menu);
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
