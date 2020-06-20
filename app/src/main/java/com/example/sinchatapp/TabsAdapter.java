package com.example.sinchatapp;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


public class TabsAdapter extends FragmentPagerAdapter {
    public TabsAdapter( FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        //İstekler tabında 0 ise istekleri getir
        switch (position){
            case 0:
                RequetsFragment requetsFragment=new RequetsFragment();
                return requetsFragment;
            case 1:
                MesajlarFragment mesajlarFragment=new MesajlarFragment();
                return mesajlarFragment;
            case 2:
                ArkdaslarFragment arkdaslarFragment=new ArkdaslarFragment();
                return arkdaslarFragment;
             default:
                 return null;
        }
    }
    @Override
    //3 sekmemiz oldugundan dolayı 3 yaaıxoyuz.
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "İSTEKLER";
            case 1:
                return "MESAJLAR";
            case 2:
                return "ARKDAŞLAR";
             default:
                 return  null;
        }

    }
}
