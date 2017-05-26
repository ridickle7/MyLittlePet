package yapp.co.kr.mlp.component;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import yapp.co.kr.mlp.Item_adapter.CommentAdapter;
import yapp.co.kr.mlp.Item_item.Comment;
import yapp.co.kr.mlp.R;

public class AnswerActivity extends ActionBarActivity {

    TextView question, date, nickname, content, comment_num;
    Button input_comment;
    ListView comment_list;
    ArrayList<Comment> com_list;
    CommentAdapter comment_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        init();
        listener();

        for (int i = 0; i < 3; i++) {
            Comment temp = new Comment();
            temp.setDate("2015-10-0" + i);
            temp.setContent("망아지도 절 때렸어요 " + i);
            temp.setNickname("ridickle" + i);
            temp.setProfile(getResources().getDrawable(R.mipmap.ic_launcher));
            com_list.add(temp);
        }

        comment_adapter = new CommentAdapter(getApplicationContext(), R.layout.list_comment, com_list);
        comment_list.setAdapter(comment_adapter);


    }

    public void init() {
        question = (TextView) findViewById(R.id.question);
        date = (TextView) findViewById(R.id.date);
        nickname = (TextView) findViewById(R.id.nickname);
        comment_num = (TextView) findViewById(R.id.comment_num);
        content = (TextView) findViewById(R.id.content);
        input_comment = (Button) findViewById(R.id.input_comment);
        comment_list = (ListView) findViewById(R.id.comment_list);

        com_list = new ArrayList<>();

    }

    public void listener() {
        input_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_answer, menu);
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
