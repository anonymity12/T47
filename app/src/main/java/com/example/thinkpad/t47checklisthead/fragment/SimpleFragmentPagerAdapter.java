package com.example.thinkpad.t47checklisthead.fragment;

import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentPagerAdapter;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

import com.example.thinkpad.t47checklisthead.R;

/**
 * Created by thinkpad on 2018/3/5.
 * A simple FragmentPagerAdapter for showing tabs and fragments(PageFragment)
 */

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter{
    private int[] imageResId = {R.drawable.ic_light,
            R.drawable.ic_robot,
            R.drawable.ic_sensor};
    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[]{"tab1","tab2","tab3"};
    private Context context;

    public SimpleFragmentPagerAdapter(FragmentManager fm,Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return PageFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        //Generate title based on item position
//        return tabTitles[position];
        //Generate image title
        Drawable image = context.getResources().getDrawable(imageResId[position]);
        image.setBounds(0,0,image.getIntrinsicWidth(),image.getIntrinsicHeight());
        SpannableString sb = new SpannableString("  ");
        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;
    }
}
