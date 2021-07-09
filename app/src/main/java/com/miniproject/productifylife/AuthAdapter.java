package com.miniproject.productifylife;

import android.content.Context;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import org.jetbrains.annotations.NotNull;

public class AuthAdapter extends FragmentPagerAdapter {

   private Context mycontext;
   int totalTabs;

    public AuthAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        this.mycontext = context;
        this.totalTabs = totalTabs;
    }

    @NotNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                LoginFragment loginFragment = new LoginFragment();
                return loginFragment;
            case 1:
                SignupFragment signupFragment = new SignupFragment();
                return signupFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 0;
    }
}
