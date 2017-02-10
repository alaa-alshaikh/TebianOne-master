package org.islamright.tebian.fahras;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.islamright.tebian.App;
import org.islamright.tebian.R;
import org.islamright.tebian.model.FahrasItem;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class FahrasAdapter extends BaseAdapter {

    ArrayList<FahrasItem> arrayList;

    public FahrasAdapter(ArrayList<FahrasItem> arrayList) {
        this.arrayList = arrayList;
    }


    @Override
    public int getCount() {
        return arrayList.size();
    }


    @Override
    public FahrasItem getItem(int position) {
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
            convertView = LayoutInflater.from(App.getInstance()).inflate(R.layout.item_fahras, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        convertView.setBackgroundResource(position % 2 == 0 ? R.drawable.item_faharas_bg_orange
                : R.drawable.item_faharas_bg_whit);

        viewHolder.tvSoraNumber.setText(getItem(position).getId());

        viewHolder.tvSoraPageNumber.setText(getItem(position).getStart() + "");

        viewHolder.tvSoraName.setText(getItem(position).getName());

        return convertView;
    }


    static class ViewHolder {
        @InjectView(R.id.tvSoraPageNumber)
        TextView tvSoraPageNumber;
        @InjectView(R.id.tvSoraName)
        TextView tvSoraName;
        @InjectView(R.id.tvSoraNumber)
        TextView tvSoraNumber;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }


}