package thebird.com.memory;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Chivoin Long on 07-Sep-16.
 */
public class CustomListAdapter extends ArrayAdapter {

    private Activity mActivity;
    private Context mContext;
    private int id;
    private String[] items ;

    public CustomListAdapter(Activity activity, Context context, int textViewResourceId , String[] list)
    {
        super(context, textViewResourceId, list);
        mActivity = activity;
        mContext = context;
        id = textViewResourceId;
        items = list;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent)
    {
        View mView = v ;
        if(mView == null){
            LayoutInflater vi = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = vi.inflate(id, null);
        }

        TextView text = (TextView) mView.findViewById(R.id.itemText);

        if(items[position] != null)
        {
            Typeface typeface = Typeface.createFromAsset(mActivity.getAssets(), "font/SweetMemories.ttf");
            text.setText(items[position]);
            text.setTypeface(typeface);
        }

        return mView;
    }

}
