package org.islamright.tebian.explanation_reading_list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.islamright.tebian.R;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by AlaaAlShaikh on 29/10/2015.
 */
public class ExplanationAdapter extends RecyclerView.Adapter<ExplanationAdapter.Holder> {
    List<ExplanationOrReadingItem> mExplanationOrReadingItems;
    //OnExplanationSelected mOnExplanationSelected;


//    public void setOnExplanationSelected(OnExplanationSelected mOnExplanationSelected) {
//        this.mOnExplanationSelected = mOnExplanationSelected;
//    }


    public ExplanationAdapter(List<ExplanationOrReadingItem> mExplanationOrReadingItems) {
        this.mExplanationOrReadingItems = mExplanationOrReadingItems;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.explanation_item, parent, false);
        Holder mHolder = new Holder(mView);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return mExplanationOrReadingItems.size();
    }


    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @InjectView(R.id.explanationNumber)
        TextView mExplanationNumber;
        @InjectView(R.id.explanationName)
        TextView mExplanationName;
        @InjectView(R.id.explanationBg)
        LinearLayout mExplanationBg;
        protected int mPosition;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }

        public void onBind(int position) {
            mExplanationBg.setBackgroundResource(position % 2 == 0 ? R.drawable.item_faharas_bg_orange
                    : R.drawable.item_faharas_bg_whit);
            mExplanationName.setText(mExplanationOrReadingItems.get(position).getName());
            mExplanationNumber.setText(position++);
            mPosition= position;
        }

        @Override
        public void onClick(View v) {
//            if (mOnExplanationSelected != null)
//                mOnExplanationSelected.onSelected(mExplanationOrReadingItems.get(mPosition));
        }
    }
}
