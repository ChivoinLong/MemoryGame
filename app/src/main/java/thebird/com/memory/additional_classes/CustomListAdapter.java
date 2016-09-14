package thebird.com.memory.additional_classes;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

import thebird.com.memory.R;

/**
 * Created by Chivoin Long on 07-Sep-16.
 */
public class CustomListAdapter extends ArrayAdapter<ListItem> {

    Context context;
    int resource;
    LayoutInflater layoutInflater;

    public CustomListAdapter(Context context, int resource, List<ListItem> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
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
        viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.itemText);

        if (!isImage) {
            viewHolder.tvTitle.setText(listItem.getTexts());
        }
        if (isImage) {
            viewHolder.imgLeft.setImageResource(listItem.getLeftImages());
            viewHolder.imgRight.setImageResource(listItem.getRightImages());
        }

        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "font/SweetMemories.ttf");
        viewHolder.tvTitle.setTypeface(typeface);
        viewHolder.tvTitle.setTextColor(listItem.getTextColor());
        return convertView;
    }

}
