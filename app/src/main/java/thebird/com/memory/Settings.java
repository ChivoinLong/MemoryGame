package thebird.com.memory;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import thebird.com.memory.additional_classes.BackgroundMusicService;
import thebird.com.memory.additional_classes.Score_DBController;

public class Settings extends Activity implements Switch.OnClickListener, RadioGroup.OnCheckedChangeListener {

    public static final String PREFS_NAME = "Setting";
    SharedPreferences prefs = null;
    SharedPreferences.Editor editor = null;
    Score_DBController dbController;
    DatabaseErrorHandler error ;
    SQLiteDatabase.CursorFactory factory;
    TextView title = null;
    Switch swMusic = null;
    Switch swSound = null;
    RadioGroup themeRGroup, levelRGroup;
    Button btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        title = (TextView) findViewById(R.id.settingTitle);
        Typeface typeface = MainActivity.typeface;
        title.setTypeface(typeface);

        swMusic = (Switch) findViewById(R.id.swMusic);
        swSound = (Switch) findViewById(R.id.swSound);
        btnReset = (Button) findViewById(R.id.btnReset);
        swMusic.setTypeface(typeface);
        swSound.setTypeface(typeface);
        swMusic.setOnClickListener(this);
        swSound.setOnClickListener(this);
        btnReset.setOnClickListener(this);

        themeRGroup = (RadioGroup) findViewById(R.id.themeRGroup);
        themeRGroup.setOnCheckedChangeListener(this);

        levelRGroup = (RadioGroup) findViewById(R.id.levelRGroup);
        levelRGroup.setOnCheckedChangeListener(this);

        prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopService(new Intent(this, BackgroundMusicService.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        /*    MUSIC   */
        boolean isMusicOn = prefs.getBoolean("music", false);
        if (isMusicOn)
            startService(new Intent(this, BackgroundMusicService.class));
        swMusic.setChecked(isMusicOn);

        /*    SOUND   */
        boolean isSoundOn = prefs.getBoolean("sound", false);
        swSound.setChecked(isSoundOn);

        /*    THEME   */
        int color = prefs.getInt("theme", R.color.PINK);
        switch (color) {
            case R.color.RED:
                themeRGroup.check(R.id.red);
                break;
            case R.color.GREEN:
                themeRGroup.check(R.id.green);
                break;
            case R.color.BLUE:
                themeRGroup.check(R.id.blue);
                break;
            case R.color.PINK:
                themeRGroup.check(R.id.pink);
                break;
            case R.color.PURPLE:
                themeRGroup.check(R.id.purple);
                break;
            case R.color.ORANGE:
                themeRGroup.check(R.id.orange);
                break;
        }//end switch
        color = getResources().getColor(prefs.getInt("theme", R.color.PINK));
        title.setTextColor(color);
        swSound.getThumbDrawable().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
        swMusic.getThumbDrawable().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
//            swSound.getTrackDrawable().setColorFilter(color, PorterDuff.Mode.MULTIPLY);

        /*    LEVEL   */
        int level = prefs.getInt("level", 0);
        switch (level) {
            case 0:
                levelRGroup.check(R.id.easyLevel);
                break;
            case 1:
                levelRGroup.check(R.id.mediumLevel);
                break;
            case 2:
                levelRGroup.check(R.id.hardLevel);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        switch (v.getId()){
            case R.id.swMusic:
                if (((Switch) v).isChecked())
                    editor.putBoolean("music",true);
                else
                    editor.putBoolean("music",false);
                break;
            case R.id.swSound:
                if (((Switch) v).isChecked())
                    editor.putBoolean("sound",true);
                else
                    editor.putBoolean("sound",false);
                break;
            case R.id.btnReset:
                new AlertDialog.Builder(this)
                        .setTitle("Confirm")
                        .setMessage("Are you sure that you want to reset the game?")
                        .setPositiveButton("No", null)
                        .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                editor = getSharedPreferences(Settings.PREFS_NAME, MODE_PRIVATE).edit();
                                editor.putBoolean("music",true);
                                editor.putBoolean("sound",true);
                                editor.putInt("theme", R.color.PINK);
                                editor.putInt("level", 0);
                                editor.apply();

                                dbController = new Score_DBController(getApplicationContext(),factory,1,error);
                                dbController.deleteAllData();
                                onResume();
                            }

                        })
                        .show();
                break;
        }
        editor.apply();

        boolean isMusicOn = prefs.getBoolean("music", false);
        if (isMusicOn) {
            startService(new Intent(this, BackgroundMusicService.class));
        }
        else {
            stopService(new Intent(this, BackgroundMusicService.class));
        }
//        Toast.makeText(getApplicationContext(),"music on? " + String.valueOf(isMusicOn),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        switch (checkedId){
            case R.id.red:
                editor.putInt("theme", R.color.RED);
                break;
            case R.id.green:
                editor.putInt("theme", R.color.GREEN);
                break;
            case R.id.blue:
                editor.putInt("theme", R.color.BLUE);
                break;
            case R.id.pink:
                editor.putInt("theme", R.color.PINK);
                break;
            case R.id.purple:
                editor.putInt("theme", R.color.PURPLE);
                break;
            case R.id.orange:
                editor.putInt("theme", R.color.ORANGE);
                break;
            case R.id.easyLevel:
                editor.putInt("level", 0);
                break;
            case R.id.mediumLevel:
                editor.putInt("level", 1);
                break;
            case R.id.hardLevel:
                editor.putInt("level", 2);
                break;
        }//end switch
        editor.apply();

        int color = getResources().getColor(prefs.getInt("theme", R.color.PINK));
        title.setTextColor(color);
        swSound.getThumbDrawable().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
        swMusic.getThumbDrawable().setColorFilter(color, PorterDuff.Mode.MULTIPLY);

//        swSound.getTrackDrawable().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
//        Toast.makeText(getApplicationContext(), colorTheme + " theme", Toast.LENGTH_SHORT).show();
    }
}
