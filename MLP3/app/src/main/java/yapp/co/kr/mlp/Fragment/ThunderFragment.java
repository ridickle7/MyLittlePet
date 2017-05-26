package yapp.co.kr.mlp.Fragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import yapp.co.kr.mlp.R;

/**
 * Created by home on 2015-11-04.
 */
public class ThunderFragment extends DialogFragment {
    LinearLayout cancleButton, OkButton;
    String messageReceived;

    public static ThunderFragment newInstance() {
        ThunderFragment fragment = new ThunderFragment();
        return fragment;
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if(bundle != null) {
            messageReceived = bundle.getString("nickname");
            Log.d("thunder_nickname", messageReceived);
        }
        View v = inflater.inflate(R.layout.fragment_thunder, container, false);
        cancleButton = (LinearLayout) v.findViewById(R.id.dialog_layout_button_cancel);
        OkButton = (LinearLayout) v.findViewById(R.id.dialog_layout_button_ok);

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
                Toast.makeText(getActivity(), "" + getArguments(),Toast.LENGTH_LONG).show();
            }
        });

        return v;
    }
}