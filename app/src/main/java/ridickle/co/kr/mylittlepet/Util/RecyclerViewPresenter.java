package ridickle.co.kr.mylittlepet.Util;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by ridickle on 2017. 10. 3..
 */

public class RecyclerViewPresenter {

    public static <T> ArrayList<T> init(){
        return new ArrayList<>();
    }

    // 1_1. 해당 아이템 추가 (아이템)
    public static <T> ArrayList<T> add(ArrayList<T> list, T item){
        list.add(item);
        return list;
    }

    // 1_2. 해당 아이템 추가 (인덱스)
    public static <T> ArrayList<T> add(ArrayList<T> list, T item, int index){
        list.add(index, item);
        return list;
    }

    // 2. 해당 아이템 갱신
    public static <T> ArrayList<T> update(ArrayList<T> list, T item, int index){
        list.set(index, item);
        return list;
    }

    // 3_1. 해당 아이템 삭제 (아이템)
    public static <T> ArrayList<T> remove(ArrayList<T> list, T item){
        list.remove(item);
        return list;
    }

    // 3_2. 해당 아이템 삭제 (인덱스)
    public static <T> ArrayList<T> remove(ArrayList<T> list, int index){
        list.remove(index);
        return list;
    }

    public static void recyclerViewSetting(Context context, RecyclerView recyclerView, int gridNum, RecyclerView.Adapter adapter) {
        RecyclerView.LayoutManager manager = null;
        if(gridNum > 1) {
            manager = new GridLayoutManager(context, gridNum);
        }
        else{
            manager = new LinearLayoutManager(context);
        }

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }
}
