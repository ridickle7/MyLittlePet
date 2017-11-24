package ridickle.co.kr.mylittlepet.Noti;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import ridickle.co.kr.mylittlepet.R;

public class NotiActivity extends AppCompatActivity implements NotiPresenter.view{

    NotiPresenter nPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noti);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nPresenter = new NotiPresenterImpl();
        nPresenter.loadItem();
    }

    @Override
    public void updateView() {

    }
}
