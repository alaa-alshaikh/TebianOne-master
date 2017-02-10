package org.islamright.tebian.page_by_number;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.islamright.tebian.R;
import org.islamright.tebian.util.Key;
import org.islamright.tebian.util.Util;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class EnterPageNumberActivity extends AppCompatActivity implements EnterPageNumberView , View.OnKeyListener{

    @InjectView(R.id.etPageNumber)
    EditText etPageNumber;
    @InjectView(R.id.btnGo)
    Button btnGo;
    @InjectView(R.id.btnCancel)
    Button btnCancel;

    private EnterPageNumberPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_page_number);
        initView();
    }

    private void initView() {
        ButterKnife.inject(this);

        etPageNumber.setFocusableInTouchMode(true);
        etPageNumber.requestFocus();
        etPageNumber.setOnKeyListener(this);

        presenter = new EnterPageNumberPresenterImpl(this);
    }

    @OnClick(R.id.btnGo)
    public void btnGo() {
        presenter.go(etPageNumber.getText().toString().trim());
    }

    @OnClick(R.id.btnCancel)
    public void btnCancel() {
        presenter.cancel();
    }

    @Override
    public void finishView(int pageNumber) {
        setResult(RESULT_OK, new Intent().putExtra(Key.LAST_PAGE_OPEN, pageNumber));
        finish();
    }

    @Override
    public void finishView() {
        finish();
    }

    @Override
    public void showMessage(String message) {
        Util.toaster(this , message);
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
            presenter.go(etPageNumber.getText().toString().trim());
            return true;
        }

        return false;
    }
}
