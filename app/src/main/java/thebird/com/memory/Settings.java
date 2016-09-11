package thebird.com.memory;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class Settings extends Activity {

    public static final String PREFS_NAME = "Setting";
    Switch swMusic;
    Switch swSound;
    RadioGroup themeGroup;
    RadioButton red,green,blue;
    int checked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        TextView title = (TextView) findViewById(R.id.settingTitle);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "font/SweetMemories.ttf");
        title.setTypeface(typeface);

        swMusic = (Switch) findViewById(R.id.swMusic);
        swSound = (Switch) findViewById(R.id.swSound);
        red = (RadioButton) findViewById(R.id.red);
        green = (RadioButton) findViewById(R.id.green);
        blue = (RadioButton) findViewById(R.id.blue);
        themeGroup = (RadioGroup)findViewById(R.id.themeGroup);

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        if (prefs.contains("music")) {
            swMusic.setChecked(prefs.getBoolean("music", false));
        }//end if

        if (prefs.contains("sound")) {
            swSound.setChecked(prefs.getBoolean("sound", false));
        }//end if

        if (prefs.contains("theme")){
            String getTheme = prefs.getString("theme",null);
            switch (getTheme){
                case "red":
                    red.setChecked(true);
                    break;
                case "green":
                    green.setChecked(true);
                    break;
                case "blue":
                    blue.setChecked(true);
                    break;
            }//end switch
        }

        themeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
                checked = radioGroup.indexOfChild(findViewById(i));
                switch (checked){
                    case 0:
                        editor.putString("theme","red");
                        editor.commit();
                        Toast.makeText(getApplicationContext(),"red theme",Toast.LENGTH_SHORT).show();

                        break;

                    case 1:
                        editor.putString("theme","green");
                        editor.commit();
                        Toast.makeText(getApplicationContext(),"green theme",Toast.LENGTH_SHORT).show();

                        break;

                    case 2:
                        editor.putString("theme","blue");
                        editor.commit();
                        Toast.makeText(getApplicationContext(),"blue theme",Toast.LENGTH_SHORT).show();

                        break;
                }//end switch

            }
        });

    }

    public void swMusic(View view) {
        SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        Intent intent = new Intent(this, BackgroundMusic.class);
        if (swMusic.isChecked()){
            editor.putBoolean("music",true);
            Toast.makeText(getApplicationContext(),"music on",Toast.LENGTH_SHORT).show();
            startService(intent);
        }
        else {
            editor.putBoolean("sound",false);
            Toast.makeText(getApplicationContext(),"sound off",Toast.LENGTH_SHORT).show();
            stopService(intent);
        }
        editor.commit();

    }

    public void swSound(View view) {

        if (swSound.isChecked()){

            SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
            editor.putBoolean("sound",true);
            editor.commit();
            Toast.makeText(getApplicationContext(),"sound on",Toast.LENGTH_SHORT).show();

        } else {

            SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
            editor.putBoolean("sound",false);
            editor.commit();
            Toast.makeText(getApplicationContext(),"sound off",Toast.LENGTH_SHORT).show();

        }

    }
}
