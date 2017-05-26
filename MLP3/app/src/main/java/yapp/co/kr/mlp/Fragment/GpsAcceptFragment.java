package yapp.co.kr.mlp.Fragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.List;

import yapp.co.kr.mlp.R;

public class GpsAcceptFragment extends DialogFragment {
    LinearLayout cancleButton,OkButton;
    EditText editName;
    Button btn;
    String rName;

    ListView categoryList;
    List<String> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_gps_accept_fragment,container,false);
        cancleButton = (LinearLayout)v.findViewById(R.id.dialog_layout_button_cancel);
        OkButton = (LinearLayout)v.findViewById(R.id.dialog_layout_button_ok);
        editName = (EditText)v.findViewById(R.id.edit_name);


        //취소버튼
        cancleButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        //확인 버튼
        OkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismiss();
            }
        });
        return v;
    }
    public static GpsAcceptFragment newInstance(){
        GpsAcceptFragment fragment = new GpsAcceptFragment();
        return fragment;
    }


}
