package thebird.com.memory;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Chivoin Long on 07-Sep-16.
 */
public class CustomListAdapter extends ArrayAdapter {

    private Activity mActivity;
    private int tvId;
    private int mColor;
    private int[] mPicRes1;
    private int[] mPicRes2;
    private String[] arrayStr;


    public CustomListAdapter(Activity activity, int textViewResourceId, int[] picRes1, int[] picRes2) {
        super(activity, textViewResourceId);
        mActivity = activity;
        tvId = textViewResourceId;
        mPicRes1 = picRes1;
        mPicRes2 = picRes2;
    }

    public CustomListAdapter(Activity activity, int textViewResourceId, int color, String[] list) {
        super(activity, textViewResourceId, list);
        mActivity = activity;
        tvId = textViewResourceId;
        mColor = color;
        arrayStr = list;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent)
    {
        LayoutInflater inflater = mActivity.getLayoutInflater();
        View mView = inflater.inflate(tvId, null, true);
        TextView text = (TextView) mView.findViewById(R.id.itemText);
        Typeface typeface = Typeface.createFromAsset(mActivity.getAssets(), "font/SweetMemories.ttf");
        text.setTypeface(typeface);
        text.setTextColor(mColor);

        if (arrayStr[position] != null) {
            text.setText(arrayStr[position]);
        }

        if (mPicRes1 != null && mPicRes2 != null) {
            ImageView img1 = (ImageView) mView.findViewById(R.id.image1);
            ImageView img2 = (ImageView) mView.findViewById(R.id.image2);
            img1.setImageResource(mPicRes1[position]);
            img2.setImageResource(mPicRes2[position]);
            text.setText("and");
        }

        return mView;
    }

}
