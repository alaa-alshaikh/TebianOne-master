package org.islamright.tebian.home;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import org.islamright.tebian.page.PageFragment;
import org.islamright.tebian.util.Constant;
import org.islamright.tebian.util.Util;

/**
 * Created by AlaaAlShaikh on 10/05/15.
 */
public class FragmentPagesAdapter extends FragmentStatePagerAdapter {

    public FragmentPagesAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public PageFragment getItem(int position) {
        return  PageFragment.newInstance(position);
    }

    @Override
    public int getCount() {
       // return Util.allImagesAndPagesDownloaded() ? Constant.MAX_PAGES : Constant.MIN_PAGES;
        return Constant.MAX_PAGES;
    }
}
