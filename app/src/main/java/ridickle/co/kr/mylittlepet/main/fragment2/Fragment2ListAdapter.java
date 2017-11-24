package ridickle.co.kr.mylittlepet.main.fragment2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ridickle.co.kr.mylittlepet.R;
import ridickle.co.kr.mylittlepet.main.fragment2.MainF2Dialog.MainF2DialogFragment;

import static android.view.View.GONE;
import static java.lang.Integer.parseInt;
import static ridickle.co.kr.mylittlepet.main.fragment2.MainFragment2.initTextView;

/**
 * Created by ridickle on 2017. 10. 17..
 */

public class Fragment2ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int HEADER = 0;
    public static final int CHILD = 1;

    Context context;
    private List<Item> data;
    MainF2Model mF2Model = MainF2Model.getInstance();

    public Fragment2ListAdapter(Context context) {
        this.context = context;
        data = mF2Model.initData();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;

        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        switch (viewType) {
            case HEADER:
                view = inflater.inflate(R.layout.item_main_fragment2_parent, parent, false);
                SearchParentHolder header = new SearchParentHolder(view);
                return header;

            case CHILD:
                view = inflater.inflate(R.layout.item_main_fragment2_child, parent, false);
                SearchChildHolder footer = new SearchChildHolder(view);
                return footer;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Item item = data.get(position);
        switch (item.type) {
            case HEADER:
                final SearchParentHolder parentController = (SearchParentHolder) holder;
                parentController.innerItem = item;
                parentController.parentTitle.setText(item.text);
                if (item.invisibleChildren == null) {
                    parentController.btn_expand_toggle.setImageResource(R.mipmap.ic_launcher);
                } else {
                    parentController.btn_expand_toggle.setImageResource(R.mipmap.ic_launcher_round);
                }

                parentController.parentTitle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        initTextView.setVisibility(GONE);

                        int pos = data.indexOf(parentController.innerItem);
                        // 리스트를 줄이는 과정
                        if (item.invisibleChildren == null) {
                            item.invisibleChildren = new ArrayList<Item>();

                            int index = mF2Model.collapseList(pos, item, data);
                            notifyItemRangeRemoved(pos + 1, index);
                            parentController.btn_expand_toggle.setImageResource(R.mipmap.ic_launcher);

//                            if (MainActivity.f2BottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED)
//                                MainActivity.f2BottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                        }

                        // 리스트를 팽창하는 과정
                        else {
                            int index = mF2Model.expandList(pos, item, data);

                            notifyItemRangeInserted(pos + 1, index - pos - 1);
                            parentController.btn_expand_toggle.setImageResource(R.mipmap.ic_launcher_round);
                            item.invisibleChildren = null;

//                            if (MainActivity.f2BottomSheetBehavior.getState() == BottomSheetBehavior.STATE_HIDDEN)
//                                MainActivity.f2BottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                        }
                    }
                });
                break;

            case CHILD:
                int checkIndex = parseInt(item.text);
                final SearchChildHolder childController = (SearchChildHolder) holder;
                final MainF2DialogFragment mF2Dialog;

                if (checkIndex == 4) {   // Seekbar 가 나와야 할 경우
                    mF2Dialog = MainF2DialogFragment.newInstance(checkIndex, null);
                    childController.f2ChildSeekBarLayout.setVisibility(View.VISIBLE);
                    childController.f2ChildText.setVisibility(View.GONE);
                    childController.f2ChildSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean booleanValue) {
                            if (progress <= 25) {
                                seekBar.setProgress(0);
                                mF2Dialog.setSeekBar(0);
                            } else if (progress >= 75) {
                                seekBar.setProgress(100);
                                mF2Dialog.setSeekBar(2);
                            } else {
                                seekBar.setProgress(50);
                                mF2Dialog.setSeekBar(1);
                            }
                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {

                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {

                        }
                    });
                } else {
                    if (item.flag == 0) {
                        childController.f2ChildSeekBarLayout.setVisibility(View.GONE);
                        childController.f2ChildText.setVisibility(View.VISIBLE);
                        if (!childController.f2ChildText.getText().equals(""))
                            childController.f2ChildText.setText("");

                        mF2Dialog = MainF2DialogFragment.newInstance(checkIndex, childController.f2ChildText);
                        mF2Dialog.show(MainFragment2.getInstance().getFragmentManager(), "");
                        item.setFlag(1);
                    }
                }
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).type;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class SearchParentHolder extends RecyclerView.ViewHolder {
        public TextView parentTitle;
        public ImageView btn_expand_toggle;
        public Item innerItem;

        public SearchParentHolder(View itemView) {
            super(itemView);
            parentTitle = (TextView) itemView.findViewById(R.id.f2ParentTitle);
            btn_expand_toggle = (ImageView) itemView.findViewById(R.id.f2ParentArrow);
        }
    }

    public class SearchChildHolder extends RecyclerView.ViewHolder {
        public TextView f2ChildText;
        public LinearLayout f2ChildSeekBarLayout;
        public SeekBar f2ChildSeekBar;

        public SearchChildHolder(View itemView) {
            super(itemView);
            f2ChildText = (TextView) itemView.findViewById(R.id.f2ChildText);
            f2ChildSeekBarLayout = (LinearLayout) itemView.findViewById(R.id.f2ChildSeekBarLayout);
            f2ChildSeekBar = (SeekBar) itemView.findViewById(R.id.f2ChildSeekBar);
        }

    }
}
