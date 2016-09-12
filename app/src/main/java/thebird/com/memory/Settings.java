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
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopService(new Intent(this, BackgroundMusicService.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        if (prefs.contains("music")) {
            boolean isMusicOn = prefs.getBoolean("music", false);
            swMusic.setChecked(isMusicOn);
        }//end if

        if (prefs.contains("sound")) {
            boolean isSoundOn = prefs.getBoolean("sound", false);
            swSound.setChecked(isSoundOn);
        }//end if

        if (prefs.contains("theme")){
            String getTheme = prefs.getString("theme", "red");
            int color = getResources().getColor(R.color.grey);;
            switch (getTheme){
                case "red":
                    themeRGroup.check(R.id.red);
                    color = getResources().getColor(R.color.RED);
                    break;
                case "green":
                    themeRGroup.check(R.id.green);
                    color = getResources().getColor(R.color.GREEN);
                    break;
                case "blue":
                    themeRGroup.check(R.id.blue);
                    color = getResources().getColor(R.color.BLUE);
                    break;
                case "pink":
                    themeRGroup.check(R.id.pink);
                    color = getResources().getColor(R.color.PINK);
                    break;
                case "purple":
                    themeRGroup.check(R.id.purple);
                    color = getResources().getColor(R.color.PURPLE);
                    break;
                case "orange":
                    themeRGroup.check(R.id.orange);
                    color = getResources().getColor(R.color.ORANGE);
                    break;
            }//end switch
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

        prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
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
        int color = getResources().getColor(R.color.grey);
        switch (checkedId){
            case R.id.red:
                editor.putString("theme","red");
                color = getResources().getColor(R.color.RED);
                break;
            case R.id.green:
                editor.putString("theme","green");
                color = getResources().getColor(R.color.GREEN);
                break;
            case R.id.blue:
                editor.putString("theme","blue");
                color = getResources().getColor(R.color.BLUE);
                break;
            case R.id.pink:
                editor.putString("theme","pink");
                color = getResources().getColor(R.color.PINK);
                break;
            case R.id.purple:
                editor.putString("theme","purple");
                color = getResources().getColor(R.color.PURPLE);
                break;
            case R.id.orange:
                editor.putString("theme","orange");
                color = getResources().getColor(R.color.ORANGE);
        }//end switch
        editor.apply();

        title.setTextColor(color);
        swSound.getThumbDrawable().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
        swMusic.getThumbDrawable().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
//        swSound.getTrackDrawable().setColorFilter(color, PorterDuff.Mode.MULTIPLY);

//        prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
//        String colorTheme = prefs.getString("theme", "");
//        Toast.makeText(getApplicationContext(), colorTheme + " theme", Toast.LENGTH_SHORT).show();
    }
}
