package thebird.com.memory;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity implements ListView.OnItemClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView title = (TextView) findViewById(R.id.tvGameName);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "font/SweetMemories.ttf");
        title.setTypeface(typeface);
        ListView menuList = (ListView) findViewById(R.id.list_menu);

        ArrayAdapter listAdapter = new CustomListAdapter(this, getApplicationContext(), R.layout.menu_item , getResources().getStringArray(R.array.menu_items));
        menuList.setAdapter(listAdapter);

        menuList.setOnItemClickListener(this);
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
}
