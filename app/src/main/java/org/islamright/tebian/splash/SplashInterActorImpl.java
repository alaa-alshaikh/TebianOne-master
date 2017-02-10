package org.islamright.tebian.splash;

import org.islamright.tebian.util.Key;
import org.islamright.tebian.util.Preferences;

/**
 * Created by AlaaAlShaikh on 22/04/15.
 */
public class SplashInterActorImpl implements SplashInterActor {

    @Override
    public void saveScreenDimensions(int screenWidth, int screenHeight) {
        Preferences.getInstance().putInt(Key.SCREEN_WIDTH, screenWidth);
        Preferences.getInstance().putInt(Key.SCREEN_HIGHT, screenHeight);

    }

    @Override
    public void saveScreenPercentage(float percentageWidth, float percentageHeight , float percentageNewHeight) {

        Preferences.getInstance().putFloat(Key.PERCENTAGE_WIDTH, percentageWidth);
        Preferences.getInstance().putFloat(Key.PERCENTAGE_HEIGHT, percentageHeight);
        Preferences.getInstance().putFloat(Key.PERCENTAGE_NEW_HEIGHT, percentageNewHeight);
    }
}
