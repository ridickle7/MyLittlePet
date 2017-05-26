package yapp.co.kr.mlp.Fragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import yapp.co.kr.mlp.R;

/**
 * Created by home on 2015-10-28.
 */
public class LocationFragment extends DialogFragment {
    LinearLayout cancleButton,OkButton;
    EditText input_location;
    Button extern_location_button;
    String location;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_location,container,false);
        cancleButton = (LinearLayout)v.findViewById(R.id.dialog_layout_button_cancel);
        OkButton = (LinearLayout)v.findViewById(R.id.dialog_layout_button_ok);
        input_location = (EditText)v.findViewById(R.id.edit_name);


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
                double fileSize = 0;
                location = input_location.getText().toString();
                extern_location_button = (Button) getActivity().findViewById(R.id.user_location);
                extern_location_button.setText(location);
                dismiss();
            }
        });
        return v;
    }
    public static LocationFragment newInstance(){
        LocationFragment fragment = new LocationFragment();
        return fragment;
    }

}
