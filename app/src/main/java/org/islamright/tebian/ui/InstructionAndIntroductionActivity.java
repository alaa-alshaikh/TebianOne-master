package org.islamright.tebian.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import org.islamright.tebian.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class InstructionAndIntroductionActivity extends AppCompatActivity {

    @InjectView(R.id.ivImage)
    ImageView ivImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction_and_introduction);
        ButterKnife.inject(this);
        ivImage.setImageResource(getIntent().getIntExtra("image", R.drawable.ic_launcher));
    }


}
