package ridickle.co.kr.mylittlepet.detailEvent;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;
import java.util.ArrayList;

import ridickle.co.kr.mylittlepet.MyApplication;
import ridickle.co.kr.mylittlepet.R;

public class DetailEventDialogFragment extends DialogFragment implements DetailEventPresenter.fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
    private static Intent intent;
    private static DetailEventPresenter dPresenter;

    private View convertView;

    private ImageView dDialogIv;
    private EditText dDialogEt;

    public DetailEventDialogFragment() {
        // Required empty public constructor
    }

    //    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment DetailEventDialogFragment.
//     */
    // TODO: Rename and change types and number of parameters
    public static DetailEventDialogFragment newInstance(Intent data, DetailEventPresenter presenter) {
        DetailEventDialogFragment fragment = new DetailEventDialogFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        intent = data;
        dPresenter = presenter;

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater


        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        dPresenter.uiSetting(this, R.layout.fragment_detailevent_dialog);

        builder.setView(convertView)
                // Add action buttons
                .setPositiveButton("등록", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // sign in the user ...
                        String text = dDialogEt.getText().toString();
                        ArrayList<String> tagList = MyApplication.getTagList(text);
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
    public void settingUI(android.view.View convertView) {
        this.convertView = convertView;
        dDialogIv = (ImageView) convertView.findViewById(R.id.dDialogIv);
        dDialogEt = (EditText) convertView.findViewById(R.id.dDialogEt);

        Bitmap bitmap = null;
        if (intent.getData() != null) {
//            dDialogIv.setImageURI(intent.getData());
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), intent.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            bitmap = (Bitmap) intent.getExtras().get("data");
        }
        dDialogIv.setImageBitmap(bitmap);
    }
}
