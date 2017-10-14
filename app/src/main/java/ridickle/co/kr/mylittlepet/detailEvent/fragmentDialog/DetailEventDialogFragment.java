package ridickle.co.kr.mylittlepet.detailEvent.fragmentDialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import ridickle.co.kr.mylittlepet.MySharedPreference;
import ridickle.co.kr.mylittlepet.Network.DataBody.Network_Content;
import ridickle.co.kr.mylittlepet.R;
import ridickle.co.kr.mylittlepet.Util.PhotoPresenter;

public class DetailEventDialogFragment extends DialogFragment implements DetailEventDialogPresenter.fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
    private static DetailEventDialogFragment instnce = null;
    private static Intent intent;
    private static Context context;
    private static DetailEventDialogPresenter dPresenter;

    private View convertView;

    private ImageView dDialogIv;
    private EditText dDialogEt;

    public DetailEventDialogFragment() {
        dPresenter = DetailEventDialogPresenterImpl.getInstance(this);
    }

    public static DetailEventDialogFragment newInstance(Intent data) {
        if(instnce == null){
            instnce = new DetailEventDialogFragment();
        }
        intent = data;
        return instnce;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        context = getActivity();
        dPresenter.loadItem(context, intent);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(convertView)
                // Add action buttons
                .setPositiveButton("등록", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // sign in the user ...
                        String text = dDialogEt.getText().toString();
                        dPresenter.clickOk(MySharedPreference.getDefaultInstance().getStringData(MySharedPreference.EVENT_ID), text);
                    }
                })
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        DetailEventDialogFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }

    @Override
    public void updateView(Bitmap bitmap) {
        convertView = ((Activity) context).getLayoutInflater().inflate(R.layout.fragment_detailevent_dialog, null);

        dDialogIv = (ImageView) convertView.findViewById(R.id.dDialogIv);
        dDialogEt = (EditText) convertView.findViewById(R.id.dDialogEt);

        dDialogIv.setImageBitmap(bitmap);
    }

    @Override   // 이벤트 id 초기화 및 화면에서 나옴
    public void executeOk(Network_Content contentItem) {
        MySharedPreference.getDefaultInstance().setData(MySharedPreference.EVENT_ID, "");
        PhotoPresenter.bitmapClear();
    }
}
