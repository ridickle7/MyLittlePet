package ridickle.co.kr.mylittlepet.main.fragment2.MainF2Dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import ridickle.co.kr.mylittlepet.R;

/**
 * Created by ridickle on 2017. 10. 24..
 */

public class MainF2DialogFragment extends DialogFragment implements MainF2DialogPresenter.fragment {
    private static MainF2DialogFragment instnce = null;

    private static Context context;
    private static MainF2DialogPresenter mPresenter;

    private View convertView;

    private static TextView mF2Tv;
    private static int index;
    private ListView f2DialogListView;

    private static String[] searchData = new String[6];

    public MainF2DialogFragment() {
        mPresenter = MainF2DialogPresenterImpl.getInstance(this);
    }

    public static MainF2DialogFragment newInstance(int indexData, TextView textView) {
        if (instnce == null) {
            instnce = new MainF2DialogFragment();
        }
        setData(indexData, textView);

        return instnce;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        context = getActivity();
        mPresenter.loadItem(context, index);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(convertView)
                // Add action buttons
                .setPositiveButton("등록", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // sign in the user ...
                        String text = "- abcd" + index;
                        mF2Tv.setText(text);
                        searchData[index] = text;
                        getDialog().cancel();
                    }
                })
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        searchData[index] = "";
                        mF2Tv.setText("");
                        mF2Tv = null;
                        getDialog().cancel();
                    }
                });
        return builder.create();
    }

    @Override
    public void updateView() {
        convertView = ((Activity) context).getLayoutInflater().inflate(R.layout.fragment_mainf2_dialog, null);

        f2DialogListView = (ListView) convertView.findViewById(R.id.f2DialogListView);
        f2DialogListView.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_2));
    }

    private static void setData(int indexData, TextView textView) {
        mF2Tv= textView;
        index = indexData;
    }

    public static void setSeekBar(int weight){
        searchData[4] = weight + "";
    }
}
