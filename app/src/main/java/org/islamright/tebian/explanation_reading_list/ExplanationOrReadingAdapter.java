package org.islamright.tebian.explanation_reading_list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.islamright.tebian.App;
import org.islamright.tebian.R;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class ExplanationOrReadingAdapter extends BaseAdapter {

    List<ExplanationOrReadingItem> arrayList;

    public ExplanationOrReadingAdapter(List<ExplanationOrReadingItem> arrayList) {
        this.arrayList = arrayList;
    }


    @Override
    public int getCount() {

        return arrayList.size();
    }


    @Override
    public ExplanationOrReadingItem getItem(int position) {
        return arrayList.get(position);
    }


    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(App.getInstance()).inflate(R.layout.explanation_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        convertView.setBackgroundResource(position % 2 == 0 ? R.drawable.item_faharas_bg_orange
                : R.drawable.item_faharas_bg_whit);
          int x =position;
        x++;
        viewHolder.mExplanationNumber.setText(" "+x);

        viewHolder.mExplanationName.setText(getItem(position).getName());

        return convertView;
    }


    static class ViewHolder {

        @InjectView(R.id.explanationName)
        TextView mExplanationName;
        @InjectView(R.id.explanationNumber)
        TextView mExplanationNumber;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }


}