package ridickle.co.kr.mylittlepet.main.fragment1.fragment1_3;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ridickle on 2017. 10. 12..
 */

public class MainF1_3Model {
    AtomicInteger sNextGeneratedId;

    public ArrayList<Integer> tagInflating(final LinearLayout layout, ArrayList<String> dummyList) {
        sNextGeneratedId = new AtomicInteger(1);

        ArrayList<Integer> textList = new ArrayList<>();
        for (int i = 0; i < dummyList.size(); ) {
            LinearLayout itemRow = new LinearLayout(layout.getContext());
            itemRow.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            itemRow.setOrientation(LinearLayout.HORIZONTAL);

            for (int j = 0; j < 4; j++, i++) {
                TextView tagText = new TextView(layout.getContext());
                tagText.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                tagText.setPadding(30, 30, 30, 30);
                final int newId = generateViewId();
                tagText.setId(newId);
                textList.add(newId);
                tagText.setText(dummyList.get(i));

                itemRow.addView(tagText);
            }
            layout.addView((View) itemRow);
        }

        return textList;
    }

    /**
     * @return a generated ID value
     */
    public int generateViewId() {
        for (; ; ) {
            final int result = sNextGeneratedId.get();
            // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
            int newValue = result + 1;
            if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
            if (sNextGeneratedId.compareAndSet(result, newValue)) {
                return result;
            }
        }
    }
}
