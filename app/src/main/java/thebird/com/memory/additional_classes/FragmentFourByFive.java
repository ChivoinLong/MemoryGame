package thebird.com.memory.additional_classes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import thebird.com.memory.R;

/**
 * Created by Chivoin Long on 12-Aug-16.
 */
public class FragmentFourByFive extends android.support.v4.app.Fragment {

    static final String fragmentName = "4 X 5";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fourbyfive, container, false);
        TextView textView = (TextView) view.findViewById(R.id.fragment_name);
        textView.setText(fragmentName);

        return view;
    }
}
