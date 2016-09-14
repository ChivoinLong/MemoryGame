package thebird.com.memory;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements ListView.OnItemClickListener{

    private static final String PREFS_NAME = "Setting";
    public static Typeface typeface;
    SharedPreferences prefs;
    TextView title;
    ListView menuList;
    ArrayAdapter listAdapter;
    List<ListItem> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menuList = (ListView) findViewById(R.id.list_menu);
        menuList.setOnItemClickListener(this);

        title = (TextView) findViewById(R.id.menuTitle);
        typeface = Typeface.createFromAsset(getAssets(), "font/SweetMemories.ttf");
        title.setTypeface(typeface);

        prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        if (!prefs.contains("music") || !prefs.contains("sound") || !prefs.contains("theme")){
            SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
            editor.putBoolean("music",true);
            editor.putBoolean("sound",true);
            editor.putInt("theme", R.color.PINK);
            editor.apply();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        /*     MUSIC    */
        if (prefs.getBoolean("music", false)) {
            startService(new Intent(this, BackgroundMusicService.class));
        }

        /*    THEME   */
        if (prefs.contains("theme")) {
            int color = getResources().getColor(prefs.getInt("theme", R.color.PINK));
            listItems = new ArrayList<>();
            listItems.add(new ListItem(getResources().getString(R.string.play), color));
            listItems.add(new ListItem(getResources().getString(R.string.score), color));
            listItems.add(new ListItem(getResources().getString(R.string.setting), color));
            listItems.add(new ListItem(getResources().getString(R.string.help), color));
            title.setTextColor(color);
            listAdapter = new CustomListAdapter(this, R.layout.listview_item, listItems);
            menuList.setAdapter(listAdapter);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isFinishing()) {
            stopService(new Intent(this, BackgroundMusicService.class));
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i;
        switch (position){
            case 0:
                TopickerFragment topickerFragment = new TopickerFragment();
                topickerFragment.show(getFragmentManager(), "MyDialogFragment");
                break;
            case 1:
                i = new Intent(MainActivity.this, ViewScores.class);
                startActivity(i);
                break;
            case 2:
                i = new Intent(MainActivity.this, Settings.class);
                startActivity(i);
                break;
            case 3:
//                        i = new Intent(MainActivity.this, Help.class);
//                        startActivity(i);
                break;
            default:

                break;
        }
    }
}
