package thebird.com.memory.additional_classes;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Chivoin Long on 12-Aug-16.
 */
public class TabsAdapter extends FragmentPagerAdapter {

    public static String[] tabNames = {FragmentLvlEasy.fragmentName, FragmentLvlMedium.fragmentName, FragmentLvlHard.fragmentName};

    public TabsAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    public static String[] getTabNames() {
        return tabNames;
    }

    public static void setTabNames(String[] tabNames) {
        TabsAdapter.tabNames = tabNames;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FragmentLvlEasy();
            case 1:
                return new FragmentLvlMedium();
            case 2:
                return new FragmentLvlHard();
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabNames.length;
    }
}
