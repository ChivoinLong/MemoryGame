package thebird.com.memory;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import thebird.com.memory.additional_classes.TabsAdapter;

public class ViewScores extends FragmentActivity implements ActionBar.TabListener {

    private ViewPager viewPager;
    private TabsAdapter tabsAdapter;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ScoreTheme);
        setContentView(R.layout.activity_view_scores);


        viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getActionBar();
        tabsAdapter = new TabsAdapter(getSupportFragmentManager());

        viewPager.setAdapter(tabsAdapter);
        actionBar.hide();
        actionBar.setTitle("High Scores");
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        for (String tabName : TabsAdapter.getTabNames()) {
            ActionBar.Tab tab = actionBar
                    .newTab()
                    .setText(tabName)
                    .setTabListener(this);
            actionBar.addTab(tab);
        }

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }


//    protected void updateScore(TableLayout tableLayout, Vector Score){
//        for (int i = 0; i < Score.size(); i++){
//            TableRow tableRow = new TableRow(this);
//
//            TextView tvName = new TextView(this);
//            tvName.setText(((Score)Score.elementAt(i)).playerName);
//            tableRow.addView(tvName);
//
//            TextView tvTries = new TextView(this);
//            tvTries.setText(((Score)Score.elementAt(i)).numTries);
//            tableRow.addView(tvTries);
//
//            tableLayout.addView(tableRow);
//        }
//    }

//    protected void insertScore(Score score, Vector scoreList){
//        for (int i = 0; i < scoreList.size(); i++) {
//            int numTries = score.numTries;
//            Score tmpScore = scoreList.elementAt(i);
//
//            if(numTries < tmpScore.numTries){
//                scoreList.insertElementAt(i);
//                return;
//            }
//        }
//    }
}
