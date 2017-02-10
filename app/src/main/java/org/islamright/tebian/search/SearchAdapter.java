package org.islamright.tebian.search;

import android.graphics.Color;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.islamright.tebian.App;
import org.islamright.tebian.R;
import org.islamright.tebian.model.Aya;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by AlaaAlShaikh on 14/05/15.
 */
public class SearchAdapter extends BaseAdapter {
    private ArrayList<Aya> ayatList;
    private String word;

    public SearchAdapter(ArrayList<Aya> ayatList, String word) {
        this.ayatList = ayatList;
        this.word = word;
    }

    @Override
    public int getCount() {
        return ayatList.size();
    }

    @Override
    public Aya getItem(int position) {
        return ayatList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getID();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(App.getInstance()).inflate(R.layout.item_search_result, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (position % 2 == 0) {
            convertView.setBackgroundColor(Color.LTGRAY);
        } else {
            convertView.setBackgroundColor(Color.WHITE);
        }


        viewHolder.tvAyaText.setText(getHeightLightWord(word, getItem(position).getTextWithoutTashkil()));

        viewHolder.tvAyaNumber.setText(App.getInstance().getString(R.string.ayaNumber) + " " + getItem(position).getAyaNumberGeneral());
        viewHolder.tvPageNumber.setText(App.getInstance().getString(R.string.pageNumber) + " " + getItem(position).getPageNumber());
        viewHolder.tvSoraName.setText(App.getInstance().getString(R.string.soraName) + " " + getItem(position).getSoraName());


        return convertView;
    }

    private Spanned getHeightLightWord(String word, String statement) {

        String highLight = "<font color='#FF0000'>" + word + "</font>";

        return Html.fromHtml(statement.replaceAll(word, highLight));
    }

    static class ViewHolder {
        @InjectView(R.id.tvAyaText)
        TextView tvAyaText;
        @InjectView(R.id.tvAyaNumber)
        TextView tvAyaNumber;
        @InjectView(R.id.tvPageNumber)
        TextView tvPageNumber;
        @InjectView(R.id.tvSoraName)
        TextView tvSoraName;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

}
