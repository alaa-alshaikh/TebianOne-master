package org.islamright.tebian.explanation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.islamright.tebian.App;
import org.islamright.tebian.R;
import org.islamright.tebian.model.Explanation;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class ExplanationButtonAdapter extends BaseAdapter {

    ArrayList<Explanation> arrayList;

    public void setPosition(int position) {
        this.position = position;
    }

    private int position;

    public ExplanationButtonAdapter(ArrayList<Explanation> arrayList, int position) {
        this.arrayList = arrayList;
        this.position = position;
    }


    @Override
    public int getCount() {
        return arrayList.size();
    }


    @Override
    public Explanation getItem(int position) {
        return arrayList.get(position);
    }


    @Override
    public long getItemId(int position) {
        return getItem(position).getID();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(App.getInstance()).inflate(R.layout.item_explanation_button, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvExplanation.setText(getItem(position).getName());

        viewHolder.tvExplanation.setBackgroundResource(this.position == position ? R.drawable.btn_pressed : R.drawable.btn_enable);

        return convertView;
    }


    static class ViewHolder {
        @InjectView(R.id.tvExplanation)
        TextView tvExplanation;


        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }


}