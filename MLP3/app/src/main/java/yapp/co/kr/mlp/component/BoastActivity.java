package yapp.co.kr.mlp.component;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

import yapp.co.kr.mlp.Fragment.BoastAddFragment;
import yapp.co.kr.mlp.Item_adapter.BoastSubAdapter;
import yapp.co.kr.mlp.Item_item.ListItem02;
import yapp.co.kr.mlp.R;

/**
 * Created by hayoung on 2015-08-29.
 */
public class BoastActivity extends ActionBarActivity{
    ListView listView02;
    BoastSubAdapter adapter02;

    ImageButton btn_write;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Resources res02 = getResources();
        ArrayList<ListItem02> list02 = new ArrayList<ListItem02>();
        list02.add(new ListItem02(res02.getDrawable(R.mipmap.photo),"hy21116"));
        list02.add(new ListItem02(res02.getDrawable(R.mipmap.noun_364),"teddy_S"));
        list02.add(new ListItem02(res02.getDrawable(R.mipmap.noun_751),"shynee"));

        listView02 = (ListView)findViewById(R.id.listView_sub);
        View header = getLayoutInflater().inflate(R.layout.list_sub_header, null, false);
        listView02.addHeaderView(header);

        adapter02 = new BoastSubAdapter(this,R.layout.list_sub,list02);
        listView02.setAdapter(adapter02);

        btn_write = (ImageButton) findViewById(R.id.btn_write);
        btn_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BoastAddFragment dialogFragment = BoastAddFragment.newInstance();
                dialogFragment.setStyle(R.style.Theme_Dialog_Transparent, R.style.Theme_Dialog_Transparent);
                dialogFragment.show(getFragmentManager(), "TAG");
            }
        });

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