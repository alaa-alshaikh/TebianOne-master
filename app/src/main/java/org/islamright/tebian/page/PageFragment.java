package org.islamright.tebian.page;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.islamright.tebian.R;
import org.islamright.tebian.explanation.ExplanationActivity;
import org.islamright.tebian.model.Aya;
import org.islamright.tebian.util.Key;
import org.islamright.tebian.util.Util;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class PageFragment extends Fragment implements PageView, View.OnClickListener, View.OnTouchListener {

    @InjectView(R.id.ivImage)
    ImageView ivImage;

    private PagePresenter presenter = new PagePresenterImpl(this);

    public PageFragment() {
        // Required empty public constructor
    }

    public static PageFragment newInstance(int position) {
        PageFragment fragment = new PageFragment();
        Bundle args = new Bundle();
        args.putInt(Key.PAGE_NUMBER, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            presenter.onCreate(getArguments());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_page, container, false);
        presenter.onCreateView(view);
        return view;
    }

    @Override
    public void initView(View view) {
        ButterKnife.inject(this, view);
        view.setOnClickListener(this);
        view.setOnTouchListener(this);
    }

    @Override
    public void setImage(Drawable drawable, final ArrayList<Aya> arrayList) {
        ivImage.setImageDrawable(drawable);
    }

    @Override
    public void onClick(View v) {
        presenter.onClick();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        presenter.onTouch(event.getX(), event.getY());
        return false;
    }

    @Override
    public void showMessage(String message) {
        Util.toaster(getActivity(), message);
    }

    @Override
    public void goToExplanation(Intent intent) {
        intent.setClass(getActivity() , ExplanationActivity.class);
        startActivity(intent);

    }
}
