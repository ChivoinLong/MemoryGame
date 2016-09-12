package thebird.com.memory;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chivoin Long on 08-Sep-16.
 */
public class TopickerFragment extends DialogFragment implements ListView.OnItemClickListener{

    List<ListItem> listItems;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        SharedPreferences prefs = getActivity().getSharedPreferences(Settings.PREFS_NAME, getActivity().MODE_PRIVATE);
        int color = getResources().getColor(prefs.getInt("theme", R.color.PINK));

        listItems = new ArrayList<>();
        listItems.add(new ListItem(R.drawable.frog, R.drawable.frog, color));
        listItems.add(new ListItem(R.drawable.bear, R.drawable.bee, color));
        listItems.add(new ListItem(R.drawable.bear, R.drawable.bee, color));

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setTitle("Pick a topic:");

        ListView topicList = new ListView(getActivity());
        ArrayAdapter listAdapter = new CustomListAdapter(getActivity(),
                R.layout.listview_image_item, listItems);
        topicList.setAdapter(listAdapter);
        topicList.setOnItemClickListener(this);
        topicList.setDivider(getActivity().getResources().getDrawable(R.drawable.kid_divider));
        topicList.setDividerHeight(150);
//        topicList.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        builder.setView(topicList);
        Dialog dialog = builder.create();
//        dialog.setCanceledOnTouchOutside(false);

        return dialog;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), String.valueOf(position), Toast.LENGTH_SHORT).show();
    }
}
