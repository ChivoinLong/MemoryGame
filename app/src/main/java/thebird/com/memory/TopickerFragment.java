package thebird.com.memory;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
        listItems.add(new ListItem(R.drawable.list_pic, R.drawable.list_pic, color));
        listItems.add(new ListItem(R.drawable.list_word, R.drawable.list_pic, color));
        listItems.add(new ListItem(R.drawable.list_alphabet, R.drawable.list_pic_word, color));

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        ListView topicList = new ListView(getActivity());
        ArrayAdapter listAdapter = new CustomListAdapter(getActivity(),
                R.layout.listview_image_item, listItems);
        topicList.setAdapter(listAdapter);
        topicList.setOnItemClickListener(this);
        topicList.setDivider(getActivity().getResources().getDrawable(R.drawable.colorful_divider));
        topicList.setDividerHeight(80);

        builder.setView(topicList);
        Dialog dialog = builder.create();
//        dialog.setCanceledOnTouchOutside(false);

        return dialog;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Toast.makeText(getActivity(), String.valueOf(position), Toast.LENGTH_SHORT).show();
        Intent i = new Intent(getActivity(), PlayGame.class);
        i.putExtra("GameType", position);
        startActivity(i);
        dismiss();
    }
}
