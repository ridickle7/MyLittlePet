package ridickle.co.kr.mylittlepet.main.fragment2;

import java.util.ArrayList;
import java.util.List;

import static ridickle.co.kr.mylittlepet.main.fragment2.Fragment2ListAdapter.CHILD;
import static ridickle.co.kr.mylittlepet.main.fragment2.Fragment2ListAdapter.HEADER;

/**
 * Created by ridickle on 2017. 10. 12..
 */

public class MainF2Model {
    static MainF2Model instance;
    static String[] nameArr = {"성별", "나이", "품종", "몸무게", "지역"};

    public static synchronized MainF2Model getInstance() {
        if (instance == null)
            instance = new MainF2Model();

        return instance;
    }

    public ArrayList initData() {
        ArrayList<Item> initList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Item itemParent = new Item(HEADER, nameArr[i]);

            itemParent.invisibleChildren = new ArrayList<>();
            itemParent.invisibleChildren.add(new Item(CHILD, (i + 1) + ""));

            initList.add(itemParent);
        }

        return initList;
    }

    public int collapseList(int position, Item item, List<Item> data) {
        int count = 0;
        while (data.size() > position + 1 && data.get(position + 1).type == CHILD) {
            data.get(position + 1).setFlag(0);
            item.invisibleChildren.add(data.remove(position + 1));
            count++;
        }

        return count;
    }

    public int expandList(int position, Item item, List<Item> data) {
        int index = position + 1;
        for (Item i : item.invisibleChildren) {
            data.add(index, i);
            index++;
        }

        return index;
    }
}
