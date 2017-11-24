package ridickle.co.kr.mylittlepet.main.fragment3.fragment3_2;

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

public class MainF3Fragment2 extends Fragment implements MainF3_2Presenter.fragment {
    public static final String TAG = "MainF1Fragment2";
    MainF3_2Presenter mPresenter;
    private static MainF3Fragment2 instance;

    View convertView;
    LinearLayout f3_2SelectButtonList;
    ImageView f3_2Image, f3_2Rotate;
    Button f3_2Search, f3_2Cancel;

    public MainF3Fragment2() {
        // Required empty public constructor
        mPresenter = MainF3_2PresenterImpl.getInstance(this);
    }

    public static MainF3Fragment2 newInstance() {
        if (instance == null)
            instance = new MainF3Fragment2();
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        convertView = inflater.inflate(R.layout.fragment_tab3_fragment2, container, false);

        mPresenter.loadItem();

        return convertView;
    }

    @Override
    public void updateItem() {
        f3_2SelectButtonList = (LinearLayout) convertView.findViewById(R.id.f3_2SelectButtonList);
        f3_2Image = (ImageView) convertView.findViewById(R.id.f3_2Image);
        f3_2Rotate = (ImageView) convertView.findViewById(R.id.f3_2Rotate);
        f3_2Search = (Button) convertView.findViewById(R.id.f3_2Search);
        f3_2Cancel = (Button) convertView.findViewById(R.id.f3_2Cancel);

        // 메인 이미지 버튼 누를 시
        f3_2Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
                intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(intent, 0);
            }
        });

        f3_2Rotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhotoPresenter.getInstance().imgRotate();
                f3_2Image.setBackground(new BitmapDrawable(getResources(), PhotoPresenter.getInstance().getBitmap()));
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
            f3_2SelectButtonList.setVisibility(View.VISIBLE);
            f3_2Rotate.setVisibility(View.VISIBLE);

            final File imageFile = mPresenter.loadImageFile(intent);
            f3_2Image.setBackground(new BitmapDrawable(getResources(), PhotoPresenter.getInstance().getBitmap()));

            // 검색 버튼 누를 시
            f3_2Search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mPresenter.searchByImage(imageFile);
                }
            });

            // 취소 버튼 누를 시
            f3_2Cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MyApplication.setImage(getActivity(), f3_2Image, "");
                    PhotoPresenter.bitmapClear();
                    f3_2SelectButtonList.setVisibility(View.GONE);
                    f3_2Rotate.setVisibility(View.GONE);
                }
            });
        } else {

        }
    }


}
