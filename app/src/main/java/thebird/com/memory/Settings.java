package thebird.com.memory;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import thebird.com.memory.additional_classes.BackgroundMusicService;

public class Settings extends Activity implements Switch.OnClickListener, RadioGroup.OnCheckedChangeListener {

    public static final String PREFS_NAME = "Setting";
    SharedPreferences prefs = null;
    SharedPreferences.Editor editor = null;
    TextView title = null;
    Switch swMusic = null;
    Switch swSound = null;
    RadioGroup themeRGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        title = (TextView) findViewById(R.id.settingTitle);
        Typeface typeface = MainActivity.typeface;
        title.setTypeface(typeface);

        swMusic = (Switch) findViewById(R.id.swMusic);
        swSound = (Switch) findViewById(R.id.swSound);
        swMusic.setTypeface(typeface);
        swSound.setTypeface(typeface);
        swMusic.setOnClickListener(this);
        swSound.setOnClickListener(this);

        themeRGroup = (RadioGroup) findViewById(R.id.themeRGroup);
        themeRGroup.setOnCheckedChangeListener(this);

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
        if (prefs.contains("music")) {
            boolean isMusicOn = prefs.getBoolean("music", false);
            swMusic.setChecked(isMusicOn);
        }//end if

        if (prefs.contains("sound")) {
            boolean isSoundOn = prefs.getBoolean("sound", false);
            swSound.setChecked(isSoundOn);
        }//end if

        if (prefs.contains("theme")){
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
