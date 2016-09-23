package thebird.com.memory;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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

import thebird.com.memory.additional_classes.BackgroundMusicService;
import thebird.com.memory.additional_classes.CustomListAdapter;
import thebird.com.memory.additional_classes.ListItem;

public class MainActivity extends Activity implements ListView.OnItemClickListener{

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

        prefs = getSharedPreferences(Settings.PREFS_NAME, MODE_PRIVATE);
        if (!prefs.contains("music") || !prefs.contains("sound") || !prefs.contains("theme") || !prefs.contains("level")){
            SharedPreferences.Editor editor = getSharedPreferences(Settings.PREFS_NAME, MODE_PRIVATE).edit();
            editor.putBoolean("music",true);
            editor.putBoolean("sound",true);
            editor.putInt("theme", R.color.PINK);
            editor.putInt("level", 0);
            editor.apply();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
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
            listItems.add(new ListItem(getResources().getString(R.string.about), color));
            title.setTextColor(color);
            listAdapter = new CustomListAdapter(this, R.layout.listview_item, listItems, "");
            menuList.setAdapter(listAdapter);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopService(new Intent(this, BackgroundMusicService.class));
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Exit")
                .setMessage("Are you sure that you want to exit from this game?")
                .setPositiveButton("Continue playing", null)
                .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i;
        switch (position){
            case 0:
                GameTypePickerFragment gameTypePickerFragment = new GameTypePickerFragment();
                gameTypePickerFragment.show(getFragmentManager(), "Pick Game Type Fragment");
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
                        i = new Intent(MainActivity.this, About.class);
                        startActivity(i);
                break;
            default:

                break;
        }
    }
}
