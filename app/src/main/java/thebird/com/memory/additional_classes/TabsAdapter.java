package thebird.com.memory.additional_classes;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Chivoin Long on 12-Aug-16.
 */
public class TabsAdapter extends FragmentPagerAdapter {

    public static String[] tabNames = {FragmentThreeByFour.fragmentName, FragmentFourByFive.fragmentName};

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
                return new FragmentThreeByFour();
            case 1:
                return new FragmentFourByFive();
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabNames.length;
    }
}
