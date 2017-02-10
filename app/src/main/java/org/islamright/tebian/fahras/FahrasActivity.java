package org.islamright.tebian.fahras;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.islamright.tebian.R;
import org.islamright.tebian.model.FahrasItem;
import org.islamright.tebian.util.Key;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class FahrasActivity extends AppCompatActivity implements FahrasView, AdapterView.OnItemClickListener {


    @InjectView(R.id.listView)
    ListView listView;

    private FahrasPresenter presenter = new FahrasPresenterImpl(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fahras);
        presenter.onCreate(getIntent().getExtras().getString(Key.FAHRAS_PATH));

    }

    @Override
    public void initView() {
        ButterKnife.inject(this);

    }

    @Override
    public void setItems(ArrayList<FahrasItem> items) {
        listView.setAdapter(new FahrasAdapter(items));
        listView.setOnItemClickListener(this);
    }

    @Override
    public void finishView(int pageNumber) {
        setResult(RESULT_OK, new Intent().putExtra(Key.LAST_PAGE_OPEN, pageNumber));
        finish();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        presenter.itemClick(position);
    }
}