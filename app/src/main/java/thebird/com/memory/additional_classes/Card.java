package thebird.com.memory.additional_classes;

/**
 * Created by Chivoin Long on 31-May-16.
 */
public class Card {
    public Integer _id;
    public Integer _imgID = null;
    public Integer _state = null;

    public Card(Integer id, Integer imgID, Integer state) {
        _id = id;
        _imgID = imgID;
        _state = state;
    }
}
