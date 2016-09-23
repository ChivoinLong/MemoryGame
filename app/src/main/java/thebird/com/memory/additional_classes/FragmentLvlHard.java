package thebird.com.memory.additional_classes;

import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import thebird.com.memory.MainActivity;
import thebird.com.memory.R;

/**
 * Created by Chivoin Long on 16-Sep-16.
 */
public class FragmentLvlHard extends Fragment {

    static final String fragmentName = "5 X 6";
    ListView listView;
    CustomListAdapter customListAdapter;
    ArrayList<ListItem> newScore;

    Score_DBController score_dbController;
    DatabaseErrorHandler error ;
    SQLiteDatabase.CursorFactory factory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lvlhard, container, false);

        TextView title = (TextView) view.findViewById(R.id.scoreTitle);
        title.setTypeface(MainActivity.typeface);

        TextView lvl = (TextView) view.findViewById(R.id.tvLvl);
        lvl.setTypeface(MainActivity.typeface);

        listView = (ListView)view.findViewById(R.id.lvScore);
        newScore = new ArrayList<>();
        score_dbController = new Score_DBController(getContext(),factory,1,error);
        score_dbController.readData(newScore, 2, getActivity());

        customListAdapter = new CustomListAdapter(getContext(),R.layout.listview_item,newScore, "SCORE");
        listView.setAdapter(customListAdapter);

        return view;
    }

}
