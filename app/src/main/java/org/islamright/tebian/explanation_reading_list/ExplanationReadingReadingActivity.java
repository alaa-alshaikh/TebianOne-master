package org.islamright.tebian.explanation_reading_list;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.islamright.tebian.R;
import org.islamright.tebian.util.Key;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ExplanationReadingReadingActivity extends AppCompatActivity implements ExplanationReadingView, /*OnExplanationSelected,*/ AdapterView.OnItemClickListener, DialogInterface.OnClickListener {
    // @InjectView(R.id.explanationListView)
    // RecyclerView mExplanationList;
    @InjectView(R.id.listView2)
    ListView mlist;
    ExplanationReadingPresenter mPresenter = new ExplanationReadingReadingPresenterImpl(this);
    ProgressDialog progressDialog;
    private AlertDialog.Builder alertDialog;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explanation_download);
        mPresenter.onCreate(getIntent().getExtras().getString(Key.READING_EXPLANATION));
    }

    @Override
    public void initView() {
        ButterKnife.inject(this);
        progressDialog = new ProgressDialog(ExplanationReadingReadingActivity.this);
        progressDialog.setTitle(getString(R.string.processing));
        progressDialog.setMessage(getString(R.string.please_wait));
        progressDialog.setCancelable(false);
        progressDialog.show();

        alertDialog = new AlertDialog.Builder(ExplanationReadingReadingActivity.this);
        alertDialog.setMessage(getString(R.string.msgPromptDownloadFirstPages))
                .setPositiveButton(getString(R.string.confirm), this)
                .setNegativeButton(getString(R.string.cancel), this);
    }

    @Override
    public void setItems(List<ExplanationOrReadingItem> items) {
        // add adapter
//        LinearLayoutManager layoutManager = new LinearLayoutManager(ExplanationReadingReadingActivity.this);
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        mExplanationList.setLayoutManager(layoutManager);
//        ExplanationAdapter mExplanationAdapter = new ExplanationAdapter(items);
//        mExplanationAdapter.setOnExplanationSelected(this);
//        mExplanationList.setAdapter(mExplanationAdapter);

        //  ExplanationOrReadingAdapter mExplanationAdapter = new ExplanationOrReadingAdapter(items);
        //  mExplanationAdapter.setOnExplanationSelected(this);
        progressDialog.dismiss();
        mlist.setAdapter(new ExplanationOrReadingAdapter(items));
        mlist.setOnItemClickListener(this);
    }


    @Override
    public void finishView(String id) {
        if (id.startsWith("error"))
            progressDialog.dismiss();
        else {
            alertDialog.show();
        }
// start loading the selected file
    }

//    @Override
//    public void onSelected(ExplanationOrReadingItem item) {
//        //  finishView(item.getUrl());
//        // mPresenter.itemClick(item.getUrl());
//    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mPresenter.itemClick(position);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            case DialogInterface.BUTTON_POSITIVE:

//           Intent i = new Intent(this, DownloadExplanationActivity.class);
//                startActivity(i);
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                //No button clicked

                break;
        }
    }
}
