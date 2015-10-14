package wearable.hotelbeds.shared.hotel;

import java.util.List;

/**
 * Created by lexfaraday on 14/10/15.
 */
public class HotelInfo {
    public String name;
    public String price;
    public List<String> images;

    public HotelInfo(String name, String price, List<String> images) {
        this.name = name;
        this.price = price;
        this.images = images;
    }
}
