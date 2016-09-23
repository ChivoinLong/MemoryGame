package thebird.com.memory.additional_classes;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import thebird.com.memory.MainActivity;
import thebird.com.memory.R;

/**
 * Created by Chivoin Long on 07-Sep-16.
 */
public class CustomListAdapter extends ArrayAdapter<ListItem> {

    Context context;
    int resource;
    String mType;
    LayoutInflater layoutInflater;

    public CustomListAdapter(Context context, int resource, List<ListItem> objects, String type) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        mType = type;
        this.layoutInflater = layoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        ListItem listItem = getItem(position);
        boolean isImage = false;

        if (convertView == null) {
            convertView = layoutInflater.inflate(this.resource, null);
        }
        viewHolder = new ViewHolder();
        if (listItem.getLeftImages() != null && listItem.getRightImages() != null) {
            viewHolder.imgLeft = (ImageView) convertView.findViewById(R.id.image1);
            viewHolder.imgRight = (ImageView) convertView.findViewById(R.id.image2);
            isImage = true;
        }
        viewHolder.tvItem = (TextView) convertView.findViewById(R.id.itemText);

        viewHolder.tvItem.setTypeface(MainActivity.typeface);
        viewHolder.tvItem.setTextColor(listItem.getTextColor());

        if (!isImage) {
            viewHolder.tvItem.setText(listItem.getTexts());
            if (mType == "SCORE" && position == 0){
                viewHolder.tvItem.setTextSize(100);
                viewHolder.tvItem.setTextColor(Color.WHITE);
            }
        }
        if (isImage) {
            viewHolder.imgLeft.setImageResource(listItem.getLeftImages());
            viewHolder.imgRight.setImageResource(listItem.getRightImages());
        }
        return convertView;
    }

}
