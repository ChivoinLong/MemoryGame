package thebird.com.memory;

/**
 * Created by Chivoin Long on 31-May-16.
 */
public class Card {
    Integer _id;
    Integer _imgID = null;
    Integer _state = null;

    public Card(Integer id, Integer imgID, Integer state) {
        _id = id;
        _imgID = imgID;
        _state = state;
    }
}
