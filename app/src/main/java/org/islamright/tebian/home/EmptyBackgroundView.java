package org.islamright.tebian.home;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Rect;
import android.view.View;

import org.islamright.tebian.R;
import org.islamright.tebian.util.Key;
import org.islamright.tebian.util.Preferences;

/**
 * Created by AlaaAlShaikh on 22/06/15.
 */
public class EmptyBackgroundView extends View {
    private float y;
    public EmptyBackgroundView(Context context , float y) {
        super(context);
        this.y = y;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.empty_background);
        canvas.drawBitmap(bm, 0, y, new Paint());
    }
}