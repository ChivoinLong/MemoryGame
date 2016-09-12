package thebird.com.memory;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Chivoin Long on 08-Sep-16.
 */
public class TopickerFragment extends DialogFragment implements ListView.OnItemClickListener{

    int[] leftPic = {R.drawable.rooster, R.drawable.zebra, R.drawable.pig};
    int[] rightPic = {R.drawable.snake, R.drawable.octopus, R.drawable.bird};

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Pick a topic:");

        ListView topicList = new ListView(getActivity());
        ArrayAdapter listAdapter = new CustomListAdapter(getActivity(),
                R.layout.listview_image_item, leftPic, rightPic);
        topicList.setAdapter(listAdapter);
        topicList.setOnItemClickListener(this);
        topicList.setPadding(10,30,10,30);
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
