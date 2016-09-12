package thebird.com.memory;

/**
 * Created by Chivoin Long on 12-Sep-16.
 */
public class ListItem {
    Integer LeftImages = null;
    Integer RightImages = null;
    String Texts = null;
    Integer TextColor = null;

    public ListItem(Integer leftImages, Integer rightImages, Integer textColor) {
        LeftImages = leftImages;
        RightImages = rightImages;
        TextColor = textColor;
    }

    public ListItem(String texts, Integer textColor) {
        Texts = texts;
        TextColor = textColor;
    }

    public Integer getLeftImages() {
        return LeftImages;
    }

    public Integer getRightImages() {
        return RightImages;
    }

    public String getTexts() {
        return Texts;
    }

    public Integer getTextColor() {
        return TextColor;
    }
}
