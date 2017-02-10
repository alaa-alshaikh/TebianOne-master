package org.islamright.tebian.search;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.islamright.tebian.R;
import org.islamright.tebian.model.Aya;
import org.islamright.tebian.util.Util;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SearchActivity extends AppCompatActivity implements SearchView, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener, DialogInterface.OnClickListener, View.OnClickListener {

    @InjectView(R.id.btnFullSearch)
    Button btnFullSearch;
    @InjectView(R.id.btnSubSearch)
    Button btnSubSearch;
    @InjectView(R.id.etTextSearch)
    EditText etTextSearch;
    @InjectView(R.id.listView)
    ListView listView;
    @InjectView(R.id.tvNumberOfResult)
    TextView tvNumberOfResult;
    @InjectView(R.id.tvEmptyMessage)
    TextView tvEmptyMessage;


    private ArrayAdapter<String> arrayAdapter;

    private AlertDialog.Builder alertDialog;

    private AlertDialog.Builder ayaTextDialog;

    private SearchPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        presenter = new SearchPresenterImpl(this, this);
        presenter.onCreate();
    }


    @Override
    public void initView() {
        ButterKnife.inject(this);

        btnFullSearch.setOnClickListener(this);
        btnSubSearch.setOnClickListener(this);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);

        arrayAdapter = new ArrayAdapter(SearchActivity.this, android.R.layout.select_dialog_item);

        arrayAdapter.add(getString(R.string.showAyaText));
        arrayAdapter.add(getString(R.string.copy));
        arrayAdapter.add(getString(R.string.share));

        alertDialog = new AlertDialog.Builder(SearchActivity.this);

        alertDialog.setNegativeButton(R.string.cancel, null);

        alertDialog.setAdapter(arrayAdapter, this);

        ayaTextDialog = new AlertDialog.Builder(SearchActivity.this);

        ayaTextDialog.setNegativeButton(R.string.hide , null);

    }

    @Override
    public void setItems(ArrayList<Aya> items , String word) {
        listView.setAdapter(new SearchAdapter(items , word));
    }

    @Override
    public void setButtonsBackground(int btnSubSearchBackground, int btnFullSearchBackground) {
        btnSubSearch.setBackgroundResource(btnSubSearchBackground);
        btnFullSearch.setBackgroundResource(btnFullSearchBackground);
    }

    @Override
    public void showDialog() {
        alertDialog.show();
    }

    @Override
    public void showMessage(String message) {
        Util.toaster(this, message);
    }

    @Override
    public void showEmptyMessage(boolean showEmptyMessage) {
        tvEmptyMessage.setVisibility(showEmptyMessage ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setCountOfSearchResult(int count) {
        tvNumberOfResult.setText(getString(R.string.numberOfResult) + " " + count);
    }

    @Override
    public void showAyaTextDialog(String ayaText) {
        ayaTextDialog.setMessage(ayaText);
        ayaTextDialog.show();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

        switch (which) {
            case 0:
                presenter.showAyaText();
                break;
            case 1:
                presenter.copyAyaText();
                break;
            case 2:
                presenter.shareAyaText();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        presenter.itemClick(position);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        presenter.itemLongClick(position);
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnFullSearch:
                presenter.search(true , etTextSearch.getText().toString());
                break;
            case R.id.btnSubSearch:
                presenter.search(false , etTextSearch.getText().toString());
                break;
        }
    }
}
