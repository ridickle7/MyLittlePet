package yapp.co.kr.mlp.Fragment;

import android.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import yapp.co.kr.mlp.Item_adapter.QnaAdapter;
import yapp.co.kr.mlp.Item_item.Qna;
import yapp.co.kr.mlp.R;
import yapp.co.kr.mlp.component.AnswerActivity;

/**
 * Created by home on 2015-11-02.
 */
public class SectionsFragment4 extends Fragment {

    public SectionsFragment4() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_page4,
                container, false);
        Toolbar toolbar4;
        toolbar4 = (Toolbar) rootView.findViewById(R.id.toolbar4);
        View spinnerContainer = LayoutInflater.from(this.getActivity()).inflate(R.layout.toolbar_spinner,
                toolbar4, false);
        ActionBar.LayoutParams lp = new ActionBar.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        toolbar4.addView(spinnerContainer, lp);


        Spinner spinner = (Spinner) spinnerContainer.findViewById(R.id.toolbar_spinner);

        ArrayList<String> subList = new ArrayList<String>();
        subList.add("질병관련");
        subList.add("정보관련");
        ArrayAdapter<String> adapters = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, subList);
        spinner.setAdapter(adapters);

        final ArrayList<Qna> mainList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Qna temp = new Qna();

            temp.setId("" + i);
            temp.setQuestion("우리 뚱이가 너무 많이 먹어요ㅜㅜ.");
            temp.setWatch(30 + i + "");
            temp.setComment(20 + i + "");
            temp.setName("hy21116");
            temp.setDate("2015-08-23");

            mainList.add(temp);
        }

        final QnaAdapter adapter;
        adapter = new QnaAdapter(getActivity(), R.layout.list_qna, mainList);


        ListView lv = (ListView) rootView.findViewById(R.id.listView);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                String str = mainList.get(position).getName();
                Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getActivity(), AnswerActivity.class);
                //intent.putExtra("showpage",);
                startActivity(intent);
            }
        });

        //like();
        return rootView;
    }
}