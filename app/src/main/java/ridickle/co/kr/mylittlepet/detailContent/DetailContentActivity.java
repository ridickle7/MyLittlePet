package ridickle.co.kr.mylittlepet.detailContent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import ridickle.co.kr.mylittlepet.MyApplication;
import ridickle.co.kr.mylittlepet.Network.DataBody.Network_Content;
import ridickle.co.kr.mylittlepet.R;

public class DetailContentActivity extends AppCompatActivity implements DetailContentPresenter.view{
    private DetailContentPresenter dPresenter;

    ImageView dcImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_content);

        dPresenter = DetailContentPresenterImpl.getInstance(this);
        dPresenter.loadItem();
    }

    @Override
    public void updateView(Network_Content contentItem) {
        dcImage = (ImageView) findViewById(R.id.dcImage);
        MyApplication.setImage(this, dcImage, contentItem.getcImageURL());
    }
}
