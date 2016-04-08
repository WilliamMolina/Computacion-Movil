package com.example.wondercode.maps;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import com.google.android.gms.maps.SupportMapFragment;

/**
 * Created by wondercode on 7/04/16.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {
    private FragmentManager fma;
    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
        fma=fm;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        if(position==1){
            MapFragment mapFragment=MapFragment.newInstance();
            return mapFragment;
        }
        return PlaceholderFragment.newInstance(position + 1);
    }


    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Rutas";
            case 1:
                return "Mapa";
            case 2:
                return "Cerca";
        }
        return null;
    }
}
