package thebird.com.memory;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Vector;

public class ViewScores extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_scores);
        TextView title = (TextView) findViewById(R.id.scoreTitle);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "font/el_barrio.ttf");
        title.setTypeface(typeface);

        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec tabSpec1 = tabHost.newTabSpec("four_four");
        tabSpec1.setIndicator("4x4");
        tabSpec1.setContent(R.id.scrollView44);
        tabHost.addTab(tabSpec1);

        TabHost.TabSpec tabSpec2 = tabHost.newTabSpec("six_six");
        tabSpec2.setIndicator("6x6");
        tabSpec2.setContent(R.id.scrollView66);
        tabHost.addTab(tabSpec2);

        tabHost.setCurrentTab(0);

        TableLayout score44 = (TableLayout) findViewById(R.id.tabLayout44);
    }

    protected void updateScore(TableLayout tableLayout, Vector Score){
        for (int i = 0; i < Score.size(); i++){
            TableRow tableRow = new TableRow(this);

            TextView tvName = new TextView(this);
            tvName.setText(((Score)Score.elementAt(i)).playerName);
            tableRow.addView(tvName);

            TextView tvTries = new TextView(this);
            tvTries.setText(((Score)Score.elementAt(i)).numTries);
            tableRow.addView(tvTries);

            tableLayout.addView(tableRow);
        }
    }

    protected void insertScore(Score score, Vector scoreList){
//        for (int i = 0; i < scoreList.size(); i++) {
//            int numTries = score.numTries;
//            Score tmpScore = scoreList.elementAt(i);
//
//            if(numTries < tmpScore.numTries){
//                scoreList.insertElementAt(i);
//                return;
//            }
//        }
    }
}
