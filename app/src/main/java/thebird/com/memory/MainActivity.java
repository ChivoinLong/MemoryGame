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

public class MainActivity extends Activity implements ListView.OnItemClickListener{

    public static Typeface typeface;
    private static final String PREFS_NAME = "Setting";
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView title = (TextView) findViewById(R.id.tvGameName);
        typeface = Typeface.createFromAsset(getAssets(), "font/SweetMemories.ttf");
        title.setTypeface(typeface);
        ListView menuList = (ListView) findViewById(R.id.list_menu);

        ArrayAdapter listAdapter = new CustomListAdapter(this, getApplicationContext(),
                R.layout.menu_listview_item, getResources().getStringArray(R.array.menu_items));
        menuList.setAdapter(listAdapter);

        menuList.setOnItemClickListener(this);

        prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        if (!prefs.contains("music") || !prefs.contains("sound") || !prefs.contains("theme")){
            SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
            editor.putBoolean("music",true);
            editor.putBoolean("sound",true);
            editor.putString("theme", "pink");
            editor.apply();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i;
        switch (position){
            case 0:
                i = new Intent(MainActivity.this, PlayGame.class);
                startActivity(i);
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
                TopickerFragment topickerFragment = new TopickerFragment();
                topickerFragment.show(getFragmentManager(), "MyDialogFragment");
                break;
            default:

                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        if (prefs.getBoolean("music", false)){
            startService(new Intent(this, BackgroundMusicService.class));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isFinishing()) {
            stopService(new Intent(this, BackgroundMusicService.class));
        }
    }

}
