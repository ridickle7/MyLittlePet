package ridickle.co.kr.mylittlepet.main.fragment2;

import java.util.List;

/**
 * Created by ridickle on 2017. 10. 25..
 */

public class Item {
    public int type;        // PARENT (부모 value) / CHILD (자식 value)
    public String text;     //
    public int flag = 0;
    public List<Item> invisibleChildren;

    public Item() {

    }

    public Item(int type, String text) {
        this.type = type;
        this.text = text;
    }

    public Item(int type, String text, int flag) {
        this.type = type;
        this.text = text;
        this.flag = flag;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
