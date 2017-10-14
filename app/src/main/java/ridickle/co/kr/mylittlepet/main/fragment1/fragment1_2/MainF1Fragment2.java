package ridickle.co.kr.mylittlepet.main.fragment1.fragment1_2;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;

import ridickle.co.kr.mylittlepet.Util.PhotoPresenter;
import ridickle.co.kr.mylittlepet.MyApplication;
import ridickle.co.kr.mylittlepet.R;

import static android.app.Activity.RESULT_OK;

/**
 * Created by ridickle on 2017. 9. 30..
 */

public class MainF1Fragment2 extends Fragment implements MainF1_2Presenter.fragment {
    public static final String TAG = "MainF1Fragment2";
    MainF1_2Presenter mPresenter;
    private static MainF1Fragment2 instance;

    View convertView;
    LinearLayout f1_2SelectButtonList;
    ImageView f1_2Image, f1_2Rotate;
    Button f1_2Search, f1_2Cancel;

    public MainF1Fragment2() {
        // Required empty public constructor
        mPresenter = MainF1_2PresenterImpl.getInstance(this);
    }

    public static MainF1Fragment2 newInstance() {
        if (instance == null)
            instance = new MainF1Fragment2();
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        convertView = inflater.inflate(R.layout.fragment_tab1_fragment2, container, false);

        mPresenter.loadItem();

        return convertView;
    }

    @Override
    public void updateItem() {
        f1_2SelectButtonList = (LinearLayout) convertView.findViewById(R.id.f1_2SelectButtonList);
        f1_2Image = (ImageView) convertView.findViewById(R.id.f1_2Image);
        f1_2Rotate = (ImageView) convertView.findViewById(R.id.f1_2Rotate);
        f1_2Search = (Button) convertView.findViewById(R.id.f1_2Search);
        f1_2Cancel = (Button) convertView.findViewById(R.id.f1_2Cancel);

        // 메인 이미지 버튼 누를 시
        f1_2Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
                intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(intent, 0);
            }
        });

        f1_2Rotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhotoPresenter.getInstance().imgRotate();
                f1_2Image.setBackground(new BitmapDrawable(getResources(), PhotoPresenter.getInstance().getBitmap()));
            }
        });
    }

    @Override
    public void loadToastMessage(String toastMessage) {
        Toast.makeText(getActivity(), toastMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        Log.d(TAG, "requestCode : " + requestCode + "\nresultCode : " + resultCode);

        if (resultCode == RESULT_OK) {
            f1_2SelectButtonList.setVisibility(View.VISIBLE);
            f1_2Rotate.setVisibility(View.VISIBLE);

            final File imageFile = mPresenter.loadImageFile(intent);
            f1_2Image.setBackground(new BitmapDrawable(getResources(), PhotoPresenter.getInstance().getBitmap()));

            // 검색 버튼 누를 시
            f1_2Search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mPresenter.searchByImage(imageFile);
                }
            });

            // 취소 버튼 누를 시
            f1_2Cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MyApplication.setImage(getActivity(), f1_2Image, "");
                    PhotoPresenter.bitmapClear();
                    f1_2SelectButtonList.setVisibility(View.GONE);
                    f1_2Rotate.setVisibility(View.GONE);
                }
            });
        } else {

        }
    }


}
