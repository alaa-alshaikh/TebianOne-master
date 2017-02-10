package org.islamright.tebian.splash;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.islamright.tebian.R;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SplashPresenter presenter = new SplashPresenterImpl(this);
        presenter.onCreate();
    }


}
