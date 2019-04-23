package com.example.bazaid.e_store;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class Pager extends FragmentStatePagerAdapter {
    int tabCount;
    public Pager(FragmentManager fm,int tabCount){
        super(fm);
        this.tabCount=tabCount;
    }
    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0 :
                Home home=new Home();
                return home;
            case 1 :
                Wishilist wl=new Wishilist();
                return wl;
            case 2 :
                Cart cart=new Cart();
                return cart;
            case 3 :
                Contact cont =new Contact();
                return cont;
            default:  return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
