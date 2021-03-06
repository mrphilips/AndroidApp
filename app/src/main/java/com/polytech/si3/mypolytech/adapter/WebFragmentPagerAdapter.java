package com.polytech.si3.mypolytech.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.polytech.si3.mypolytech.fragment.WebFragment;

/**
 * Created by david on 15/04/2016.
 */
public class WebFragmentPagerAdapter extends FragmentPagerAdapter {

    private static int PAGE_COUNT = 3;
    private static String tabTitles[] = new String[] { "Tab1", "Tab2", "Tab3" };
    protected Context context;

    public WebFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        return WebFragment.newInstance(position + 1);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }

    public static void setCampusTabTitles(){
        PAGE_COUNT = 3;
        tabTitles = new String[] {"TEMPLIERS","LEARNING CENTER","LUCIOLES"};
    }

    public static void setAssosTabTitles(){
        PAGE_COUNT = 5;
        tabTitles = new String[] {"BDE","BDS","BDM","BDJ","BDC"};
    }
}
